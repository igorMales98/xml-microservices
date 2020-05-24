FROM openjdk:8-jdk-alpine
EXPOSE 8761
WORKDIR /app
COPY ./target/eureka-service-1.0-SNAPSHOT.jar ./
CMD ["java","-jar","eureka-service-1.0-SNAPSHOT.jar"]