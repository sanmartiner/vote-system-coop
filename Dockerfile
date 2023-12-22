# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Create the application directory
WORKDIR /app

# Create a non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Change ownership of the /app directory to non-root user
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Copy the specific JAR file into the container
COPY target/voting-system-0.0.1-SNAPSHOT-spring-boot.jar app.jar

# Copy the RabbitMQ initialization script
COPY scripts/rabbitmq-init.sh rabbitmq-init.sh

# Copy the wait-for-services script
COPY scripts/wait-for-services.sh wait-for-services.sh

# Set permissions for the scripts
USER root
RUN chmod +x rabbitmq-init.sh wait-for-services.sh
USER appuser

# Install netcat (nc) for the wait-for-services script
USER root
RUN apk add --no-cache netcat-openbsd
USER appuser

# Expose the port that the application will run on
EXPOSE 8080

# Run the wait-for-services script to wait for MySQL and RabbitMQ to be ready
CMD ["sh", "-c", "./wait-for-services.sh mysql:3306 rabbitmq:5672 -- /rabbitmq-init.sh && java -jar app.jar"]
