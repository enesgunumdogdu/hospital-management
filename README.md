# Hospital Management System

A full-stack hospital management application built with Spring Boot and Vue.js, featuring complete CRUD operations for doctors, patients, examinations, and prescriptions.

## 🏥 Project Overview

This project implements a hospital management system with a RESTful API backend and modern SPA frontend. The system follows clean architecture principles with layered design and provides comprehensive management capabilities for hospital operations.

## 🚀 Key Features

- **Complete CRUD Operations** for all entities (Doctor, Patient, Examination, Prescription)
- **RESTful API** with clean endpoint structure
- **Modern Frontend** with Vue.js 3 and responsive design
- **Database Integration** with PostgreSQL and JPA/Hibernate
- **Clean Architecture** with DTO pattern and MapStruct mapping

## 🛠️ Technology Stack

**Backend:** Java 21, Spring Boot 3.5.6, Spring Data JPA, Hibernate, PostgreSQL, Maven, MapStruct  
**Frontend:** Vue.js 3.5.22, Vite, Axios, CSS3  
**Database:** PostgreSQL (Production), H2 (Testing)

## 📋 Prerequisites

- **Java 21** or higher
- **Node.js 16** or higher  
- **PostgreSQL 12** or higher
- **Maven 3.6** or higher

## 🚀 Quick Start

### Backend
```bash
# Clone and navigate to project
git clone <repository-url>
cd hospital-management

# Configure PostgreSQL database
# Update src/main/resources/application.properties

# Run the application
mvn clean install
mvn spring-boot:run
```

### Frontend
```bash
# Navigate to frontend directory
cd frontend-hospital-management

# Install dependencies and start
npm install
npm run dev
```

**Backend:** `http://localhost:8080`  
**Frontend:** `http://localhost:5173`

## 📚 API Endpoints

**Base URL:** `http://localhost:8080/api`

| Entity | GET | POST | PUT | DELETE |
|--------|-----|------|-----|--------|
| Doctors | `/doctors` | `/doctors` | `/doctors/{id}` | `/doctors/{id}` |
| Patients | `/patients` | `/patients` | `/patients/{id}` | `/patients/{id}` |
| Examinations | `/examinations` | `/examinations` | `/examinations/{id}` | `/examinations/{id}` |
| Prescriptions | `/prescriptions` | `/prescriptions` | `/prescriptions/{id}` | `/prescriptions/{id}` |

## 🏗️ Architecture

**Backend:** Layered Architecture (Controller → Service → Repository → Entity)  
**Frontend:** Component-based SPA with Vue.js 3  
**Database:** PostgreSQL with JPA/Hibernate ORM  
**Communication:** RESTful API with JSON data exchange

## 📝 License

This project is licensed under the MIT License.

## 👥 Author

**Enes Günümdoğdu**
---
