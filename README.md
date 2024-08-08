# Library Management System

## Overview

The Library Management System is a Spring Boot application designed to manage a library's books, patrons, and borrowing records.
It provides a RESTful API to interact with the system, allowing for the management of books, patrons, and tracking of borrowing activities .

## Features

- **Book Management**: Add, update, retrieve, and delete books.
- **Patron Management**: Add, update, retrieve, and delete patrons.
- **Borrowing Records**: Record the borrowing and returning of books.

- 

## Technology Stack

- **Backend**: Spring Boot
- **Database**: H2 Database (in-memory for development, replace with your choice in production)
- **Java Version**: 21.0.1

## Getting Started

### Prerequisites

- Java 21.0.1
- Apache Maven 3.9.8

### Clone the Repository


git clone https://github.com/yourusername/library-management-system.git
cd library-management-system

### Build and Run the Application

1) Build the project: mvn clean install
The application will start on http://localhost:8080.


# Library Management System API Endpoints

## Book Management

### Retrieve All Books
- **Endpoint**: `GET /api/books`
- **Description**: Retrieves a list of all books in the library.
- **Response**:
  - **Status Code**: 200 OK
  - **Content-Type**: `application/json`
  - **Response Body**:
    ```json
    [
      {
        "id": 1,
        "title": "Effective Java",
        "author": "Joshua Bloch",
        "publicationYear": 2018,
        "isbn": "978-0134685991"
      }
    ]
    ```

### Retrieve Book by ID
- **Endpoint**: `GET /api/books/{id}`
- **Description**: Retrieves details of a specific book by ID.
- **Response**:
  - **Status Code**: 200 OK
  - **Content-Type**: `application/json`
  - **Response Body**:
    ```json
    {
      "id": 1,
      "title": "Effective Java",
      "author": "Joshua Bloch",
      "publicationYear": 2018,
      "isbn": "978-0134685991"
    }
    ```
  - **Status Code**: 404 Not Found (if book does not exist)

### Add a New Book
- **Endpoint**: `POST /api/books`
- **Description**: Adds a new book to the library.
- **Request Body**:
  ```json
  {
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "publicationYear": 2008,
    "isbn": "978-0132350884"
  }

### Update an Existing Book
- **Endpoint**: 'PUT /api/books/{id}'
- **Description**: Updates information for an existing book.
-**Request Body**:
  ```json
  {
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "publicationYear": 2019,
  "isbn": "978-0132350884"
}
### Delete a Book
- **Endpoint**: DELETE /api/books/{id}
- **Description**: Removes a book from the library.
- **Response**:
Status Code: 204 No Content
Status Code: 404 Not Found (if book does not exist)


### Patron Management
- ## Retrieve All Patrons
- **Endpoint**: GET /api/patrons
- **Description**: Retrieves a list of all patrons.
- **Response** :
Status Code: 200 OK
Content-Type: application/json


### Retrieve Patron by ID
- **Endpoint**: GET /api/patrons/{id}
- **Description**: Retrieves details of a specific patron by ID.
- **Response**:
Status Code: 200 OK
Content-Type: application/json

### Add a New Patron
- **Endpoint**: POST /api/patrons
- **Description**: Adds a new patron to the system.
- - **Request Body:**
  - {
  "name": "Jane Smith",
  "contactInfo": "jane.smith@example.com"

}

### Update an Existing Patron
-**Endpoint**: PUT /api/patrons/{id}
- **Description**: Updates information for an existing patron.

- **Request Body**:
- {
  "name": "Jane Smith",
  "contactInfo": "jane.smith@newdomain.com"

}

### Delete a Patron
- **Endpoint**: DELETE /api/patrons/{id}
- **Description**: Removes a patron from the system.

### Borrowing Records
## Borrow a Book
- **Endpoint**: POST /api/borrow/{bookId}/patron/{patronId}
- **Description**: Allows a patron to borrow a book.
- **Response**:
- **Status Code**: 200 OK
- **Content-Type**: application/json
- **Response Body**:
- {
  "id": 1,
  "borrowDate": "2024-08-08",
  "returnDate": null,
  "bookId": 5,
  "bookTitle": "Clean Code",
  "patronId": 4,
  "patronName": "Jane Smith"

}

### Return a Book
- **Endpoint**: PUT /api/return/{bookId}/patron/{patronId}
- **Description**: Records the return of a borrowed book by a patron.
- **Response**:
- **Status Code**: 200 OK
- **Content-Type**: application/json
- **Response Body**:
- {
  "id": 1,
  "borrowDate": "2024-08-08",
  "returnDate": "2024-08-10",
  "bookId": 5,
  "bookTitle": "Clean Code",
  "patronId": 4,
  "patronName": "Jane Smith"

}


### Retrieve All Borrowing Records
- **Endpoint**: GET /api/borrowing-records
- **Description**: Retrieves a list of all borrowing records.
- **Response**:
- **Status Code**: 200 OK
- **Content-Type**: application/json
- **Response Body**:
- [
  {
    "id": 1,
    "borrowDate": "2024-08-08",
    "returnDate": null,
    "bookId": 5,
    "bookTitle": "Clean Code",
    "patronId": 4,
    "patronName": "Jane Smith"
  }
]



