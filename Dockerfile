## build
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jdk-alpine AS jre
WORKDIR /app

## slice Jar 
FROM jre AS java-slice
COPY target/*.jar app.jar
USER root
RUN java -Djarmode=layertools -jar app.jar extract

## package app
FROM jre
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
COPY --from=java-slice app/dependencies/ ./
COPY --from=java-slice app/spring-boot-loader/ ./
COPY --from=java-slice app/snapshot-dependencies/ ./
COPY --from=java-slice app/application/ ./

### Meta
ARG HTTP_PORT=8080
ENV HTTP_PORT=${HTTP_PORT}
EXPOSE ${HTTP_PORT}
