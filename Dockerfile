FROM ghcr.io/graalvm/jdk-community:21
WORKDIR /app
COPY target/anfp /app
EXPOSE 8080
CMD ["./anfp"]