# Shipping Company OOAD System

A comprehensive Spring Boot-based shipping company management system built using Object-Oriented Analysis and Design (OOAD) principles. This system manages the complete lifecycle of shipping operations including user management, order processing, inventory management, and account payable functions.

## 🚀 Project Overview

This system provides end-to-end functionality for a shipping company with multiple user roles and automated workflows. It handles customer orders, supplier management, inventory tracking, order fulfillment, shipping logistics, and invoice processing.

## 🏗️ System Architecture

The system is built using a modular architecture with the following main components:

### **User Management System**
- Customer registration and authentication
- Supplier registration and management  
- Employee registration with role-based access
- Login verification for all user types
- Address management

### **Order Processing System**
- Order creation and request management
- Order status tracking (pending, verified, fulfilled, shipped, delivered)
- Order search and retrieval functionality
- Customer order updates
- Payment status management

### **Shipping System**
- Inventory management (new products, cancelled items, returned items)
- Order fulfillment workflow
- Shipping logistics and transport management
- Delivery tracking and confirmation
- Location status updates
- Stock clerk operations

### **Account Payable System**
- Invoice extraction and processing
- Invoice approval workflow
- Supplier invoice management
- Payment tracking

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.4.5
- **Language**: Java 17
- **Database**: MySQL with Spring Data JPA
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Additional Libraries**: Lombok, Spring Validation, Spring Web Services

## 👥 User Roles & Controllers

The system supports multiple user roles with dedicated controllers:

- **HomePageController**: Landing page and navigation
- **CustomerRegistrationController**: Customer onboarding
- **EmployeeController**: Employee management
- **SupplierController**: Supplier operations
- **ManagerController**: Administrative oversight
- **StolkClerkController**: Inventory management
- **FulfilleradminController**: Order fulfillment
- **ShipperController**: Shipping operations
- **RecieverController**: Receiving and verification
- **OrderPSysController**: Order processing management

## 📁 Project Structure

```
src/main/java/
├── com/example/demo/           # Main application and controllers
├── users/                      # User management entities and services
├── shippingSys/                # Shipping system logic
├── orderProcessingSys/         # Order processing components
├── accountpayableSys/          # Invoice and payment management
├── applicationLogic/           # Core business logic
├── businessLogic/              # Business rule implementations
└── repositoryLogic/            # Data access layer

src/main/resources/
├── templates/                  # Thymeleaf HTML templates
│   ├── admin/                  # Admin interface pages
│   └── user/                   # User interface pages
├── static/                     # CSS, JavaScript, and images
│   ├── css/                    # Stylesheets
│   ├── js/                     # Client-side scripts
│   └── images/                 # Static images
└── application.properties      # Application configuration
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL database
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Database Setup
1. Create a MySQL database
2. Configure database credentials in `src/main/resources/application.properties`
3. Run the application to auto-create tables using JPA

### Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🔧 Configuration

Edit `src/main/resources/application.properties` to configure:
- Database connection settings
- Server port
- JPA/Hibernate properties
- Logging configuration

## 📊 Key Features

### Order Workflow
1. **Order Placement**: Customers place orders through the system
2. **Order Verification**: Receiver clerks verify order details
3. **Order Fulfillment**: Fulfillment clerks process and pack orders
4. **Shipping**: Shipper manages logistics and transport
5. **Delivery**: System tracks delivery status and confirmation
6. **Invoicing**: Account payable system handles supplier invoices

### Inventory Management
- Real-time stock tracking
- New product intake
- Cancelled item processing
- Returned product handling
- Stock level monitoring

### User Access Control
- Role-based authentication
- Secure login verification
- Dedicated interfaces for each role
- Session management

## 🤝 Contributing

This project is part of an Object-Oriented Analysis and Design (OOAD) course implementation. The system demonstrates practical application of OOAD principles including:
- Encapsulation through modular design
- Inheritance via entity hierarchies
- Polymorphism in service implementations
- Abstraction through interface-based design

## 📝 License

This project is developed for educational purposes as part of an OOAD course.

## 👨‍💻 Author

**PMWsiwale** - Initial implementation and OOAD design

## 🔗 Repository

https://github.com/PMWsiwale/Shipping-Company-OOAD

---

*This system demonstrates enterprise-level Spring Boot application development with comprehensive business logic for shipping company operations.*
