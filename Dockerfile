FROM openjdk:21-slim-bookworm
ARG JAR_FILE=target/subscriber-bc-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
