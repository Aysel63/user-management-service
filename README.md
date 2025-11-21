# User Management Backend Service

A production-ready user management microservice built with **Spring Boot 3**, **Java 21**, **PostgreSQL**, and **Docker**. Designed with clean code principles, comprehensive error handling, and professional logging.

## Features

- ✅ Create, Read, Update, Delete (CRUD) users
- ✅ User roles (USER, ADMIN) and phone prefixes (Azercell, Bakcell, etc.)
- ✅ Structured error handling with HTTP status codes
- ✅ Comprehensive logging and monitoring
- ✅ PostgreSQL database with Hibernate ORM
- ✅ Docker support
- ✅ Environment-based configuration
- ✅ Request/response validation
- ✅ RESTful API design

## Tech Stack

- **Framework**: Spring Boot 3.5.7
- **Language**: Java 21
- **Database**: PostgreSQL 15
- **Container**: Docker
- **Build Tool**: Maven 3.9
- **ORM**: Hibernate/JPA
- **Validation**: Jakarta Bean Validation

## Project Structure

```
user-management-service/
├── src/main/java/com/ayselabdulzade/usermanagementservice/
│   ├── controller/
│   │   └── UserController.java
│   ├── service/
│   │   └── UserService.java
│   ├── dao/
│   │   └── UserRepository.java
│   ├── entity/
│   │   └── UserEntity.java
│   ├── dto/
│   │   ├── CreateUserRequest.java
│   │   ├── UserResponse.java
│   │   └── ErrorResponse.java
│   ├── enums/
│   │   ├── Role.java
│   │   └── PhonePrefix.java
│   ├── exception/
│   │   ├── ResourceNotFoundException.java
│   │   ├── DuplicateEmailException.java
│   └── UserManagementServiceApplication.java
├── src/main/resources/
│   └── application.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## Quick Start

### Prerequisites

- Docker installed
- Git
- Java 21+ (for local development)
- Maven 3.8+ (for local development)

### Run Locally (Without Docker)

1. **Clone the repository**
   ```bash
   git clone https://github.com/Aysel63/user-management-service.git
   cd user-management-service
   ```

2. **Configure PostgreSQL**
   - Ensure PostgreSQL is running on your machine
   - Create database: `createdb userdb`
   - Set environment variables:
     ```bash
     export DB_HOST=localhost
     export DB_USER=postgres
     export DB_PASSWORD=your_password
     ```

3. **Build and run**
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

4. **Access the API**
   ```bash
   curl http://localhost:8080/api/v1/users
   ```

## Configuration

Environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| DB_HOST | localhost | PostgreSQL host |
| DB_PORT | 5432 | PostgreSQL port |
| DB_NAME | userdb | Database name |
| DB_USER | postgres | Database user |
| DB_PASSWORD | postgres | Database password |
| SERVER_PORT | 8080 | App port |
| SPRING_PROFILES_ACTIVE | dev | Active profile (dev/prod) |

## API Endpoints

### Base URL
```
http://localhost:8080/api/v1/users
```
### Deployed URL
```
https://user-management-service-fmkb.onrender.com/api/v1/users
```
### 1. Create User
**POST** `/api/v1/users`

Request:
```json
{
  "name": "Aysel Abdulzade",
  "email": "aysel@example.com",
  "phoneNumber": "501234567",
  "phonePrefix": "_050",
  "role": "ADMIN"
}
```

Response (201 Created):
```json
{
  "id": 1,
  "name": "Aysel Abdulzade",
  "email": "aysel@example.com",
  "phoneNumber": "501234567",
  "phonePrefix": "_050",
  "role": "ADMIN",
  "createdAt": "2025-11-21T07:30:00",
  "updatedAt": "2025-11-21T07:30:00"
}
```

### 2. Get User by ID
**GET** `/api/v1/users/{id}`

Example:
```bash
curl http://localhost:8080/api/v1/users/1
```

### 3. List All Users
**GET** `/api/v1/users`

Example:
```bash
curl http://localhost:8080/api/v1/users
```

### 4. Update User
**PUT** `/api/v1/users/{id}`

Request:
```json
{
  "name": "Aysel Updated",
  "email": "aysel.updated@example.com",
  "phoneNumber": "502345678",
  "phonePrefix": "_055",
  "role": "USER"
}
```

### 5. Delete User
**DELETE** `/api/v1/users/{id}`

Example:
```bash
curl -X DELETE http://localhost:8080/api/v1/users/1
```

## Error Handling

### 400 Bad Request
```json
{
  "timestamp": "2025-11-21T07:30:00",
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "email: Invalid email format"
}
```

### 404 Not Found
```json
{
  "timestamp": "2025-11-21T07:30:00",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "User not found with ID: 999"
}
```

### 409 Conflict
```json
{
  "timestamp": "2025-11-21T07:30:00",
  "status": 409,
  "error": "CONFLICT",
  "message": "User with email aysel@example.com already exists"
}
```

### 500 Internal Server Error
```json
{
  "timestamp": "2025-11-21T07:30:00",
  "status": 500,
  "error": "INTERNAL_SERVER_ERROR",
  "message": "An unexpected error occurred"
}
```

## Validation Rules

- **Name**: Required, non-empty string
- **Email**: Required, valid email format, unique
- **Phone Number**: Required, 7-10 digits
- **Phone Prefix**: Required, one of: _050, _055, _051, _070, _077, _099, _010
- **Role**: Optional, defaults to USER (USER or ADMIN)

## Available Phone Prefixes

| Prefix | Operator |
|--------|----------|
| _050 | Azercell |
| _055 | Bakcell |
| _051 | Azercell| |
| _070 | Nar |
| _077 | Nar|
| _099 | Bakcell |
| _010 | Bakcell |

## Available Roles

- **USER**: Regular user
- **ADMIN**: Administrator

## Testing with Postman

### Create User
- Method: POST
- URL: `http://localhost:8080/api/v1/users`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "name": "Test User",
  "email": "test@example.com",
  "phoneNumber": "501234567",
  "phonePrefix": "_050",
  "role": "USER"
}
```

### Get All Users
- Method: GET
- URL: `http://localhost:8080/api/v1/users`

### Get User by ID
- Method: GET
- URL: `http://localhost:8080/api/v1/users/1`

### Update User
- Method: PUT
- URL: `http://localhost:8080/api/v1/users/1`

### Delete User
- Method: DELETE
- URL: `http://localhost:8080/api/v1/users/1`

## Development

### Build
```bash
mvn clean package
```

### Run tests
```bash
mvn test
```

### Run locally
```bash
mvn spring-boot:run
```

### Skip tests during build
```bash
mvn clean package -DskipTests
```

## Deployment

### Deploy to Render

1. Push code to GitHub
2. Visit [render.com](https://render.com)
3. Create new Web Service
4. Connect GitHub repository
5. Set environment variables:
   ```
   DB_HOST=dpg-d4g5t3re5dus739kiidg-a
   DB_USER=userdb_8jt4_user
   DB_PASSWORD=<your password>
   DB_NAME=userdb_8jt4
   SERVER_PORT=10000
   SPRING_PROFILES_ACTIVE=prod
   ```
6. Deploy
7. Verify deployment:
   https://user-management-service-fmkb.onrender.com/api/v1/users

### Troubleshooting

**Connection refused to PostgreSQL**
- Check PostgreSQL is running
- Verify host and port settings
- Check credentials

**Port already in use**
- Change `SERVER_PORT` environment variable
- Kill process using port 8080

**Database table doesn't exist**
- Check `application.yml`: `ddl-auto: create`
- Restart application

## Architecture

### Layers

1. **Controller**: HTTP request/response handling
2. **Service**: Business logic
3. **Repository**: Data access (JPA)
4. **Entity**: Database model
5. **DTO**: Data transfer objects
6. **Exception**: Error handling


---
