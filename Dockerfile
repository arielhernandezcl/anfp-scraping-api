FROM ghcr.io/graalvm/graalvm-ce:ol9-java21

WORKDIR /app

COPY target/*-runner /app/application

RUN chmod +x /app/application

ENTRYPOINT ["/app/application"]