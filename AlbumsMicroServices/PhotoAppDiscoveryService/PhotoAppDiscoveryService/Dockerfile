FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /build/libs/PhotoAppDiscoveryService-0.0.1-SNAPSHOT.jar /app/DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]