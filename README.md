# 📚 Library Inventory System

A small ***Java*** console application for managing a library inventory.
Built to practise core Java concepts including OOP, layered architecture, exception handling and the DAO/Service/DTO pattern.

---

## 🎯 Project Overview

This project simulates a basic library system where you can:

- Add new books to the inventory
- Search books by title or genre
- Borrow books (decrements available copies)
- Delete books from the inventory
- List all books in the library

---

## 🏗️ Architecture & Design Patterns

The project follows a **3-layer architecture**:

```
Main (Entry Point)
    ↓
Service Layer (Business Logic)
    ↓
DAO Layer (Data Access)
```

### Layers

**DAO Layer** (`IBookDAO` / `BookDAOImpl`)
- Handles all data access and storage
- Uses an in-memory `HashMap<Long, Book>` as the data store
- Responsible for ID generation via an auto-incrementing counter

**Service Layer** (`IBookService` / `BookServiceImpl`)
- Contains all business logic
- Validates inputs and throws domain exceptions
- Never exposes raw domain objects — always maps to DTOs before returning

**DTOs** (`BookInsertDTO`, `BookReadOnlyDTO`)
- `BookInsertDTO` — used for creating a new book (no ID)
- `BookReadOnlyDTO` — used for all read operations (includes ID)
- Raw `Book` domain objects never leave the service layer

**Mapper** (`Mapper`)
- Static utility class
- Handles all transformations between `Book`, `BookInsertDTO`, and `BookReadOnlyDTO`

### Design Patterns Used

- **DAO Pattern** — separates data access logic from business logic
- **DTO Pattern** — protects the domain model from external exposure
- **Dependency Injection** — `BookServiceImpl` receives `IBookDAO` via constructor
- **Programming to interfaces** — all dependencies are declared as interfaces (`IBookDAO`, `IBookService`)

---

## ▶️ Prerequisites

- Java 17 (developed and tested with this version)
- Any Java IDE (IntelliJ IDEA recommended) 

---

## ⚠️ Known Limitations & Future Improvements

### Current Limitations

- **In-memory storage only** — all data is lost when the application stops. No database or file persistence.
- **Single copy borrow tracking** — borrowing only decrements a counter. There is no record of *who* borrowed a book or *when*.
- **No return book functionality** — once borrowed, a book's copies can only be restored manually.

### Future Improvements

- [ ] Add a `returnBook()` method to increment available copies
- [ ] Persist data with a database (e.g. PostgreSQL via JDBC or Hibernate)
- [ ] Add a `Member` entity to track who borrows which book
- [ ] Add proper logging (e.g. SLF4J) instead of `System.out.printf`
- [ ] Write unit tests with JUnit 5 and Mockito
- [ ] Add a REST API layer with Spring Boot

---

## 📁 Project Structure

```
src/
├── dao/
│   ├── IBookDAO.java
│   └── BookDAOImpl.java
├── service/
│   ├── IBookService.java
│   └── BookServiceImpl.java
├── model/
│   └── Book.java
├── dto/
│   ├── BookInsertDTO.java
│   └── BookReadOnlyDTO.java
├── mapper/
│   └── Mapper.java
├── exceptions/
│   ├── BookAlreadyExists.java
│   ├── BookNotFoundException.java
│   └── BookNotAvailableException.java
└── Main.java
```

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Storage:** In-memory HashMap


