FROM openjdk:17

WORKDIR /app

COPY target/blog-platform-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar", "blog-platform-0.0.1-SNAPSHOT.jar"]
