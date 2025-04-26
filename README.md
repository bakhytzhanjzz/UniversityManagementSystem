# University Management System (UMS)

A simple and clean RESTful API for managing students, courses, and enrollments in a university.  
Built with Java Spring Boot, PostgreSQL, and Docker to demonstrate backend development skills, clean architecture, and best practices.

This project was created as part of a technical portfolio to showcase real-world coding skills, software design patterns, and backend infrastructure setup.

---

## How to Run the Project

Follow these steps to run the University Management System locally:

### 1. Prerequisites
- Java 21 installed
- Maven installed
- Docker Desktop installed and running

### 2. Clone the Repository
```bash
git clone https://github.com/bakhytzhanjzz/UniversityManagementSystem
cd UniversityManagementSystem
```

### 3. Start PostgreSQL with Docker
Run the following command to start a PostgreSQL container:
```bash
docker run --name ums_postgres -e POSTGRES_DB=ums_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:latest
```

Make sure port 5432 is not already used on your computer.

### 4. Configure the Database Connection (Optional)
The project is already preconfigured to connect to:

Host: localhost

Port: 5432

Database: ums

Username: postgres

Password: 1234

If needed, you can update the database connection settings in src/main/resources/application.properties

### 5. Build and Run the Project
Build the project using Maven:
```bash
mvn clean install
```
Then run the application:
```bash
mvn spring-boot:run
```
The server will start on:
http://localhost:8080

### 6. Access the API
You can now interact with the API using tools like Postman, Insomnia, or simply your browser!

Example:

Get all students: GET http://localhost:8080/api/students

Create a new course: POST http://localhost:8080/api/courses

---

## Project Structure Overview

The University Management System follows a clean, layered Spring Boot architecture:

### Key Layers Explained:
- **Controller**: Handles incoming HTTP requests, maps them to appropriate services.
- **DTO**: Data Transfer Objects used to transfer data between the client and server.
- **Model**: JPA Entities that map to database tables.
- **Repository**: Handles database CRUD operations via Spring Data JPA.
- **Service**: Contains the core business logic of the application.
- **Mapper**: Used to map between internal model objects and DTOs.
- **Security**: Holds security configurations, such as JWT-based authentication and role-based access control.
- **Config**: Contains application-wide configurations.
- **Exception**: Custom exception handling for catching errors globally.

---

### Example Flow (Request Lifecycle):
1. **Client** → Sends an HTTP request (e.g., POST /students) to the **Controller**.
2. **Controller** → The controller method parses the request and calls the appropriate **Service** method.
3. **Service** → The service layer contains the core business logic, like creating or updating a student, which then interacts with the **Repository**.
4. **Repository** → The repository interacts with the database to save, retrieve, or update data.
5. **Response** → After the database operation, the service returns the result to the controller, which then sends the appropriate response back to the client.

---

## Example API Endpoints

### Authorization Overview
To access **admin-only** endpoints (e.g., creating, updating, or deleting students or courses), you must authenticate as an admin. Upon successful login, you will receive a **JWT token**, which must be passed as a Bearer token in the Authorization header for subsequent requests.

**Students** can only access their own data and are restricted from performing actions like creating, updating, or deleting other students or courses.

### Example API Endpoints
1. POST http://localhost:8080/auth/register

- Description: Register a new user (both students and admins).

- Request Body:

```
{
    "firstName": "Aman",
    "lastName": "Rakhmetov",
    "email": "aman@example.com",
    "password": "securepassword123",
    "role": "STUDENT" // or "ADMIN" for admin users
}
```

2. POST http://localhost:8080/auth/login

- Description: Log in and receive a JWT token.

- Request Body:

```
{
    "email": "aman@example.com",
    "password": "securepassword123"
}
```
- Response:
```
{
    "token": "jwt_token_here"
}
```

3. GET http://localhost:8080/api/students

- Description: Get a list of all students (Admin only).

- Authorization: Bearer token with ADMIN role required.

- Request Header:

```
Authorization: Bearer jwt_token_here
```
- Response:
```
[
    {
        "id": "bf418281-220d-4ede-8281-6b5e41a756fc",
        "firstName": "Daniel",
        "lastName": "Li",
        "email": "danik@gmail.com",
        "dateOfBirth": null,
        "user": {
            "id": "13dddfee-4503-447b-a464-82272ead60f1",
            "firstName": "Daniel",
            "lastName": "Li",
            "email": "danik@gmail.com",
            "password": "$2a$10$5NH/j2SM2Ejzme/U5gtsCunfCtOUWc9WNiYTWLTlpcD4jElUVkWMa",
            "role": "STUDENT",
            "createdAt": "2025-04-26T15:01:31.691419",
            "updatedAt": "2025-04-26T15:01:31.691419"
        }
    },
    {
        "id": "9233c582-7808-4570-9b41-3675ac04c87d",
        "firstName": "Aman",
        "lastName": "Rakhmetov",
        "email": "aman@example.com",
        "dateOfBirth": null,
        "user": {
            "id": "7596b76e-5bde-409e-adf3-fbfd66109557",
            "firstName": "Aman",
            "lastName": "Rakhmetov",
            "email": "aman@example.com",
            "password": "$2a$10$fLi/P6okd7xmo7gGboEJNeCeqTvmOTbHEXrpcz9o6nTBFtRg0pRB.",
            "role": "STUDENT",
            "createdAt": "2025-04-26T19:48:54.98724",
            "updatedAt": "2025-04-26T19:48:54.98724"
        }
    }
]
```
4. GET http://localhost:8080/api/students/9233c582-7808-4570-9b41-3675ac04c87d

- Description: Get details of a specific student by ID (Students can access their own data, Admins can access any student’s data).

- Authorization: Bearer token required.

- Response:

```
{
    "id": "9233c582-7808-4570-9b41-3675ac04c87d",
    "firstName": "Aman",
    "lastName": "Rakhmetov",
    "email": "aman@example.com",
    "dateOfBirth": null,
    "user": {
        "id": "7596b76e-5bde-409e-adf3-fbfd66109557",
        "firstName": "Aman",
        "lastName": "Rakhmetov",
        "email": "aman@example.com",
        "password": "$2a$10$fLi/P6okd7xmo7gGboEJNeCeqTvmOTbHEXrpcz9o6nTBFtRg0pRB.",
        "role": "STUDENT",
        "createdAt": "2025-04-26T19:48:54.98724",
        "updatedAt": "2025-04-26T19:48:54.98724"
    }
}
```

5. POST http://localhost:8080/api/courses

- Description: Create a new course (Admin only).

- Authorization: Bearer token with ADMIN role required.

- Request Body:
```
{
    "name": "Mathematics 101",
    "description": "Basic mathematics course",
    "credits": 3
}
```
- Response:
```
{
    "id": "ce271541-a2b9-418a-8096-11fe35a9e311",
    "name": "Mathematics 101",
    "description": "Basic mathematics course",
    "credits": 3
}
```
- Error: 403 Forbidden if not authorized as an admin.

7. DELETE /api/courses/{id}

- Description: Delete a course (Admin only).

- Authorization: Bearer token with ADMIN role required.

- Response:
```
{
    "message": "Course deleted successfully"
}
```

---

## Access Control Summary:
### Admins can:

- Access all endpoints, including creating, updating, and deleting students and courses.

- Retrieve details of any student or course.

## Students can:

- Register and log in.

- View and update their own student details.

- Access a list of available courses but cannot modify or delete any.

- Cannot create, update, or delete students or courses.

--- 

## Used Technologies
This project uses a modern and professional technology stack to ensure scalability, security, and clean architecture.

### Backend
**Java 21**
→ Latest LTS version of Java, bringing improved performance and language features.

**Spring Boot 3**
→ Rapid backend development framework with built-in support for security, validation, dependency injection, etc.

**Spring Security 6**
→ Handles authentication and authorization using JWT (JSON Web Tokens).

**Spring Data JPA**
→ Simplifies database interactions with an object-relational mapping approach.

**Jakarta Persistence API (JPA)**
→ Used for entity management and database operations.

**Maven**
→ Project management and build tool.

**PostgreSQL**
→ Powerful and open-source relational database system.

**Docker**
→ Containerization tool to easily set up the database environment with consistent configurations.

### Libraries & Tools
**JWT** (JSON Web Token) for stateless authentication.

**Lombok** to reduce boilerplate code (getters, setters, constructors, etc.).

**ModelMapper** to automatically map between DTOs and Entities.

**Validation API** (Jakarta Validation) to enforce data integrity.

**HikariCP** for efficient database connection pooling.

### Best Practices Applied
- Layered architecture (Controller → Service → Repository).

- DTO and Factory patterns for clean separation between database models and API exposure.

- Global exception handling for consistent error responses.

- Role-based access control (RBAC) to secure endpoints.

- Dockerized database for easy setup and deployment.

--- 

## Contact Information

Github page: https://github.com/bakhytzhanjzz

Phone number: +7 708 734 1235

Email: bakhytzhanabdilmazhit@gmail.com

Telegram: @bakhtzhn
