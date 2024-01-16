# Getting Started

### Requirements
java 17 

maven 3.9.1

project is build on spring 3.2.1

## Guides
### Before you run
Please change Database connection according to your DB. Settings you can find in application properties file.
Port also change according to your availability.

### Run Application
Run two simple commands ````
    mvn clean package -DskipTests
    mvn spring-boot:run
    ````
Once application is running you can visit get enpoint http://localhost:8080/api/customer/345678
Port number change according to yours and also last number according to ref that you would like to see

### Info
This app purpose is excercise. It is lucking a lot of security and exception handlers. It is real world app and is not recommended to use in any projects.


