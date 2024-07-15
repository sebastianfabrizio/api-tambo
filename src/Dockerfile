FROM maven:3.8.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21.0.1-jdk-slim
COPY --from=build /target/tambo-0.0.1-SNAPSHOT.jakarta tambo.jakarta
EXPOSE 8080
ENTRYPOINT ["java","-jar","tambo.jar"]
