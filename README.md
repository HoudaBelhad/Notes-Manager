# 📝 Notes Management API


![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot&logoColor=white)
![Tests](https://img.shields.io/badge/Tests-JUnit%205%20Passed-brightgreen)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?logo=h2database)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-orange?logo=swagger)

A clean and simple **RESTful Notes Management API** built with **Spring Boot**, following best practices (DTOs, Mapper, Service layer, Global Exception Handling, Swagger documentation).

This project is designed for learning purposes, portfolio showcase, and as a solid backend foundation for a full-stack application.

---

## 🚀 Features

* 🆕 Create, read, update, and delete notes
* 📅 Filter notes by creation date (startDate / endDate)
* 📦 DTO-based architecture (Request / Response / Filter)
* 🛠️ Clean service and repository layers
* ⚠️ Global exception handling
* 📄 Swagger / OpenAPI documentation
* 🧪 Unit tests for service layer (JUnit 5 + Mockito)
* 🌐 Ready to be connected to a modern frontend UI

---

## 🛠️ Tech Stack

* ☕ **Java 17**
* 🌱 **Spring Boot**
* 🌐 **Spring Web**
* 🗃️ **Spring Data JPA**
* 🐘 **Hibernate**
* 🗄️ **H2**
* 🧩 **Lombok**
* 🧪 **JUnit 5 / Mockito**
* 📜 **Swagger (springdoc-openapi)**

---

## 📂 Project Structure

```
src/main/java/com/example/hbdev/notes
│
├── controller        # REST controllers
├── service
│   ├── impl          # Service implementations
├── repository        # JPA repositories
├── entity            # JPA entities
├── dto               # Request, Response, Filter DTOs
├── mapper            # Entity ↔ DTO mappers
├── exception         # Custom exceptions & global handler
└── config            # Swagger configuration
```

---

## 📌 API Endpoints

### Create a note

```
POST /notes
```

**Request body**

```json
{
  "title": "My first note",
  "content": "This is the content of the note"
}
```

---

### Get all notes (with optional date filter)

```
GET /notes
```

**Optional query parameters**

* `startDate` (ISO LocalDateTime)
* `endDate` (ISO LocalDateTime)

Example:

```
/notes?startDate=2024-01-01T00:00:00&endDate=2024-12-31T23:59:59
```

---

### Get note by ID

```
GET /notes/{id}
```

---

### Update a note

```
PUT /notes/{id}
```

**Request body**

```json
{
  "title": "Updated title",
  "content": "Updated content"
}
```

---

### Delete a note

```
DELETE /notes/{id}
```

---

## ❗ Error Handling

The API uses a **global exception handler** to return meaningful HTTP responses.

* `404 NOT FOUND` → Note not found
* `400 BAD REQUEST` → Invalid input
* `500 INTERNAL SERVER ERROR` → Unexpected error

Example error response:

```json
{
  "message": "Note not found with id: 10"
}
```

---

## 📖 Swagger Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

Use it to explore and test all endpoints interactively.

---

## 🧪 Database

* Default: **H2 (in-memory)** for local development
* Can be easily switched to **PostgreSQL / MySQL**

H2 Console:

```
http://localhost:8080/h2-console
```

---

## 🔗 Frontend Integration

This API is designed to be consumed by a modern frontend (React, Next.js, etc.).

Example use cases:

* 🗒️ Notes dashboard
* 📅 Date-based filtering
* 🔄 CRUD operations via REST API

---

## 📜 License

This project is licensed under the **MIT License**.

```
MIT License

Copyright (c) 2025 Houda Belhad
```

---

## 👩‍💻 Author

**Houda Belhad**
Backend Developer (Java / Spring Boot)

* GitHub: [https://github.com/your-username](https://github.com/your-username)
* Email: [houdabelhad6@gmail.com](mailto:houdabelhad6@gmail.com)


