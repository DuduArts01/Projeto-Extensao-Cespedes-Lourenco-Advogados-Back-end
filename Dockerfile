# Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src
COPY . .
RUN gradle build --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR do build stage de forma direta
# Ajustamos para buscar qualquer arquivo .jar dentro da pasta libs
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080
ENV PORT=8080
ENV KTOR_ENV=production

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]