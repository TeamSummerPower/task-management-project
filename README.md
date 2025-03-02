# Task Management Project

A full-stack task management application built with Spring Boot and vanilla JavaScript.

## Features

- User authentication and authorization
- Task creation, reading, updating, and deletion (CRUD operations)
- Task priority levels
- Recurring tasks support
- User profile management
- Responsive web interface

## Tech Stack

### Backend
- Java Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL (configured in application.yml)
- Gradle build system

### Frontend
- HTML5
- CSS3
- Vanilla JavaScript
- RESTful API integration

## Project Structure

```
├── TasksMSpring/          # Backend Spring Boot application
│   ├── src/main/java/
│   │   └── com/example/tasksmspring/
│   │       ├── controllers/    # REST endpoints
│   │       ├── repositories/   # Data access layer
│   │       ├── services/      # Business logic
│   │       ├── tasks/         # Task domain models
│   │       └── users/         # User domain models
│   └── src/main/resources/
│       └── application.yml    # Application configuration
│
└── front-end/             # Frontend application
    ├── mainTaskPage/      # Main task management interface
    ├── index.html         # Login/Registration page
    ├── script.js          # Frontend logic
    └── style.css         # Styling
```

## Getting Started

1. Clone the repository
2. Configure the database settings in `TasksMSpring/src/main/resources/application.yml`
3. Build the backend:
   ```bash
   cd TasksMSpring
   ./gradlew build
   ```
4. Run the Spring Boot application:
   ```bash
   ./gradlew bootRun
   ```
5. Open the frontend application in a web browser by serving the `front-end` directory

## API Endpoints

The application exposes REST endpoints for:
- User management (registration, login, profile updates)
- Task management (CRUD operations)
- Task prioritization
- Recurring task configuration

## Security

- Password encryption using BCrypt
- CORS configuration for secure cross-origin requests
- Role-based access control (ADMIN and USER roles)

## Contributing

Feel free to submit issues and enhancement requests.
