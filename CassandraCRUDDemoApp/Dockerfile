FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Shivaji
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/cassandra-0.1.0.jar /app/
ENTRYPOINT ["java", "-jar", "cassandra-0.1.0.jar"]