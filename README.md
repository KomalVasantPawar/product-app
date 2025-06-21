# Quarkus Reactive Product Management App

This project is a reactive REST API built using [Quarkus](https://quarkus.io) to manage product data using CRUD operations with PostgreSQL.

## Tech Stack
- Quarkus (Reactive)
- Hibernate Reactive with Panache
- Mutiny (Reactive programming)
- PostgreSQL
- Maven
- JUnit 5 & RestAssured (for testing)

## Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven
- PostgreSQL running with a database `productdb`

### 2. Database Config

### 3. API Endpoints
POST	/products	Create product
Method	Endpoint	Description
POST	/products	Create product
GET	/products	Get all products
GET	/products/{id}	Get product by ID
PUT	/products/{id}	Update product
DELETE	/products/{id}	Delete product
GET	/products/{id}/availability?count=5	Check stock
GET	/products/sorted	Get sorted by price


## Test Results
./mvnw test

## Tests Covered:

Create product (POST)

Response structure

Status code validation

## Run Project
./mvnw compile quarkus:dev
