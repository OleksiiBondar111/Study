FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /build/libs/PhotoAppApiUsers-0.0.1-SNAPSHOT.jar /app/users-microservice.jar
ENTRYPOINT ["java","-jar","users-microservice.jar"]