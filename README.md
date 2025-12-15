# Carol's Boutique - Retail Point of Sale System

A Java EE web application for retail store management with multi-store support, inventory tracking, and sales reporting. Built for Carol's Boutique retail chain.

## Quick Start

```bash
# Prerequisites: Java 8+, Maven, MySQL 8.0+, Tomcat 9+

# 1. Set up database
mysql -u root -p -e "CREATE DATABASE ecom"
mysql -u root -p ecom < database/schema.sql

# 2. Configure credentials (create /opt/tomcat/bin/setenv.sh)
export DB_PASSWORD="your_password"
export CATALINA_OPTS="-DDB_PASSWORD=$DB_PASSWORD"

# 3. Build and deploy
mvn clean package -DskipTests
cp target/Ecom-1.0-SNAPSHOT.war /opt/tomcat/webapps/

# 4. Access at http://localhost:8080/Ecom-1.0-SNAPSHOT/
```

**Demo Credentials:** K@gmail.com / 1234 (Owner)

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [API & Architecture](#architecture)
- [Contributing](#contributing)

---

## Features

| Module | Description |
|--------|-------------|
| **Point of Sale** | Process transactions with barcode scanning |
| **Multi-Store** | Manage inventory across multiple locations |
| **Inventory** | Stock tracking, inter-branch transfers, shortage alerts |
| **Customers** | Profiles, purchase history, layby/layaway plans |
| **Reports** | Sales analytics, PDF export, visual dashboards |
| **Access Control** | Role-based permissions (Owner, Manager, Cashier) |

## Tech Stack

- **Backend:** Java 8, Java EE 7 (Servlet/JSP)
- **Database:** MySQL 8.0
- **Build:** Maven
- **Server:** Apache Tomcat 9+
- **Libraries:** PDFBox, ZXing, JFreeChart, Twilio, Lombok

---

## Installation

### Prerequisites

- Java JDK 8+
- Maven 3.6+
- MySQL 8.0+
- Apache Tomcat 9+

### Option 1: Automated (Recommended)

```bash
bash deploy.sh
```

The script handles: prerequisite checks, Maven build, Tomcat deployment, and verification.

### Option 2: Manual

**1. Database Setup**

```sql
CREATE DATABASE ecom;
```

```bash
# Use the schema file included in this repo
mysql -u root -p ecom < database/schema.sql
```

**2. Build**

```bash
mvn clean package -DskipTests
```

**3. Deploy**

```bash
cp target/Ecom-1.0-SNAPSHOT.war /opt/tomcat/webapps/
```

**4. Start Services**

```bash
sudo service mysql start
/opt/tomcat/bin/startup.sh
```

**5. Access**

Open http://localhost:8080/Ecom-1.0-SNAPSHOT/

---

## Configuration

Database credentials are loaded from system properties or environment variables (no hardcoded passwords).

### Environment Variables

Copy `.env.example` to `.env` and configure your values:

```bash
cp .env.example .env
# Edit .env with your actual credentials
```

**Required Variables:**

| Variable | Description | Example |
|----------|-------------|---------|
| `DB_URL` | MySQL connection URL | `jdbc:mysql://localhost:3306/ecom` |
| `DB_USER` | Database username | `root` |
| `DB_PASSWORD` | Database password | `your_password` |
| `SMTP_HOST` | Email server | `smtp.gmail.com` |
| `SMTP_PORT` | Email port | `587` |
| `SMTP_USERNAME` | Email address | `your_email@gmail.com` |
| `SMTP_PASSWORD` | Email app password | `your_app_password` |
| `APP_BASE_URL` | Application URL | `http://localhost:8080/Ecom-1.0-SNAPSHOT` |

### Tomcat Setup (Recommended)

Create `/opt/tomcat/bin/setenv.sh`:

```bash
#!/bin/bash
export DB_URL="jdbc:mysql://localhost:3306/ecom?useSSL=false"
export DB_USER="root"
export DB_PASSWORD="your_password"

export CATALINA_OPTS="$CATALINA_OPTS -DDB_URL=$DB_URL -DDB_USER=$DB_USER -DDB_PASSWORD=$DB_PASSWORD"
```

### Configuration Priority

1. System Property (`-DDB_PASSWORD=xxx`)
2. Environment Variable (`export DB_PASSWORD=xxx`)
3. Default (empty - not for production)

### Default Users

| Role | Email | Password |
|------|-------|----------|
| Owner | K@gmail.com | 1234 |
| Regional Manager | T@gmail.com | 1234 |
| Store Manager | P@gmail.com | 1234 |
| Floor Manager | C@gmail.com | 1234 |

---

## Project Structure

```
├── database/
│   └── schema.sql      # MySQL database schema
├── .env.example        # Environment variables template
├── src/main/java/
│   ├── Connection/     # Database connection (DbConn.java)
│   ├── User/           # Authentication & user management
│   ├── Customer/       # Customer profiles & history
│   ├── Product/        # Product catalog
│   ├── Store/          # Store management
│   ├── Sale/           # Sales transactions
│   ├── Order/          # Order processing
│   ├── Payments/       # Payment handling
│   ├── IBTOrders/      # Inter-branch transfers
│   ├── Reports/        # Analytics & PDF generation
│   └── Ratings/        # Customer feedback
└── src/main/webapp/
    ├── *.jsp           # View templates
    ├── Stylepages/     # CSS
    └── Images/         # Static assets
```

---

## Architecture

```
┌─────────────────────────────────┐
│     Presentation (JSP)          │
└───────────────┬─────────────────┘
                ▼
┌─────────────────────────────────┐
│   Business Logic (Servlets)     │
└───────────────┬─────────────────┘
                ▼
┌─────────────────────────────────┐
│    Data Access (DAO + JDBC)     │
└───────────────┬─────────────────┘
                ▼
┌─────────────────────────────────┐
│         MySQL Database          │
└─────────────────────────────────┘
```

---

## Useful Commands

```bash
# View logs
tail -f /opt/tomcat/logs/catalina.out

# Restart Tomcat
/opt/tomcat/bin/shutdown.sh && /opt/tomcat/bin/startup.sh

# Rebuild & redeploy
bash deploy.sh
```

---

## Code Quality & Security

### Security Features

- **BCrypt Password Hashing** - Passwords are hashed using BCrypt with configurable work factor
- **Environment Variables** - Sensitive credentials loaded from environment (not hardcoded)
- **Parameterized Queries** - All SQL uses PreparedStatement to prevent injection
- **Input Validation** - InputValidator utility class for user input sanitization

### Code Standards

- **Resource Management** - All DAOs use try-with-resources for automatic cleanup
- **Logging** - java.util.logging.Logger replaces printStackTrace()
- **DRY Principle** - Helper methods reduce code duplication in DAOs

### Key Utility Classes

| Class | Location | Purpose |
|-------|----------|---------|
| `PasswordUtil` | User/Service/ | BCrypt password hashing & verification |
| `InputValidator` | User/Service/ | Input validation & sanitization |
| `DbConn` | Connection/ | Database connection management |

### Database Schema

The `database/schema.sql` file contains the complete database structure including:
- All tables (stores, user, customer, products, orders, sales, payments, etc.)
- Foreign key relationships
- Performance indexes
- Inter-branch transfer (IBT) tables

---

## Changelog

### Recent Updates

- **Code Quality Refactor** (Dec 2025)
  - Implemented BCrypt password hashing (PasswordUtil.java)
  - Added InputValidator utility class
  - Refactored all DAOs to use try-with-resources
  - Replaced printStackTrace() with proper logging
  - Removed hardcoded credentials (SMTP, manager password, file paths)
  - Added helper methods to reduce code duplication
  - Added session null checks to all servlets
  - Added number parsing safety (try-catch for parseInt/parseDouble)
  - Created `database/schema.sql` - Complete MySQL database schema
  - Created `.env.example` - Environment variables template

- Externalized database credentials (security improvement)
- Added automated deployment script (`deploy.sh`)
- Fixed missing DAO method implementations
- Added password reset functionality with manager authorization

### Roadmap

- [x] Implement BCrypt password hashing
- [x] Fix resource leaks in DAOs
- [x] Add input validation utilities
- [ ] Migrate to Spring Boot
- [ ] Add connection pooling (HikariCP)
- [ ] Implement unit tests
- [ ] Add CSRF protection

---

## Contributors

Built collaboratively during a skills development program:

- Keiketlile Maboe
- Tsheno
- Tshiamo

---

## License

Educational purposes only.
