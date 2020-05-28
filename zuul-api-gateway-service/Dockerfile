FROM openjdk:8-jdk-alpine
EXPOSE 8091
WORKDIR /app
COPY ./target/zuul-api-gateway-service-1.0-SNAPSHOT.jar ./
COPY ./src/main/resources/bootsecurity.p12 ./etc/ssl/certs/
CMD ["java","-jar","zuul-api-gateway-service-1.0-SNAPSHOT.jar"]