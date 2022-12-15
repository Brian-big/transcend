# syntax=docker/dockerfile:1

FROM openjdk:17-slim

RUN mkdir /app
COPY . /app
WORKDIR /app

ENTRYPOINT ["java", "-jar", "./build/libs/Transcend-1.0-SNAPSHOT.jar"]