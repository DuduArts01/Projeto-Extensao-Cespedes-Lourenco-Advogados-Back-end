# Build stage
FROM gradle:8.12-jdk21 AS build
WORKDIR /home/gradle/src
COPY . .
# Forçamos o build e o shadowJar
RUN gradle build shadowJar --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR gerado pelo shadowJar (que tem todas as dependências)
COPY --from=build /home/gradle/src/build/libs/app.jar /app/app.jar

EXPOSE 8080
ENV PORT=8080
ENV KTOR_ENV=production

ENTRYPOINT ["java", "-jar", "app.jar"]