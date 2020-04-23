# Build application
FROM maven:3 AS build
MAINTAINER github.com/nhood-org

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN
ARG MVN_LOCAL_REPOSITORY

WORKDIR /usr/src/nhood-org/
COPY . .

ADD $MVN_LOCAL_REPOSITORY /root/

RUN export GITHUB_USERNAME=$GITHUB_USERNAME && \
    export GITHUB_TOKEN=$GITHUB_TOKEN && \
    mvn -s .mvn.settings.xml clean install

# Application run time
FROM openjdk:11
MAINTAINER github.com/nhood-org
EXPOSE 8080

WORKDIR /usr/src/nhood-org/
COPY --from=build /usr/src/nhood-org/nhood-engine-service-svc/target/nhood-engine-service-svc.jar ./app/

CMD java -jar ./app/nhood-engine-service-svc.jar
