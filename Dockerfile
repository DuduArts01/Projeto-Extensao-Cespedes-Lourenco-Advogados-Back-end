# Build stage
FROM gradle:8.12-jdk21 AS build
WORKDIR /home/gradle/src
COPY . .
RUN gradle build --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia especificamente o app.jar que você identificou na pasta
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENV PORT=8080
ENV KTOR_ENV=production

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]