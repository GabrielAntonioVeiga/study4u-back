# study4u-back
Backend project designed to manage classrooms and educational content

The frontend project can be found [here](https://github.com/Rafaells-dv/study4u-front.git)

# Technologies and Dependencies Used
- **Java 17**
- **Maven** used as a dependency management tool.
- **Spring Boot** used to build hassle free web applications and writing REST APIs.
- **Spring Security** used for Authentication and Authorizations.
- **Spring data JPA (Hibernate)** used to reduce the time of writing hardcoded sql queries and instead allows to write much more readable and scalable code.
- **Spring web** used to build controllers that expose REST endpoints.
- **MySQL** used as a Java persistence store.
- **Project Lombok** reduces the time of writing java boiler plate code.

# 1. You can clone it from github by running following command
  ```
  $ git clone https://github.com/GabrielAntonioVeiga/study4u-back.git 
  ```

# 2. Import project into eclipse
  `File -> Import -> Maven -> Existing Maven Projects -> Browse Project from cloned location`

# 3. Right click on project in eclipse and then Maven -> Update Projects

# 4. Import src/main/java/resources/study.sql into MySQL database

# 5. Update database credential and other configuration into application.properties available in src/main/java/resources
```
spring.datasource.url=jdbc:mysql://localhost:3306/study
spring.datasource.username=root
spring.datasource.password=
spring.jpa.show-sql=true


projeto.jwtSecret=bxOksa8BHgdAhR80Y3pEYvS5M+MnF2sheFDqprkTqQ4odqoszJLW1ikw64/nT/dTvlgrcBTq7HfK1B9Gai2h5A==
projeto.jwtExpirationMs=1800000
```

# 6. Right click on Application.java file and run as Java Application

# Backend Design
![Spring Boot architecture diagram](https://i.sstatic.net/BfNin.jpg)
