# Project Changelog

This document outlines the changes made to improve the codebase quality and organization.

---

## Why This Document Exists

This project was developed during a learning phase. The "Before" state reflects the natural progression of a developer gaining experience with Java EE and software architecture. The "After" state represents the application of improved knowledge and industry best practices.

---

## Before (Learning Phase)

### What It Looked Like

**Inconsistent Folder Structure:**
```
src/main/java/
├── Customer/
│   ├── CustomerController/    # Redundant prefix
│   ├── CustomerDao/           # Lowercase 'Dao'
│   ├── CustomerModel/         # Redundant prefix
│   └── CustomerService/       # Redundant prefix
├── Order/
│   ├── OrderController/
│   ├── OrderDao/
│   ├── OrderModel/
│   └── OrderService/
├── Sale/
│   ├── SaleController/
│   ├── SaleDao/
│   ├── SaleModel/
│   └── SaleService/
├── Item/
│   ├── ItemDao/
│   └── ItemService/
```

**Class Naming Issues:**
- `storeProducts` - lowercase class name (should be PascalCase)
- `storesIBT` - lowercase class name
- `storeProductDAO`, `storeProductDAOImp` - lowercase prefixes
- `storeProductServices`, `storeProductsServicesImp` - lowercase, inconsistent naming
- `CustomerDaoImpl` - inconsistent DAO casing (should be `DAO`)

**Why It Was Like This:**
- Early-stage learning where focus was on making things work rather than conventions
- Limited exposure to Java naming standards and best practices
- Iterative development without refactoring as knowledge grew
- Common for beginners to prioritize functionality over structure

---

## After (Improved Knowledge)

### What It Looks Like Now

**Consistent Folder Structure:**
```
src/main/java/
├── Customer/
│   ├── Controller/     # Clean, no redundant prefix
│   ├── DAO/            # Uppercase acronym (proper convention)
│   ├── Model/          # Clean
│   └── Service/        # Clean
├── Order/
│   ├── Controller/
│   ├── DAO/
│   ├── Model/
│   └── Service/
├── Sale/
│   ├── Controller/
│   ├── DAO/
│   ├── Model/
│   └── Service/
├── Item/
│   ├── Controller/
│   ├── DAO/
│   ├── Model/
│   └── Service/
```

**Corrected Class Names:**
- `StoreProduct` - proper PascalCase
- `StoreIBT` - proper PascalCase
- `StoreProductDAO`, `StoreProductDAOImpl` - proper naming
- `StoreProductService`, `StoreProductServiceImpl` - consistent Service naming
- `CustomerDAO`, `CustomerDAOImpl` - uppercase DAO acronym

---

## Changes Applied

### 1. Class Naming Fixes

| Before | After | Reason |
|--------|-------|--------|
| `storeProducts` | `StoreProduct` | Java classes use PascalCase |
| `storesIBT` | `StoreIBT` | PascalCase convention |
| `storeProductDAO` | `StoreProductDAO` | PascalCase for class names |
| `storeProductDAOImp` | `StoreProductDAOImpl` | Full "Impl" suffix is standard |
| `storeProductServices` | `StoreProductService` | Service (singular) is standard |
| `storeProductsServicesImp` | `StoreProductServiceImpl` | Consistent naming |

### 2. Package/Folder Restructuring

| Before | After | Reason |
|--------|-------|--------|
| `Customer.CustomerController` | `Customer.Controller` | Removes redundant prefix |
| `Customer.CustomerDao` | `Customer.DAO` | Uppercase acronym, no redundancy |
| `Customer.CustomerModel` | `Customer.Model` | Clean package naming |
| `Customer.CustomerService` | `Customer.Service` | Removes redundant prefix |
| `Order.OrderController` | `Order.Controller` | Consistent pattern |
| `Order.OrderDao` | `Order.DAO` | Consistent pattern |
| `Item.ItemDao` | `Item.DAO` | Consistent pattern |
| `Sale.SaleDao` | `Sale.DAO` | Consistent pattern |

### 3. DAO File Naming

| Before | After | Reason |
|--------|-------|--------|
| `CustomerDao.java` | `CustomerDAO.java` | DAO is an acronym (Data Access Object) |
| `CustomerDaoImpl.java` | `CustomerDAOImpl.java` | Acronyms should be uppercase |
| `OrderDao.java` | `OrderDAO.java` | Consistent convention |
| `SaleDao.java` | `SaleDAO.java` | Consistent convention |
| `ItemDao.java` | `ItemDAO.java` | Consistent convention |

### 4. File/Folder Typo Fixes

| Before | After |
|--------|-------|
| `reginoalReg.jsp` | `regionalReg.jsp` |

---

## What Stayed The Same

The following aspects were intentionally preserved:

- **Architecture**: The existing MVC + Service + DAO layered architecture already follows Separation of Concerns
- **Business Logic**: No functional changes were made
- **Database Queries**: All SQL and data access patterns remain unchanged
- **JSP Views**: Frontend files untouched (except typo fixes)

---

## Lessons Learned

1. **Naming Conventions Matter**: Consistent naming makes code easier to navigate and maintain
2. **Acronyms in Java**: DAO, DTO, URL should be uppercase in class names
3. **Package Structure**: Avoid redundant prefixes (e.g., `Customer.CustomerService` is redundant)
4. **Refactoring is Normal**: Code evolves as developers grow - it's expected to improve over time
5. **Separation of Concerns**: Already implemented correctly with Controller/Service/DAO/Model layers

---

## Technical Details

- **Framework**: Java EE 7 (Servlet 3.1, JSP)
- **Build Tool**: Maven
- **Database**: MySQL
- **Server**: Apache Tomcat 9.x

---

*This changelog demonstrates growth as a developer - recognizing areas for improvement and applying better practices while maintaining working functionality.*
