FROM amd64/ubuntu:22.04

ENV DOCKERIZE_VERSION v0.6.1

COPY *.hurl hurls/

RUN apt-get update && apt-get install -y wget
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN apt-get install -y curl

RUN curl -k --location --remote-name https://github.com/Orange-OpenSource/hurl/releases/download/4.0.0/hurl_4.0.0_amd64.deb
RUN apt-get update && apt-get install -y ./hurl_4.0.0_amd64.deb

ENTRYPOINT dockerize -wait tcp://nalab-server:8080 -timeout 300s && hurl --very-verbose --color --test hurls/*.hurl
