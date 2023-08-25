# Exploring-Design-Patterns-with-FSD-
This project, executed independently, showcases the practical application of RESTful API development, Factory and Strategy design patterns, and error handling in Java. The application fetches disease count statistics from different sources, offering an insightful tracking mechanism for diseases like COVID-19.

This project fetches and analyzes disease statistics from various sources. It leverages the Spring Boot framework, integrates with RESTful services like Disease.sh and John Hopkins, and provides endpoints to access disease statistics and insights.

## Features
-> Fetch Active Cases: Access real-time active case counts from Disease.sh and John Hopkins.

-> Calculate Infected Ratios: Dynamically compute the infection ratio based on the source.

-> Modular Architecture: Uses Factory and Strategy design patterns for scalable and maintainable code.

## Endpoints
-> Disease.sh count: http://localhost:8080/v1/disease/disease-sh-io/count

-> John Hopkins count: http://localhost:8080/v1/disease/john-hopkins/count

-> Infected ratio: http://localhost:8080/v1/disease/infected-ratio?sourceType=<SOURCE_TYPE>

Replace <SOURCE_TYPE> with either DiseaseSh or JohnHopkins.

### Getting Started

Step 1: Clone the repository:
bash
Copy code

Step 2: git clone <repository-url>
Navigate to the project directory:
bash
Copy code

cd <project-directory>

Step 3: Build the project:
Copy code
mvn clean install
Run the Spring Boot application:
arduino
Copy code
mvn spring-boot:run

The service will start, and you can access the endpoints via a web browser or any API testing tool.

## Skills & Tools Used
-> Spring Boot

-> REST API Integration

-> Factory and Strategy Design Patterns

-> Java Streams and Data Structures

-> Exception Handling & Logging

-> Credits

This project was solely developed by Rahil Sharma. Feel free to contribute or raise any issues.
