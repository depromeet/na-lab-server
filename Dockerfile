FROM openjdk:11.0.11-jre-slim

ARG JAR_FILE=./api/build/libs/*-SNAPSHOT.jar
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG JWT_SECRET
ARG KAKAO_CLIENT_ID
ARG SENTRY_DSN

COPY ${JAR_FILE} luffy.jar

ENV db_url=${DB_URL} \
    db_username=${DB_USERNAME} \
    db_password=${DB_PASSWORD} \
    jwt_secret=${JWT_SECRET} \
    kakao_client_id=${KAKAO_CLIENT_ID} \
    sentry_dsn=${SENTRY_DSN}


ENTRYPOINT java -jar luffy.jar \
            --spring.datasource.url=${db_url} \
            --spring.datasource.username=${db_username} \
            --spring.datasource.password=${db_password} \
            --jwt.secret=${jwt_secret} \
            --oauth.kakao.client-id=${kakao_client_id} \
            --sentry.dsn=${sentry_dsn}

