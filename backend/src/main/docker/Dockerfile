FROM eclipse-temurin:21-jre-alpine
LABEL authors="Codecool Advanced Project"
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]