FROM java:8-jdk-alpine
MAINTAINER Shivaji Chandra
COPY ./target/CassandraCRUDDemoApp-0.0.1-SNAPSHOT.jar /usr/app

WORKDIR /usr/app
RUN sh -c 'touch CassandraCRUDDemoApp-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "CassandraCRUDDemoApp-0.0.1-SNAPSHOT.jar"]