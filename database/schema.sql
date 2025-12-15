-- =====================================================
-- Ecom Database Schema
-- MySQL Database Schema for E-commerce/POS Application
-- =====================================================

-- Create database
CREATE DATABASE IF NOT EXISTS ecom CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ecom;

-- =====================================================
-- Core Tables
-- =====================================================

-- Stores table
CREATE TABLE IF NOT EXISTS stores (
    store_id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(100) NOT NULL,
    province VARCHAR(100),
    city VARCHAR(100),
    address VARCHAR(255),
    manager INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Users/Employees table
CREATE TABLE IF NOT EXISTS user (
    EMPLOYEEID INT AUTO_INCREMENT PRIMARY KEY,
    FIRSTNAME VARCHAR(100) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ACCESS_LEVEL VARCHAR(50) DEFAULT 'employee',
    EmpID VARCHAR(50),
    PhoneNo VARCHAR(20),
    store_id INT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Add manager foreign key after user table exists
ALTER TABLE stores ADD CONSTRAINT fk_store_manager
    FOREIGN KEY (manager) REFERENCES user(EMPLOYEEID) ON DELETE SET NULL;

-- Customers table
CREATE TABLE IF NOT EXISTS customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    surname VARCHAR(100),
    email VARCHAR(255),
    PhoneNo VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Product Types/Categories table
CREATE TABLE IF NOT EXISTS types (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    sub_category_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Products table
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INT DEFAULT 0,
    type_id INT,
    gendre VARCHAR(50),
    barcode VARCHAR(100),
    sub_category_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (type_id) REFERENCES types(type_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Store Products (Inventory per store)
CREATE TABLE IF NOT EXISTS store_products (
    store_productsID INT AUTO_INCREMENT PRIMARY KEY,
    productID INT NOT NULL,
    storeID INT NOT NULL,
    quantity INT DEFAULT 0,
    barcode VARCHAR(100),
    price DECIMAL(10, 2) DEFAULT 0.00,
    item_recived DATE,
    last_update DATE,
    colour VARCHAR(50),
    size VARCHAR(50),
    storeName VARCHAR(100),
    productName VARCHAR(255),
    FOREIGN KEY (productID) REFERENCES products(product_id) ON DELETE CASCADE,
    FOREIGN KEY (storeID) REFERENCES stores(store_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================
-- Order & Sales Tables
-- =====================================================

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    total_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(50) DEFAULT 'pending',
    store_id INT,
    customer_id INT,
    sent BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(EMPLOYEEID) ON DELETE SET NULL,
    FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Sales table
CREATE TABLE IF NOT EXISTS sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    employee_id INT,
    amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    store_id INT,
    customer_id INT,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES user(EMPLOYEEID) ON DELETE SET NULL,
    FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Order Items table
CREATE TABLE IF NOT EXISTS orderitems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT DEFAULT 1,
    price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    sale_id INT,
    reverse BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE SET NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(sale_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(50) DEFAULT 'pending',
    store_id INT,
    employee_id INT,
    product_id INT,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE SET NULL,
    FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES user(EMPLOYEEID) ON DELETE SET NULL,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- =====================================================
-- Ratings & Reviews
-- =====================================================

-- Ratings table
CREATE TABLE IF NOT EXISTS ratings (
    rating_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    customer_id INT,
    store_id INT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comments TEXT,
    rating_date DATE,
    rateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================
-- Inter-Branch Transfer (IBT) Tables
-- =====================================================

-- IBT Orders Received table
CREATE TABLE IF NOT EXISTS ibt_ordersrecived (
    IBTOrderID INT AUTO_INCREMENT PRIMARY KEY,
    storeName VARCHAR(100),
    productID VARCHAR(50),
    storeID VARCHAR(50),
    productName VARCHAR(255),
    EmployeeID VARCHAR(50),
    price VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Stores IBT table
CREATE TABLE IF NOT EXISTS storesibt (
    storesIBT INT AUTO_INCREMENT PRIMARY KEY,
    storeRequestingName VARCHAR(100),
    productName VARCHAR(255),
    orderDate VARCHAR(50),
    requestID VARCHAR(50),
    storeRecievengName VARCHAR(100),
    totalPrice VARCHAR(50),
    quantity VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- =====================================================
-- Indexes for Performance
-- =====================================================

-- User indexes
CREATE INDEX idx_user_email ON user(EMAIL);
CREATE INDEX idx_user_store ON user(store_id);

-- Product indexes
CREATE INDEX idx_products_barcode ON products(barcode);
CREATE INDEX idx_products_type ON products(type_id);

-- Store products indexes
CREATE INDEX idx_store_products_store ON store_products(storeID);
CREATE INDEX idx_store_products_product ON store_products(productID);
CREATE INDEX idx_store_products_barcode ON store_products(barcode);

-- Order indexes
CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_orders_store ON orders(store_id);
CREATE INDEX idx_orders_customer ON orders(customer_id);
CREATE INDEX idx_orders_status ON orders(status);

-- Sales indexes
CREATE INDEX idx_sales_employee ON sales(employee_id);
CREATE INDEX idx_sales_store ON sales(store_id);
CREATE INDEX idx_sales_date ON sales(sale_date);

-- Payment indexes
CREATE INDEX idx_payments_order ON payments(order_id);
CREATE INDEX idx_payments_store ON payments(store_id);
CREATE INDEX idx_payments_date ON payments(payment_date);

-- Rating indexes
CREATE INDEX idx_ratings_product ON ratings(product_id);
CREATE INDEX idx_ratings_store ON ratings(store_id);
CREATE INDEX idx_ratings_date ON ratings(rating_date);

-- =====================================================
-- Sample Data (Optional - Remove in production)
-- =====================================================

-- Insert a default store
-- INSERT INTO stores (store_name, province, city, address)
-- VALUES ('Main Store', 'Gauteng', 'Johannesburg', '123 Main Street');

-- Insert a default admin user (password should be hashed in production)
-- INSERT INTO user (FIRSTNAME, SURNAME, EMAIL, PASSWORD, ACCESS_LEVEL, store_id)
-- VALUES ('Admin', 'User', 'admin@example.com', 'hashed_password_here', 'admin', 1);
