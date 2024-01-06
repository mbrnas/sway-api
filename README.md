# Blog Platform Documentation

## Overview

The Blog Platform is a Java-based web application designed for creating, updating, and managing blog posts. It leverages Spring Boot for its framework, JPA/Hibernate for ORM, and PostgreSQL for database management.

## Key Components

### 1. [BlogPlatformApplication](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/BlogPlatformApplication.java)
   - The main class that bootstraps the Spring Boot application.

### 2. Models
   - [Post](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/model/posts/Post.java): Represents a blog post with attributes like title, content, and associated user and category.
   - [User](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/model/users/User.java): Represents a user with attributes like username, password, and email.
   - [Category](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/model/categories/Category.java): Represents a category for blog posts.
   - [Comment](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/model/comments/Comment.java): Represents comments made on blog posts.

### 3. Repositories
   - Interfaces for CRUD operations on models.
   - [PostRepository](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/repository/posts/PostRepository.java), [UserRepository](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/repository/users/UserRepository.java), etc.

### 4. Services
   - [PostService](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/service/posts/PostService.java): Contains business logic for handling posts.

### 5. Controllers
   - [PostController](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/controller/posts/PostController.java): Handles HTTP requests for managing posts.

### 6. Exceptions
   - Custom exceptions like [PostNotFoundException](https://github.com/mbrnas/blog-platform/blob/main/src/main/java/com/company/blogplatform/exception/PostNotFoundException.java) for handling specific error scenarios.

### 7. Configuration
   - [application.yml](https://github.com/mbrnas/blog-platform/blob/main/src/main/resources/application.yml): Configuration for database and JPA/Hibernate.

### 8. Tests
   - [BlogPlatformApplicationTests](https://github.com/mbrnas/blog-platform/blob/main/src/test/java/com/company/blogplatform/BlogPlatformApplicationTests.java): Basic context loading test for the application.

## Running the Application

1. **Prerequisites**: Java and PostgreSQL installed.
2. **Database Setup**: Configure PostgreSQL database as per `application.yml`.
3. **Running the App**: Execute `BlogPlatformApplication.java` to start the application.
