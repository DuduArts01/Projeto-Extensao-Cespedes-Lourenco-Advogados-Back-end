# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Altere esta linha para usar o * (wildcard)
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy gradle files
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle/

# Copy source code
COPY src src/

# Build the application
RUN ./gradlew build --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/build/libs/app.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV PORT=8080
ENV KTOR_ENV=production

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
