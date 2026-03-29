# Student CRUD API - Spring Boot

This project is a **Spring Boot backend application** that provides REST APIs to manage student data with full **CRUD (Create, Read, Update, Delete)** functionality.

---

## 🚀 Features

* Get all students (with sorting or pagination)
* Get student by ID
* Add new student
* Update existing student
* Delete student with confirmation
* Proper error handling (e.g., student not found)

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* MySQL / H2 Database

---

## ▶️ How to Run the Project

### 1. Clone the Repository

```bash id="k3n1zj"
git clone <your-repo-url>
cd <project-folder>
```

### 2. Build the Project

```bash id="bzx2b6"
mvn clean install
```

### 3. Run the Application

```bash id="p3w6on"
mvn spring-boot:run
```

Application will start at:

```id="i1twls"
http://localhost:8080
```

---

## ⚙️ Configuration

Edit the file:

```id="u4y5l3"
src/main/resources/application.properties
```

### Example (MySQL)

```properties id="w1df93"
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 📡 API Endpoints

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| GET    | /api/all_students      | Get all students  |
| GET    | /api/get_student/{id} | Get student by ID |
| POST   | /api/post_student      | Create student    |
| PUT    | /api/update_student/{id} | Update student    |
| DELETE | /api/delete_student/{id} | Delete student    |

---

## 🧾 Sample Request Body

```json id="t9b8sm"
{
  "studentName": "John Doe",
  "fatherName": "Mr.John",
  "className": "10A"
}
```

---

## ❗ Error Handling

If a student is not found:

```json id="d4o1mq"
{
  "message": "Student with ID {id} is not found"
}
```

---

## 📁 Project Structure

```id="6h5y1z"
src/
 └── main/
     ├── java/
     │   └── com/example/student/
     │       ├── controller/
     │       ├── service/
     │       ├── repository/
     │       └── entity/
     └── resources/
         └── application.properties
```

---

## 🧪 Testing APIs

You can test APIs using:

* Postman
* curl

Example:

```bash id="b0w1dp"
curl http://localhost:8080/api/students
```

---

## 👨‍💻 Author

La Wun

---
