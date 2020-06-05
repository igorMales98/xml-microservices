FROM openjdk:8-jdk-alpine
EXPOSE 8085
WORKDIR /app
COPY ./target/advertisement-service-1.0-SNAPSHOT.jar ./
COPY ./src/main/resources/images ./app/advertisement-service/src/main/resources/images
CMD ["java","-jar","advertisement-service-1.0-SNAPSHOT.jar"]
