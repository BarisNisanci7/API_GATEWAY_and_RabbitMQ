# API Gateway Project


## Project Description

This project was developed as part of the SE4458 course assignment to implement an API Gateway. It consolidates three previously created API endpoints and provides centralized access through a gateway. Docker support is optionally included.

## Features
* API Gateway: Combines three API endpoints for centralized access.
* Docker Support: Can be run inside a Docker container.
* User-Friendly: Simplifies API interactions through a single gateway.

## Project Structure
* src/: Contains the main source code.
* pom.xml: Maven configuration file.
* Dockerfile: Configuration file for containerizing the project.

## Setup Instructions

1. Clone the repository:
```bash
git clone <https://github.com/BarisNisanci7/API_GATEWAY_and_RabbitMQl>
cd API_GATEWAY
```
2. Install Maven dependencies:
```bash
./mvnw clean install
```
3. Run the application:
```bash
./mvnw spring-boot:run
```
4. Access the API Gateway: By default, it will run at http://localhost:8080.


## Running with Docker
1. Build the Docker image:
```bash
docker build -t api-gateway .
```
2. Run the Docker container:
```bash
docker run -p 8080:8080 api-gateway
```


## Assumptions

* The project was built using Spring Boot.
* URLs of the connected endpoints are manually configured.
* The application was tested in a development environment only.

## Video links will be Update
