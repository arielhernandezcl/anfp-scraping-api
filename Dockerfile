FROM ghcr.io/graalvm/graalvm-ce:latest

WORKDIR /app

COPY target/*-runner /app/application

RUN chmod +x /app/application

ENTRYPOINT ["/app/application"]