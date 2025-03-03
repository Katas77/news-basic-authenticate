# 📰 News Comments App
## Overview 🌐
## The News Comments application is a small console app built on top of the Representational State Transfer (REST) architecture. It provides a flexible REST API for managing news content, including user accounts, news categories, articles, and comments.

## Features ✨
- Create and manage users
- Manage news categories
- Post and edit news articles
- Add and moderate comments under each article
- Filter news by categories or authors
## Security 🔒
- User permissions are controlled by specific roles. Here's how it works:

- ROLE_ADMIN: Can search for all users, modify any user's data, and delete any account.
- ROLE_MODERATOR: Can view, modify, and delete certain user profiles (except those with higher privileges).
- 0ROLE_USER: Users can only access their own profile details, but they can't delete others' accounts.
## Prerequisites ⚙️
### Before you start, ensure that you have these tools installed:

- Java 17
- Maven (for building the application)
- Spring Boot 3.2.3
- Docker Desktop
- Setup & Installation 🛠️
- Clone the Repository 📥

- git clone https://github.com/Katas77/NewsCommentsApp.git
- Build the Application 🏗️
-- Navigate into the project directory:
- Then build the application using Maven:
```bash
mvn clean install
```


## Launch Database Using Docker 🐳
- To set up your database using Docker, follow these steps:
- Start Docker if it's not already running.
- Run the command below to launch the database container:
```bash
docker-compose up -d
```

## This will create and initialize the necessary containers for your database.

 ## Running the Application 🚀
- Once you've built the application and started the database, you're ready to run the app!
- Simply execute this command from the root folder of the project:
```bash
java -jar target/news-comments-app.jar
```


- And that's it! Your News Comments app should now be up and running. 🎉


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