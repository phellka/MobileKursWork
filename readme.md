# Getting Started

### Requirements

* Gradle 6+
* Java 11
* Docker + Docker Compose(for local run)

### How to run locally

Run PostrgeSQL DB
```bash
docker compose up
```
Setup connection for DB in RDMS and execute script from *resources/migrations.sql*

Build project
```bash
./gradlew build  
```

Run project
```bash
./gradlew bootRun
```

### How to stop local run
In terminal with running app
```bash
Ctrl+C
```

In terminal with docker
```bash
Ctrl+C
```
```bash
docker compose down
```

### How to check

Application available at http://localhost:8080/swagger-ui/index.html

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building a RESTfull Web Service](https://www.baeldung.com/rest-with-spring-series)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Open API](https://www.baeldung.com/spring-rest-openapi-documentation)
* Mapstruct(https://www.baeldung.com/mapstruct)

### Additional Links

These additional references should also help you:

* [Liquibase](https://docs.liquibase.com/tools-integrations/springboot/springboot.html) - tool for DB migrations
* [Open API Specification](https://swagger.io/specification/) - tool for API first developing way(Allow to auto generate controllers with useful info for swagger)
* [Mapstruct](https://mapstruct.org/) - tool for mapping DTO to Entity and vice versa
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

