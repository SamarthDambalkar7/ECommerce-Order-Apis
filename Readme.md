E-Commerce Application

Overview
The E-Commerce Application is a Spring Boot-based application built using Java 17+. It integrates with a MySQL database
using Spring Data JPA and exposes a REST API for various operations related to orders and order items.

Requirements:

Java 17+
Maven 3.1.5
MySQL 8.0
Docker
IDE: Eclipse or Vscode or Intellij IDEA or any supporting one.
An active Internet Connection

Getting Started:

1) Cloning the Repository:

git clone https://github.com/SamarthDambalkar7/ECommerce-Order-Apis.git
cd ECommerce-Order-Apis
a) Running with Docker

Make sure you have Docker installed.
You need to pull mysql:latest image on your local docker
Navigate to the project root directory.
bash
docker-compose up
The application will be accessible at http://localhost:8080.

Building and Running Locally
bash
Copy code
./mvnw clean install
./mvnw spring-boot:run
The application will be accessible at http://localhost:8080.

REST API
Order API
GET /api/orders: Get a list of all orders.
GET /api/orders/{id}: Get an order by ID.
POST /api/orders: Create a new order.
PUT /api/orders/{id}: Update an existing order.
DELETE /api/orders/{id}: Delete an order by ID.
OrderItem API
GET /api/order-items: Get a list of all order items.
GET /api/order-items/{id}: Get an order item by ID.
POST /api/order-items: Create a new order item.
PUT /api/order-items/{id}: Update an existing order item.
DELETE /api/order-items/{id}: Delete an order item by ID.

OpenAPI (Swagger)
The API documentation is available using Swagger at http://localhost:8080/swagger-ui.html.

Docker
Docker images for the application are available on Docker Hub.

Application Image: samarthdambalkar7/e-commerce-app:latest
Database Image: mysql:latest
To run the application with Docker:

