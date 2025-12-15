#!/bin/bash
# Ecom Deployment Script with Full Diagnostics
# Run this from the project root directory: bash deploy.sh

# Get the directory where this script is located
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_PATH="$SCRIPT_DIR"

# Configuration - modify these if needed
TOMCAT_HOME="${TOMCAT_HOME:-/opt/tomcat}"
WAR_NAME="Ecom-1.0-SNAPSHOT"
DB_NAME="ecom"

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

print_status() {
    if [ $1 -eq 0 ]; then
        echo -e "${GREEN}[OK]${NC} $2"
    else
        echo -e "${RED}[FAILED]${NC} $2"
    fi
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

echo "=========================================="
echo "   ECOM DEPLOYMENT SCRIPT v2.0"
echo "=========================================="
echo ""

# ============================================
# STEP 0: Pre-flight checks
# ============================================
echo "--- PRE-FLIGHT CHECKS ---"

# Check Java
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1)
    print_status 0 "Java installed: $JAVA_VERSION"
else
    print_error "Java not installed!"
    echo "  Run: sudo apt install openjdk-8-jdk"
    exit 1
fi

# Check Maven
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -v 2>&1 | head -n 1)
    print_status 0 "Maven installed: $MVN_VERSION"
else
    print_error "Maven not installed!"
    echo "  Run: sudo apt install maven"
    exit 1
fi

# Check Tomcat directory
if [ -d "$TOMCAT_HOME" ]; then
    print_status 0 "Tomcat directory exists: $TOMCAT_HOME"
else
    print_error "Tomcat not found at $TOMCAT_HOME"
    echo "  Run these commands to install:"
    echo "    cd /opt"
    echo "    sudo wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.113/bin/apache-tomcat-9.0.113.tar.gz"
    echo "    sudo tar -xzf apache-tomcat-9.0.113.tar.gz"
    echo "    sudo mv apache-tomcat-9.0.113 tomcat"
    echo "    sudo chmod +x /opt/tomcat/bin/*.sh"
    exit 1
fi

# Check Tomcat webapps directory
if [ -d "$TOMCAT_HOME/webapps" ]; then
    print_status 0 "Tomcat webapps directory exists"
else
    print_error "Tomcat webapps directory missing!"
    echo "  Tomcat installation may be corrupted. Re-install Tomcat."
    exit 1
fi

# Check project directory
if [ -d "$PROJECT_PATH" ]; then
    print_status 0 "Project directory exists"
else
    print_error "Project directory not found: $PROJECT_PATH"
    exit 1
fi

# Check pom.xml
if [ -f "$PROJECT_PATH/pom.xml" ]; then
    print_status 0 "pom.xml found"
else
    print_error "pom.xml not found in project directory"
    exit 1
fi

echo ""

# ============================================
# STEP 1: Start MySQL
# ============================================
echo "--- STEP 1: DATABASE CHECK ---"

sudo service mysql start 2>/dev/null

# Check if MySQL is running
if sudo service mysql status > /dev/null 2>&1; then
    print_status 0 "MySQL is running"
else
    print_error "MySQL is not running!"
    echo "  Run: sudo service mysql start"
    exit 1
fi

# Check if database exists (uses environment variable or prompts)
DB_USER="${DB_USER:-root}"
DB_EXISTS=$(mysql -u "$DB_USER" -p -e "SHOW DATABASES LIKE '$DB_NAME';" 2>/dev/null | grep -c "$DB_NAME" || echo "0")
if [ "$DB_EXISTS" -eq 1 ]; then
    print_status 0 "Database '$DB_NAME' exists"
else
    print_warning "Database '$DB_NAME' not found or couldn't connect!"
    echo "  You may need to create it and import data:"
    echo "    mysql -u root -p -e 'CREATE DATABASE $DB_NAME;'"
    echo "    mysql -u root -p $DB_NAME < database/schema.sql"
fi

echo ""

# ============================================
# STEP 2: Build Project
# ============================================
echo "--- STEP 2: BUILD PROJECT ---"

cd "$PROJECT_PATH"
print_info "Building project with Maven..."

# Run Maven build and capture output
BUILD_OUTPUT=$(mvn clean package -DskipTests 2>&1)
BUILD_RESULT=$?

if [ $BUILD_RESULT -eq 0 ]; then
    print_status 0 "Maven build successful"
else
    print_error "Maven build FAILED!"
    echo ""
    echo "--- BUILD ERRORS ---"
    echo "$BUILD_OUTPUT" | grep -A 5 "\[ERROR\]"
    echo "--------------------"
    exit 1
fi

# Check WAR file exists
if [ -f "$PROJECT_PATH/target/$WAR_NAME.war" ]; then
    WAR_SIZE=$(ls -lh "$PROJECT_PATH/target/$WAR_NAME.war" | awk '{print $5}')
    print_status 0 "WAR file created: $WAR_NAME.war ($WAR_SIZE)"
else
    print_error "WAR file not created!"
    exit 1
fi

echo ""

# ============================================
# STEP 3: Stop Tomcat
# ============================================
echo "--- STEP 3: STOP TOMCAT ---"

# Check if Tomcat is running
TOMCAT_PID=$(ps aux | grep "[c]atalina" | awk '{print $2}')
if [ -n "$TOMCAT_PID" ]; then
    print_info "Tomcat is running (PID: $TOMCAT_PID). Stopping..."
    $TOMCAT_HOME/bin/shutdown.sh 2>/dev/null
    sleep 3

    # Force kill if still running
    TOMCAT_PID=$(ps aux | grep "[c]atalina" | awk '{print $2}')
    if [ -n "$TOMCAT_PID" ]; then
        print_warning "Tomcat didn't stop gracefully. Force killing..."
        kill -9 $TOMCAT_PID 2>/dev/null
        sleep 2
    fi
    print_status 0 "Tomcat stopped"
else
    print_info "Tomcat was not running"
fi

echo ""

# ============================================
# STEP 4: Deploy WAR
# ============================================
echo "--- STEP 4: DEPLOY WAR ---"

# Remove old deployment
if [ -d "$TOMCAT_HOME/webapps/$WAR_NAME" ] || [ -f "$TOMCAT_HOME/webapps/$WAR_NAME.war" ]; then
    print_info "Removing old deployment..."
    rm -rf "$TOMCAT_HOME/webapps/$WAR_NAME"
    rm -f "$TOMCAT_HOME/webapps/$WAR_NAME.war"
    print_status 0 "Old deployment removed"
fi

# Copy new WAR
print_info "Copying WAR file to Tomcat..."
cp "$PROJECT_PATH/target/$WAR_NAME.war" "$TOMCAT_HOME/webapps/"
if [ $? -eq 0 ]; then
    print_status 0 "WAR file copied to webapps"
else
    print_error "Failed to copy WAR file!"
    exit 1
fi

echo ""

# ============================================
# STEP 5: Start Tomcat
# ============================================
echo "--- STEP 5: START TOMCAT ---"

# Clear old logs
> "$TOMCAT_HOME/logs/catalina.out" 2>/dev/null

print_info "Starting Tomcat..."
$TOMCAT_HOME/bin/startup.sh

# Wait for Tomcat to start
print_info "Waiting for Tomcat to start (max 30 seconds)..."
for i in {1..30}; do
    if curl -s -o /dev/null -w "%{http_code}" http://localhost:8080 | grep -q "200\|302"; then
        print_status 0 "Tomcat is responding on port 8080"
        break
    fi
    if [ $i -eq 30 ]; then
        print_warning "Tomcat may be slow to start. Continuing checks..."
    fi
    sleep 1
    echo -n "."
done
echo ""

echo ""

# ============================================
# STEP 6: Verify Deployment
# ============================================
echo "--- STEP 6: VERIFY DEPLOYMENT ---"

# Wait for WAR extraction
print_info "Waiting for WAR extraction (max 20 seconds)..."
for i in {1..20}; do
    if [ -d "$TOMCAT_HOME/webapps/$WAR_NAME" ]; then
        print_status 0 "WAR extracted successfully"
        break
    fi
    if [ $i -eq 20 ]; then
        print_error "WAR not extracted after 20 seconds!"
    fi
    sleep 1
    echo -n "."
done
echo ""

# Check if deployment directory exists
if [ -d "$TOMCAT_HOME/webapps/$WAR_NAME" ]; then
    FILE_COUNT=$(find "$TOMCAT_HOME/webapps/$WAR_NAME" -type f | wc -l)
    print_status 0 "Deployment directory contains $FILE_COUNT files"
else
    print_error "Deployment directory does not exist!"
    echo ""
    echo "--- CHECKING LOGS FOR ERRORS ---"
    grep -i "error\|exception\|failed" "$TOMCAT_HOME/logs/catalina.out" | tail -20
    exit 1
fi

# Check for deployment errors in logs
DEPLOY_ERRORS=$(grep -i "SEVERE\|error deploying\|failed to start" "$TOMCAT_HOME/logs/catalina.out" 2>/dev/null | head -10)
if [ -n "$DEPLOY_ERRORS" ]; then
    print_warning "Possible deployment issues found in logs:"
    echo "$DEPLOY_ERRORS"
    echo ""
fi

# Test HTTP response
print_info "Testing HTTP response..."
HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" "http://localhost:8080/$WAR_NAME/" 2>/dev/null)

if [ "$HTTP_CODE" = "200" ]; then
    print_status 0 "Application responding with HTTP 200"
elif [ "$HTTP_CODE" = "302" ]; then
    print_status 0 "Application responding with HTTP 302 (redirect - normal for login)"
elif [ "$HTTP_CODE" = "404" ]; then
    print_error "Application returning HTTP 404 (Not Found)!"
    echo ""
    echo "--- DIAGNOSTIC INFO ---"
    echo "Webapps directory contents:"
    ls -la "$TOMCAT_HOME/webapps/"
    echo ""
    echo "Recent Tomcat errors:"
    grep -i "error\|exception\|severe" "$TOMCAT_HOME/logs/catalina.out" | tail -20
    echo ""
    echo "Possible causes:"
    echo "  1. WAR file corrupted - try rebuilding"
    echo "  2. web.xml missing or invalid"
    echo "  3. Servlet initialization error"
    exit 1
elif [ "$HTTP_CODE" = "500" ]; then
    print_error "Application returning HTTP 500 (Server Error)!"
    echo ""
    echo "--- SERVER ERROR LOG ---"
    grep -A 10 "Exception" "$TOMCAT_HOME/logs/catalina.out" | tail -30
    exit 1
else
    print_warning "Unexpected HTTP code: $HTTP_CODE"
fi

echo ""

# ============================================
# FINAL SUMMARY
# ============================================
echo "=========================================="
echo -e "${GREEN}   DEPLOYMENT COMPLETE!${NC}"
echo "=========================================="
echo ""
echo "Application URL: http://localhost:8080/$WAR_NAME/"
echo ""
echo "Login Credentials:"
echo "  +-----------------+---------------+----------+"
echo "  | Role            | Email         | Password |"
echo "  +-----------------+---------------+----------+"
echo "  | Owner           | K@gmail.com   | 1234     |"
echo "  | Regional Manager| T@gmail.com   | 1234     |"
echo "  | Store Manager   | P@gmail.com   | 1234     |"
echo "  | Floor Manager   | C@gmail.com   | 1234     |"
echo "  +-----------------+---------------+----------+"
echo ""
echo "Useful Commands:"
echo "  View logs:    tail -f $TOMCAT_HOME/logs/catalina.out"
echo "  Stop Tomcat:  $TOMCAT_HOME/bin/shutdown.sh"
echo "  Start Tomcat: $TOMCAT_HOME/bin/startup.sh"
echo "=========================================="
