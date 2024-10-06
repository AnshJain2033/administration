FROM openjdk:17-jdk-alpine
#Set the working directory in the container
WORKDIR /app

# Add the application JAR to the container
COPY target/leaveService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8084

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
