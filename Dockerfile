FROM openjdk:11 as build

WORKDIR /workspace

COPY . ./

RUN ./gradlew test
