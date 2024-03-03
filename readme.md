# Project Movie DB 

This project is a backend application for gas dispatch management via REST API, built using the [Spring Boot](https://spring.io/projects/spring-boot) framework. All data is persisted in an [PostgreSQL](https://www.postgresql.org/) database.

## Installation

- Clone the repository with the following git command

```
git clone https://github.com/polanc4170/project-plinovodi.git
```

### Setup with GIT

- After the project has been cloned, go into ```..\resource\application.properties``` and update the field according to your own database access:

```
spring.datasource.url      = <DB_URL>
spring.datasource.username = <DB_USERNAME>
spring.datasource.password = <DB_PASSWORD>
```

- Run the application, which will then run on default port:

```
http://localhost:8080
```

- There are two starting templates for the clientside, implemented in [Angular](https://angular.io/) and [React](https://react.dev/), but are not currently fully developed. For both [NodeJS](https://nodejs.org/en) must be installed on the machine.

```
cd src/main/resources/static/client-angular
npm install
ng add @angular/material
ng serve
```

```
cd src/main/resources/static/client-react
npm install react
npm start
```

## Data

The application consists of three entities ```Report```, ```Intervention``` and ```Holiday```, and their relationship and structure drawn with [DrawSQL](https://drawsql.app/) is as follows:

![DB Diagram](/res/db_diagram.png?raw=true)

## API Endpoints

The following endpoints are handled by the ```RestController```:

| Request | URL                | Action                           |
|:--------|:-------------------|:---------------------------------|
| GET     | /holiday           | Returns holidays                 |
| GET     | /date/{date}       | Returns date type                |
| GET     | /user/intervention | Returns interventions            |
| POST    | /user/report       | Adds report                      |
| GET     | /user/report       | Returns reports                  |
| GET     | /user/report/{id}  | Returns report with id           |
| PUT     | /user/report/{id}  | Updates report with id           |
| GET     | /user              | Returns summary for users        |
| GET     | /user/{id}         | Returns summary for user with id |
