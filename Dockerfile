# Use a base image with Java 17
FROM openjdk:21

# Copy the JAR package into the image

ADD targetJar/loan-0.0.1-SNAPSHOT.jar targetJar/app.jar

# Expose the application port
EXPOSE 9099

# Run the App
ENTRYPOINT ["java", "-jar", "/app.jar"]