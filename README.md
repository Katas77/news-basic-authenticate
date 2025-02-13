# News comments
## Overview:
-  The News Comments application is a small console application based on the Representational state Transfer software architecture. . 
- It Implements a REST API for a news service


## Features:
- create and manage users,
- create and manage news categories,
- create and manage news,
- create and manage news comments,
- implements news filtering by categories and authors.
## Security:
- Searching for all users can only be performed by a client with the ROLE_ADMIN role;
- A client with one of the following roles can receive information about a user by ID: ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR (a user who has only ROLE_USER can only receive information about himself);
- A client with one of the following roles can update user information: ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR (a user who has only ROLE_USER has the right to update only information about himself);
- A client with one of the following roles can delete a user profile: ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR (a user who only has ROLE_USER cannot delete the profiles of other users).


## Prerequisites
- Java 17
- Maven (for building the application)
- Spring Boot 3.2.3
- Docker Desktop

## Setup and Installation
- Clone the repository: git clone https://github.com/Katas77
- Go to the project directory:
- Build the application using Maven:
## For general use:
- Launch and configure the database via Docker
- To run the application using Docker, enter the following commands in the terminal:
```bash
cd docker
```
```bash
docker-compose up
```

____

### Usage
- Editing and deleting a news item is permitted only to the user who created it.
- Editing and deleting a comment on a news story is permitted only to the user who created it.
- Returning a list of entities (findAll) occurs using pagination. 
- The exception is the list of comments. list of comments to return only for specific news.
### Contact Management
Contacts are managed through a simple command-line interface. 
Input errors are handled gracefully, with prompts for correct input.

## Technologies used:

- Java
- Spring Boot
- Docker
- Spring Boot Data JPA
- Spring Security
- AOP


____
✉ Почта для обратной связи:
<a href="">krp77@mail.ru</a>