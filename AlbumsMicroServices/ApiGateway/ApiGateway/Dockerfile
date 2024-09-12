FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /build/libs/ApiGateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]