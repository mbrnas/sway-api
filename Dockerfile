# Use the official OpenJDK image as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/blog-platform-0.0.1-SNAPSHOT.jar blog-platform.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/blog-platform.jar"]
