FROM openjdk:11.0.11-jre-slim

ARG JAR_FILE=./api/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} luffy.jar

CMD ["java", "-jar", "/luffy.jar"]
