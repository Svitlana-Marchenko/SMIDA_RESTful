# SMIDA RestfulApi

## Main Technologies
- Java 11
- SpringBoot 2.7.18
- Docker
- PostgreSQL
- MongoDB
- Liquibase

## Features
- **CRUD operations** for companies and their reports.
- **Endpoints** for retrieving all reports of a specific company and detailed information about a report.
- **Database migrations** using Liquibase.
- **Logger**: Includes logging info (add an email, send emails, get rate (debug only)) to file `application.log` and console
- **Test**: Includes test `app.test.js`
- **Swagger**: Includes swagger documentation (`http://localhost:8080/swagger-ui/index.html#/`)
- **JWT Token**: Includes jwt token for auth 

# API Documentation

## Company API

### Get All Companies

- **Endpoint:** `GET /api/v1/company`
- **Description:** Retrieve a list of all companies.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    [
      {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "name": "Test Company",
        "registration_number": "123456789",
        "address": "123 Test Street",
        "created_at": "2023-06-09T12:00:00Z"
      }
    ]
    ```
    
### Get Company by ID

- **Endpoint:** `GET /api/v1/company/{id}`
- **Description:** Retrieve a company by its ID.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "name": "Test Company",
      "registration_number": "123456789",
      "address": "123 Test Street",
      "created_at": "2023-06-09T12:00:00Z"
    }
    ```
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Company not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

### Create Company

- **Endpoint:** `POST /api/v1/company`
- **Description:** Create a new company.
- **Request Body:**
  ```json
  {
    "name": "Test Company",
    "registration_number": "123456789",
    "address": "123 Test Street"
  }
- **Response:**
  - Status: `201 CREATED`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "name": "Test Company",
      "registration_number": "123456789",
      "address": "123 Test Street",
      "created_at": "2023-06-09T12:00:00Z"
    }
    ```

### Update Company

- **Endpoint:** `PUT /api/v1/company/{id}`
- **Description:** Update a new company.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the company to update.
- **Request Body:**
  ```json
  {
    "name": "Test Company",
    "registration_number": "123456789",
    "address": "123 Test Street"
  }
  ```
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "name": "Updated Test Company",
      "registration_number": "987654321",
      "address": "456 Updated Street",
      "created_at": "2023-06-09T12:00:00Z"
    }
 - Error Response: `404 Not Found`
    ```json
    {
      "error": "Company not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```
    
### Delete Company

- **Endpoint:** `DELETE /api/v1/company/{id}`
- **Description:** Delete a company by its ID.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the company to delete.
- **Response:**
  - Status: `204 No Content`
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Company not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

## Report API

### Get All Reports

- **Endpoint:** `GET /api/v1/report`
- **Description:** Retrieve a list of all reports.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    [
      {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "company": {
          "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
          "name": "Test Company",
          "registration_number": "123456789",
          "address": "123 Test Street",
          "created_at": "2023-06-09T12:00:00Z"
        },
        "report_date": "2023-06-09T12:00:00Z",
        "total_revenue": 1000000.00,
        "net_profit": 200000.00
      }
    ]
    ```

### Get Report by ID

- **Endpoint:** `GET /api/v1/report/{id}`
- **Description:** Retrieve a report by its ID.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "company": {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "name": "Test Company",
        "registration_number": "123456789",
        "address": "123 Test Street",
        "created_at": "2023-06-09T12:00:00Z"
      },
      "report_date": "2023-06-09T12:00:00Z",
      "total_revenue": 1000000.00,
      "net_profit": 200000.00
    }
    ```
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Report not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

### Get Reports by Company ID

- **Endpoint:** `GET /api/v1/report/company/{companyId}`
- **Description:** Retrieve all reports for a specific company.
- **Path Parameters:**
  - `companyId` (UUID): The unique identifier of the company.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    [
      {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "company": {
          "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
          "name": "Test Company",
          "registration_number": "123456789",
          "address": "123 Test Street",
          "created_at": "2023-06-09T12:00:00Z"
        },
        "report_date": "2023-06-09T12:00:00Z",
        "total_revenue": 1000000.00,
        "net_profit": 200000.00
      }
    ]
    ```

### Create Report

- **Endpoint:** `POST /api/v1/report`
- **Description:** Create a new report.
- **Request Body:**
  ```json
  {
    "company": {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    },
    "report_date": "2023-06-09T12:00:00Z",
    "total_revenue": 1000000.00,
    "net_profit": 200000.00
  }
- **Response:**
  - Status: `201 CREATED`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "company": {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "name": "Test Company",
        "registration_number": "123456789",
        "address": "123 Test Street",
        "created_at": "2023-06-09T12:00:00Z"
      },
      "report_date": "2023-06-09T12:00:00Z",
      "total_revenue": 1000000.00,
      "net_profit": 200000.00
    }
    ```

### Update Report

- **Endpoint:** `PUT /api/v1/report/{id}`
- **Description:** Update an existing report.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report to update.
- **Request Body:**
  ```json
  {
    "company": {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    },
    "report_date": "2023-06-09T12:00:00Z",
    "total_revenue": 1500000.00,
    "net_profit": 300000.00
  }
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "company": {
        "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "name": "Test Company",
        "registration_number": "123456789",
        "address": "123 Test Street",
        "created_at": "2023-06-09T12:00:00Z"
      },
      "report_date": "2023-06-09T12:00:00Z",
      "total_revenue": 1500000.00,
      "net_profit": 300000.00
    }
  ```
 - Error Response: `404 Not Found`
    ```json
    {
      "error": "Report not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

### Delete Report

- **Endpoint:** `DELETE /api/v1/report/{id}`
- **Description:** Delete a report by its ID.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report to delete.
- **Response:**
  - Status: `204 No Content`
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Report not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```


## Report Details API

### Get All Report Details

- **Endpoint:** `GET /api/v1/report-details`
- **Description:** Retrieve a list of all report details.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    [
      {
        "report_id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
        "financial_data": { "revenue": 1000000, "profit": 200000 },
        "comments": "Annual report for 2023"
      }
    ]
    ```

### Get Report Details by ID

- **Endpoint:** `GET /api/v1/report-details/{id}`
- **Description:** Retrieve report details by its ID.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report details.
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "report_id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "financial_data": { "revenue": 1000000, "profit": 200000 },
      "comments": "Annual report for 2023"
    }
    ```
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Report details not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

### Create Report Details

- **Endpoint:** `POST /api/v1/report-details`
- **Description:** Create new report details.
- **Request Body:**
  ```json
  {
    "report_id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
    "financial_data": { "revenue": 1000000, "profit": 200000 },
    "comments": "Annual report for 2023"
  }
  ```
- **Response:**
  - Status: `201 CREATED`
  - Body:
    ```json
    {
      "report_id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "financial_data": { "revenue": 1000000, "profit": 200000 },
      "comments": "Annual report for 2023"
    }
  ```
 - Error Response: `500 Server error`
    ```json
    {
      "error": "Report with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1 does not exist"
    }
    ```

### Update Report Details

- **Endpoint:** `PUT /api/v1/report-details/{id}`
- **Description:** Update existing report details.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report details to update.
- **Request Body:**
  ```json
  {
    "financial_data": { "revenue": 1500000, "profit": 300000 },
    "comments": "Updated annual report for 2023"
  }
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "report_id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "financial_data": { "revenue": 1000000, "profit": 200000 },
      "comments": "Annual report for 2023"
    }
  ```
 - Error Response: `500 Server error`
    ```json
    {
      "error": "Report with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1 does not exist"
    }
    ```

### Delete Report Details

- **Endpoint:** `DELETE /api/v1/report-details/{id}`
- **Description:** Delete report details by its ID.
- **Path Parameters:**
  - `id` (UUID): The unique identifier of the report details to delete.
- **Response:**
  - Status: `204 No Content`
  - Error Response: `404 Not Found`
    ```json
    {
      "error": "Report details not found with id 67d23f69-5c15-435d-aebf-8bf83fe4f3d1"
    }
    ```

## Authentication API

### Register User

- **Endpoint:** `POST /api/v1/auth/register`
- **Description:** Register a new user.
- **Request Body:**
  ```json
  {
    "username": "john",
    "password": "password123",
    "role": "ADMIN"
  }
- **Response:**
  - Status: `201 CREATED`
  - Body:
    ```json
    {
      "id": "67d23f69-5c15-435d-aebf-8bf83fe4f3d1",
      "username": "John",
      "password": "password123",
      "role": "ADMIN"
    }
    ```
  - Error Response: `400 Bad Request`
    ```json
    {
      "error": "Email already in use"
    }
    ```  

### Login User

- **Endpoint:** `POST /api/v1/auth/login`
- **Description:** Authenticate a user and return a JWT token.
- **Request Body:**
  ```json
  {
    "username": "john",
    "password": "password123"
  }
- **Response:**
  - Status: `200 OK`
  - Body:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```
  - Error Response: `500`
 
## Run
1) Clone the project
2) Start postgresql and mongodb data bases in docker and start the app
