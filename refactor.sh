#!/bin/bash

# Cosmetic Folder Restructuring Script
# This script standardizes the folder structure across all packages
# Run this from the project root directory: bash refactor.sh

set -e  # Exit on any error

# Get the directory where this script is located
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$SCRIPT_DIR"
SRC_DIR="$PROJECT_ROOT/src/main/java"

echo "=========================================="
echo "Starting Cosmetic Folder Restructuring"
echo "=========================================="

# Function to refactor a package
refactor_package() {
    local pkg=$1
    local old_controller=$2
    local old_dao=$3
    local old_model=$4
    local old_service=$5

    echo ""
    echo "Processing $pkg package..."

    cd "$SRC_DIR/$pkg"

    # Create new directories
    mkdir -p Controller DAO Model Service

    # Move and update Controller files
    if [ -d "$old_controller" ]; then
        for file in "$old_controller"/*.java; do
            if [ -f "$file" ]; then
                filename=$(basename "$file")
                # Update package declaration
                sed "s/package ${pkg}\.${old_controller};/package ${pkg}.Controller;/g" "$file" > "Controller/$filename"
                echo "  Moved: $filename -> Controller/"
            fi
        done
    fi

    # Move and update DAO files
    if [ -d "$old_dao" ]; then
        for file in "$old_dao"/*.java; do
            if [ -f "$file" ]; then
                filename=$(basename "$file")
                sed "s/package ${pkg}\.${old_dao};/package ${pkg}.DAO;/g" "$file" > "DAO/$filename"
                echo "  Moved: $filename -> DAO/"
            fi
        done
    fi

    # Move and update Model files
    if [ -d "$old_model" ]; then
        for file in "$old_model"/*.java; do
            if [ -f "$file" ]; then
                filename=$(basename "$file")
                sed "s/package ${pkg}\.${old_model};/package ${pkg}.Model;/g" "$file" > "Model/$filename"
                echo "  Moved: $filename -> Model/"
            fi
        done
    fi

    # Move and update Service files
    if [ -d "$old_service" ]; then
        for file in "$old_service"/*.java; do
            if [ -f "$file" ]; then
                filename=$(basename "$file")
                sed "s/package ${pkg}\.${old_service};/package ${pkg}.Service;/g" "$file" > "Service/$filename"
                echo "  Moved: $filename -> Service/"
            fi
        done
    fi
}

# Refactor Customer package
refactor_package "Customer" "CustomerController" "CustomerDao" "CustomerModel" "CustomerService"

# Refactor Order package
refactor_package "Order" "OrderController" "OrderDao" "OrderModel" "OrderService"

# Refactor Sale package
refactor_package "Sale" "SaleController" "SaleDao" "SaleModel" "SaleService"

# Refactor Item package (only Dao and Service need fixing)
echo ""
echo "Processing Item package..."
cd "$SRC_DIR/Item"
mkdir -p DAO Service
if [ -d "ItemDao" ]; then
    for file in ItemDao/*.java; do
        if [ -f "$file" ]; then
            filename=$(basename "$file")
            sed "s/package Item\.ItemDao;/package Item.DAO;/g" "$file" > "DAO/$filename"
            echo "  Moved: $filename -> DAO/"
        fi
    done
fi
if [ -d "ItemService" ]; then
    for file in ItemService/*.java; do
        if [ -f "$file" ]; then
            filename=$(basename "$file")
            sed "s/package Item\.ItemService;/package Item.Service;/g" "$file" > "Service/$filename"
            echo "  Moved: $filename -> Service/"
        fi
    done
fi

echo ""
echo "=========================================="
echo "Updating import statements across codebase..."
echo "=========================================="

cd "$SRC_DIR"

# Update all import statements
find . -name "*.java" -type f | while read file; do
    # Customer imports
    sed -i 's/import Customer\.CustomerController\./import Customer.Controller./g' "$file"
    sed -i 's/import Customer\.CustomerDao\./import Customer.DAO./g' "$file"
    sed -i 's/import Customer\.CustomerModel\./import Customer.Model./g' "$file"
    sed -i 's/import Customer\.CustomerService\./import Customer.Service./g' "$file"

    # Order imports
    sed -i 's/import Order\.OrderController\./import Order.Controller./g' "$file"
    sed -i 's/import Order\.OrderDao\./import Order.DAO./g' "$file"
    sed -i 's/import Order\.OrderModel\./import Order.Model./g' "$file"
    sed -i 's/import Order\.OrderService\./import Order.Service./g' "$file"

    # Sale imports
    sed -i 's/import Sale\.SaleController\./import Sale.Controller./g' "$file"
    sed -i 's/import Sale\.SaleDao\./import Sale.DAO./g' "$file"
    sed -i 's/import Sale\.SaleModel\./import Sale.Model./g' "$file"
    sed -i 's/import Sale\.SaleService\./import Sale.Service./g' "$file"

    # Item imports
    sed -i 's/import Item\.ItemDao\./import Item.DAO./g' "$file"
    sed -i 's/import Item\.ItemService\./import Item.Service./g' "$file"

    # Also fix interface/class references (Dao -> DAO in class names)
    sed -i 's/CustomerDao/CustomerDAO/g' "$file"
    sed -i 's/CustomerDaoImpl/CustomerDAOImpl/g' "$file"
    sed -i 's/OrderDao/OrderDAO/g' "$file"
    sed -i 's/OrderDaoImpl/OrderDAOImpl/g' "$file"
    sed -i 's/SaleDao/SaleDAO/g' "$file"
    sed -i 's/SaleDaoImpl/SaleDAOImpl/g' "$file"
    sed -i 's/ItemDao/ItemDAO/g' "$file"
    sed -i 's/ItemDaoImpl/ItemDAOImpl/g' "$file"
done

echo ""
echo "=========================================="
echo "Renaming DAO files to use uppercase..."
echo "=========================================="

# Rename DAO files
cd "$SRC_DIR"

# Customer DAO files
if [ -f "Customer/DAO/CustomerDao.java" ]; then
    mv "Customer/DAO/CustomerDao.java" "Customer/DAO/CustomerDAO.java"
    sed -i 's/interface CustomerDao/interface CustomerDAO/g' "Customer/DAO/CustomerDAO.java"
    echo "  Renamed: CustomerDao.java -> CustomerDAO.java"
fi
if [ -f "Customer/DAO/CustomerDaoImpl.java" ]; then
    mv "Customer/DAO/CustomerDaoImpl.java" "Customer/DAO/CustomerDAOImpl.java"
    sed -i 's/class CustomerDaoImpl/class CustomerDAOImpl/g' "Customer/DAO/CustomerDAOImpl.java"
    sed -i 's/implements CustomerDao/implements CustomerDAO/g' "Customer/DAO/CustomerDAOImpl.java"
    echo "  Renamed: CustomerDaoImpl.java -> CustomerDAOImpl.java"
fi

# Order DAO files
if [ -f "Order/DAO/OrderDao.java" ]; then
    mv "Order/DAO/OrderDao.java" "Order/DAO/OrderDAO.java"
    sed -i 's/interface OrderDao/interface OrderDAO/g' "Order/DAO/OrderDAO.java"
    echo "  Renamed: OrderDao.java -> OrderDAO.java"
fi
if [ -f "Order/DAO/OrderDaoImpl.java" ]; then
    mv "Order/DAO/OrderDaoImpl.java" "Order/DAO/OrderDAOImpl.java"
    sed -i 's/class OrderDaoImpl/class OrderDAOImpl/g' "Order/DAO/OrderDAOImpl.java"
    sed -i 's/implements OrderDao/implements OrderDAO/g' "Order/DAO/OrderDAOImpl.java"
    echo "  Renamed: OrderDaoImpl.java -> OrderDAOImpl.java"
fi

# Sale DAO files
if [ -f "Sale/DAO/SaleDao.java" ]; then
    mv "Sale/DAO/SaleDao.java" "Sale/DAO/SaleDAO.java"
    sed -i 's/interface SaleDao/interface SaleDAO/g' "Sale/DAO/SaleDAO.java"
    echo "  Renamed: SaleDao.java -> SaleDAO.java"
fi
if [ -f "Sale/DAO/SaleDaoImpl.java" ]; then
    mv "Sale/DAO/SaleDaoImpl.java" "Sale/DAO/SaleDAOImpl.java"
    sed -i 's/class SaleDaoImpl/class SaleDAOImpl/g' "Sale/DAO/SaleDAOImpl.java"
    sed -i 's/implements SaleDao/implements SaleDAO/g' "Sale/DAO/SaleDAOImpl.java"
    echo "  Renamed: SaleDaoImpl.java -> SaleDAOImpl.java"
fi

# Item DAO files
if [ -f "Item/DAO/ItemDao.java" ]; then
    mv "Item/DAO/ItemDao.java" "Item/DAO/ItemDAO.java"
    sed -i 's/interface ItemDao/interface ItemDAO/g' "Item/DAO/ItemDAO.java"
    echo "  Renamed: ItemDao.java -> ItemDAO.java"
fi
if [ -f "Item/DAO/ItemDaoImpl.java" ]; then
    mv "Item/DAO/ItemDaoImpl.java" "Item/DAO/ItemDAOImpl.java"
    sed -i 's/class ItemDaoImpl/class ItemDAOImpl/g' "Item/DAO/ItemDAOImpl.java"
    sed -i 's/implements ItemDao/implements ItemDAO/g' "Item/DAO/ItemDAOImpl.java"
    echo "  Renamed: ItemDaoImpl.java -> ItemDAOImpl.java"
fi

echo ""
echo "=========================================="
echo "Removing old folders..."
echo "=========================================="

cd "$SRC_DIR"

# Remove old Customer folders
rm -rf Customer/CustomerController Customer/CustomerDao Customer/CustomerModel Customer/CustomerService 2>/dev/null || true
echo "  Removed old Customer subfolders"

# Remove old Order folders
rm -rf Order/OrderController Order/OrderDao Order/OrderModel Order/OrderService 2>/dev/null || true
echo "  Removed old Order subfolders"

# Remove old Sale folders
rm -rf Sale/SaleController Sale/SaleDao Sale/SaleModel Sale/SaleService 2>/dev/null || true
echo "  Removed old Sale subfolders"

# Remove old Item folders
rm -rf Item/ItemDao Item/ItemService 2>/dev/null || true
echo "  Removed old Item subfolders"

echo ""
echo "=========================================="
echo "Refactoring Complete!"
echo "=========================================="
echo ""
echo "Next step: Run 'mvn clean package -DskipTests' to verify build"
