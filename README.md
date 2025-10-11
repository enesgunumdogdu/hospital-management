# Hospital Management System

A comprehensive web-based hospital management application built with Spring Boot and Vue.js, designed to manage doctors, patients, examinations, and prescriptions efficiently.

## 🏥 Project Overview

This project implements a complete hospital management system with a RESTful API backend and a modern single-page application frontend. The system follows layered architecture principles and provides full CRUD operations for all core entities.

## 🚀 Features

### Core Functionality
- **Doctor Management**: Add, view, update, and delete doctor records
- **Patient Management**: Complete patient registration and information management
- **Examination Management**: Create and manage medical examinations
- **Prescription Management**: Generate and manage prescriptions linked to examinations

### Technical Features
- **RESTful API**: Clean and well-structured API endpoints
- **Database Integration**: PostgreSQL with JPA/Hibernate
- **Modern Frontend**: Vue.js 3 with responsive design
- **CORS Support**: Cross-origin resource sharing configured
- **DTO Pattern**: Data transfer objects for clean API communication
- **MapStruct Integration**: Automatic entity-DTO mapping

## 🛠️ Technology Stack

### Backend
- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **MapStruct**

### Frontend
- **Vue.js 3.5.22**
- **Vite**
- **Axios**
- **CSS3**

### Database
- **PostgreSQL** (Production)
- **H2** (Testing)

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java 21** or higher
- **Node.js 16** or higher
- **PostgreSQL 12** or higher
- **Maven 3.6** or higher

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd hospital-management
```

### 2. Database Setup

#### PostgreSQL Configuration

1. Install PostgreSQL and create a database:
```sql
CREATE DATABASE hospital_management;
```

2. Update the database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Backend Setup

1. Navigate to the project root directory:
```bash
cd hospital-management
```

2. Install dependencies and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will be available at `http://localhost:8080`

### 4. Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend-hospital-management
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will be available at `http://localhost:5173`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### Doctors
```
GET    /api/doctors          # Get all doctors
GET    /api/doctors/{id}      # Get doctor by ID
POST   /api/doctors           # Create new doctor
PUT    /api/doctors/{id}      # Update doctor
DELETE /api/doctors/{id}      # Delete doctor
```

#### Patients
```
GET    /api/patients          # Get all patients
GET    /api/patients/{id}     # Get patient by ID
POST   /api/patients          # Create new patient
PUT    /api/patients/{id}     # Update patient
DELETE /api/patients/{id}     # Delete patient
```

#### Examinations
```
GET    /api/examinations      # Get all examinations
GET    /api/examinations/{id} # Get examination by ID
POST   /api/examinations      # Create new examination
PUT    /api/examinations/{id} # Update examination
DELETE /api/examinations/{id} # Delete examination
```

#### Prescriptions
```
GET    /api/prescriptions     # Get all prescriptions
GET    /api/prescriptions/{id} # Get prescription by ID
POST   /api/prescriptions     # Create new prescription
PUT    /api/prescriptions/{id} # Update prescription
DELETE /api/prescriptions/{id} # Delete prescription
```

### Request/Response Examples

#### Create Doctor
```json
POST /api/doctors
Content-Type: application/json

{
  "firstNameAndLastName": "Dr. John Smith",
  "specialty": "Cardiology",
  "phoneNumber": "+1234567890"
}
```

#### Create Patient
```json
POST /api/patients
Content-Type: application/json

{
  "firstNameAndLastName": "Jane Doe",
  "identityNumber": "12345678901",
  "birthDate": "1990-05-15",
  "phoneNumber": "+1234567890"
}
```

## 🏗️ Project Structure

```
hospital-management/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/enesgunumdogdu/hospital_management/
│       │       ├── config/           # Configuration classes
│       │       ├── controller/       # REST controllers
│       │       ├── domain/
│       │       │   ├── dto/         # Data Transfer Objects
│       │       │   ├── entities/    # JPA entities
│       │       │   └── mapper/     # MapStruct mappers
│       │       ├── repository/      # Data repositories
│       │       └── service/         # Business logic
│       └── resources/
│           └── application.properties
├── frontend-hospital-management/
│   ├── src/
│   │   ├── components/       # Vue components
│   │   ├── services/        # API services
│   │   ├── views/          # Page views
│   │   └── assets/         # Static assets
│   └── package.json
└── pom.xml
```

## 🗄️ Database Schema

### Entities and Relationships

#### Doctor
- `id` (Long, Primary Key)
- `firstNameAndLastName` (String)
- `specialty` (String)
- `phoneNumber` (String)

#### Patient
- `id` (Long, Primary Key)
- `firstNameAndLastName` (String)
- `identityNumber` (String)
- `birthDate` (LocalDate)
- `phoneNumber` (String)

#### Examination
- `id` (Long, Primary Key)
- `doctor` (Doctor, Many-to-One)
- `patient` (Patient, Many-to-One)
- `examinationDate` (LocalDateTime)
- `diagnosis` (String)
- `prescription` (Prescription, One-to-One)

#### Prescription
- `id` (Long, Primary Key)
- `examination` (Examination, One-to-One)
- `medicationName` (String)
- `dosage` (String)
- `description` (String)

## 🎨 Frontend Features

### User Interface
- **Clean Design**: White background with gray-toned buttons
- **Responsive Layout**: Works on desktop and mobile devices
- **Modal Forms**: Popup forms for adding/editing records
- **Data Tables**: Sortable and interactive data display
- **Loading States**: User feedback during API calls
- **Error Handling**: User-friendly error messages

### Navigation
- **Tab-based Navigation**: Easy switching between modules
- **CRUD Operations**: Add, Edit, Delete, and Refresh buttons
- **Form Validation**: Client-side validation for required fields

## 🔧 Configuration

### Backend Configuration

#### Application Properties
```properties
spring.application.name=hospital-management
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_management
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

#### CORS Configuration
The application is configured to allow requests from `http://localhost:5173` (Vue.js dev server).

### Frontend Configuration

#### API Base URL
```javascript
// src/services/api.js
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
})
```

## 🧪 Testing

### Running Tests

#### Backend Tests
```bash
mvn test
```

#### Frontend Tests
```bash
cd frontend-hospital-management
npm test
```

## 🚀 Deployment

### Backend Deployment

1. Build the application:
```bash
mvn clean package
```

2. Run the JAR file:
```bash
java -jar target/hospital-management-0.0.1-SNAPSHOT.jar
```

### Frontend Deployment

1. Build for production:
```bash
cd frontend-hospital-management
npm run build
```

2. Serve the `dist` folder with a web server (nginx, Apache, etc.)

## 🐳 Docker Support

The project includes Docker configuration for easy deployment:

```bash
docker-compose up -d
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Enes Günümdoğdu** - *Initial work*

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- Vue.js team for the amazing framework
- PostgreSQL community for robust database support

## 📞 Support

If you encounter any issues or have questions, please:

1. Check the [Issues](https://github.com/your-repo/issues) page
2. Create a new issue with detailed description
3. Contact the maintainers

---

**Happy Coding! 🚀**
