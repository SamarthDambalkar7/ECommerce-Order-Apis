# OpenJDK 17 Alpine Image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/e-commerce.jar /app/e-commerce.jar

# Expose the port the application runs on
#EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "e-commerce.jar"]
