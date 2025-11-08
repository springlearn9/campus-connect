# Campus Connect

A Spring Boot web application for managing lost and found items on campus. This application helps students and faculty report lost items and claim found items, making campus life more convenient.

## Features

- **User Management**: Register and manage user accounts (Students, Faculty, Admin)
- **Lost Items**: Report and track lost items with detailed descriptions
- **Found Items**: Report found items and help others locate their belongings
- **Events**: Create, manage and view campus events with scheduling
- **Notices**: Post and manage important campus announcements and notices
- **Search & Filter**: Find items, events, and notices by various criteria
- **RESTful API**: Complete REST API for all operations

## Technology Stack

- **Backend**: Spring Boot 3.1.5
- **Database**: H2 (development), PostgreSQL (production)
- **Build Tool**: Maven
- **Java Version**: 17+
- **Framework**: Spring Data JPA, Spring Web

## Project Structure

```
src/
├── main/
│   ├── java/com/campusconnect/
│   │   ├── controller/          # REST Controllers
│   │   ├── model/              # Entity Classes
│   │   ├── repository/         # Data Access Layer
│   │   ├── service/           # Business Logic
│   │   └── config/            # Configuration Classes
│   └── resources/
│       ├── application.properties
       ├── application-dev.properties
       ├── application-postgresql.properties
       ├── postgresql-setup.sql   # Database setup script
│       └── data.sql           # Sample data
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/springlearn9/campus-connect.git
cd campus-connect
```

2. Build the project:
```bash
mvn clean package
```

3. Run the application:
```bash
java -jar target/campus-connect-backend-1.0.0.jar
```

The application will start on `http://localhost:8080`

### Database Configuration

#### Development (H2 In-Memory)
The application uses H2 database by default for development:
- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:campus_connect_db`
- **Username**: `sa`
- **Password**: `password`

#### Production (PostgreSQL)
To use PostgreSQL in production, activate the postgresql profile:
```bash
java -jar target/campus-connect-backend-1.0.0.jar --spring.profiles.active=postgresql
```

**Prerequisites for PostgreSQL:**
1. Install PostgreSQL on your system
2. Create database using the provided script:
```bash
psql -U postgres -f src/main/resources/postgresql-setup.sql
```
3. Update `application-postgresql.properties` with your PostgreSQL configuration.

**Default PostgreSQL Configuration:**
- **Host**: localhost
- **Port**: 5432
- **Database**: campus_connect_db
- **Username**: postgres
- **Password**: your_password (update in config file)

## API Endpoints

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Lost Items
- `GET /api/lost-items` - Get all lost items
- `GET /api/lost-items/{id}` - Get lost item by ID
- `POST /api/lost-items` - Report lost item
- `PUT /api/lost-items/{id}` - Update lost item
- `DELETE /api/lost-items/{id}` - Delete lost item

### Found Items
- `GET /api/found-items` - Get all found items
- `GET /api/found-items/{id}` - Get found item by ID
- `POST /api/found-items` - Report found item
- `PUT /api/found-items/{id}` - Update found item
- `DELETE /api/found-items/{id}` - Delete found item

### Events
- `GET /api/events` - Get all events
- `GET /api/events/{id}` - Get event by ID
- `POST /api/events` - Create new event
- `PUT /api/events/{id}` - Update event
- `DELETE /api/events/{id}` - Delete event
- `GET /api/events/upcoming` - Get upcoming events
- `GET /api/events/status/{status}` - Get events by status
- `GET /api/events/search?keyword={keyword}` - Search events by title
- `GET /api/events/user/{userId}` - Get events by user
- `GET /api/events/location?location={location}` - Get events by location
- `GET /api/events/date-range?startDate={start}&endDate={end}` - Get events by date range

### Notices
- `GET /api/notices` - Get all notices
- `GET /api/notices/{id}` - Get notice by ID
- `POST /api/notices` - Create new notice
- `PUT /api/notices/{id}` - Update notice
- `DELETE /api/notices/{id}` - Delete notice
- `GET /api/notices/active` - Get active notices
- `GET /api/notices/status/{status}` - Get notices by status
- `GET /api/notices/category/{category}` - Get notices by category
- `GET /api/notices/priority/{priority}` - Get notices by priority
- `GET /api/notices/search?keyword={keyword}` - Search notices by title
- `GET /api/notices/user/{userId}` - Get notices by user
- `GET /api/notices/high-priority` - Get high priority notices

## Development

### Running in Development Mode
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building for Production
```bash
mvn clean package -Dmaven.test.skip=true
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

- **Project Repository**: https://github.com/springlearn9/campus-connect
- **Issues**: https://github.com/springlearn9/campus-connect/issues

## Acknowledgments

- Spring Boot Documentation
- Spring Data JPA
- H2 Database Engine