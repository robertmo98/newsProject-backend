# Spring Boot News Project - Server

This is the backend part of the final fullstack project that serves as a REST server for the Scientific News React.js project. To connect to the designated React.js project, visit [News Project Frontend](https://github.com/robertmo98/newsProjectFront) and follow the instructions.

The server enables the storage of users, articles, and comments for the articles. Users can register and login.
## Authentication & Authorization - JWT
### Design patterns:
- 3 Layered architecture
- Singleton 
- DI - dependency injection
- DAO - Data access objects
- Builder pattern
Etc.

## Requirements:

For building and running the application, you need:

- Java [JDK 17] or higher.
- Database: A running MySQL database with the necessary configuration (connection URL, username, password). Adjust the required configuration in the `application.properties` file.

## Dependencies:

There are several third-party dependencies used in the project. Browse the Maven `pom.xml` file for details on libraries and versions used.

## Installation Instructions
# Prerequisites
1. JDK: Download and install [JDK 17 for your operating system from the official Oracle website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. MySQL database: Download and install [MySQL for you operating system from the official MySQL website](https://dev.mysql.com/downloads/mysql/)
# Setting up the project
1. Clone the Repository:
git clone https://github.com/robertmo98/newsProject.git
   cd newsProject
2. Adjust the required configuration in the application.properties file (connection URL, username, password)!
3. Built and run the application
   There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the edu.robertmo.newsproject.NewsProjectApplication.java class from your IDE.
   Alternatively you can use the Spring Boot Maven plugin with the commands:

   mvnw clean install
   mvnw spring-boot:run

- The application will start, and you should see output indicating that the server is running.
- The application should now be accessible at http://localhost:8080.
# Further instruction regarding the endpoints can be found on http://localhost:8080/swagger 
# Once running the application, the edu.robertmo.newsproject.initialization.Datainitializer.java (the run method) class will initialize the server (in the database) with USER_ROLES, admin user, and articles to demonstrate the server's and front end's features.
# The admin username email password are: admin admin@admin.com Password1! .
- To connect to the designated React.js project, visit [News Project Frontend](https://github.com/robertmo98/newsProjectFront) and follow the instructions.

