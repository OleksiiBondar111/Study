FROM openjdk:17-jdk-slim-buster
RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl
VOLUME /tmp
COPY configuration configuration
COPY /build/libs/PhotoAppApiConfigServer-0.0.1-SNAPSHOT.jar PhotoAppApiConfigServer.jar
ENTRYPOINT ["java","-jar","PhotoAppApiConfigServer.jar"]
