FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/renato-cliente-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
