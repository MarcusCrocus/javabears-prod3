# Use a base image of OpenJDK 21
FROM openjdk:21-jdk-slim

# Install MySQL client
RUN apt-get update && apt-get install -y default-mysql-client


# Set the working directory to /app
WORKDIR /app

# Copy the JAR file of the application to the container
COPY target/BearsJava-0.0.1-SNAPSHOT.jar BearsJava.jar

# Expose port 8081
EXPOSE 8081

# Command to run the application when the container starts
CMD ["java", "-jar", "BearsJava.jar"]