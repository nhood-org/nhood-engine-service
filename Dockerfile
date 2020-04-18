FROM openjdk:11
MAINTAINER github.com/nhood-org
COPY nhood-engine-service-svc/target/nhood-engine-service-svc-0.0.1-SNAPSHOT.jar /app/
CMD java -jar /app/nhood-engine-service-svc-0.0.1-SNAPSHOT.jar
EXPOSE 8080