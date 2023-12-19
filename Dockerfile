FROM openjdk:17

WORKDIR /app

COPY target/blog-platform-0.0.1-SNAPSHOT.jar blog-platform.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/blog-platform.jar"]
