# Use official Maven image to build the project
FROM maven:3.9.1-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# Use a minimal Java runtime image for running the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/Vendor_data-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
