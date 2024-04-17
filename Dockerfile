FROM maven:latest as build
WORKDIR .
COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package

FROM openjdk:21
COPY --from=build ./app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]