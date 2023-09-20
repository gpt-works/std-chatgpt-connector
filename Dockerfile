FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY /target/std-gpt-connector-0.0.1-SNAPSHOT.jar std-gpt-connector.jar
ENTRYPOINT ["java","-jar","/std-gpt-connector.jar"]