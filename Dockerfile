# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Create the application directory
RUN mkdir /app

# Set the working directory inside the container
WORKDIR /app

# Create a non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the JAR file into the container
#COPY voting-system-coop/target/*spring-boot.jar /app/app.jar

# Change ownership of the /app directory to non-root user
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
