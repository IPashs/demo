FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jdk-alpine AS jre
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]

ARG HTTP_PORT=8080
ENV HTTP_PORT=${HTTP_PORT}
EXPOSE ${HTTP_PORT}