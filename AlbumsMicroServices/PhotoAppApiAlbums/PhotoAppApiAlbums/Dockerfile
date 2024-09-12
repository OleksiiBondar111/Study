FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /build/libs/PhotoAppApiAlbums-0.0.1-SNAPSHOT.jar /app/albums-service.jar
ENTRYPOINT ["java","-jar","albums-service.jar"]