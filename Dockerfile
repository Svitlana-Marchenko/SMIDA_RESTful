FROM openjdk:11-jdk-slim

VOLUME /tmp

ARG JAR_FILE=target/smida-api

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
