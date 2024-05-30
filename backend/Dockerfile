FROM eclipse-temurin:21-jre-alpine
LABEL authors="Codecool Advanced Project"
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]