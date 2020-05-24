FROM openjdk:8-jdk-alpine
EXPOSE 8088
WORKDIR /app
COPY ./target/message-service-1.0-SNAPSHOT.jar ./
CMD ["java","-jar","message-service-1.0-SNAPSHOT.jar"]
