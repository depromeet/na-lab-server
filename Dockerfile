FROM openjdk:11.0.11-jre-slim

ARG JAR_FILE=./api/build/libs/*-SNAPSHOT.jar
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

COPY ${JAR_FILE} luffy.jar

ENV db_url=${DB_URL} \
    db_username=${DB_USERNAME} \
    db_password=${DB_PASSWORD}

ENTRYPOINT java -jar luffy.jar \
            --spring.datasource.url=${db_url} \
            --spring.datasource.username=${db_username} \
            --spring.datasource.password=${db_password}
