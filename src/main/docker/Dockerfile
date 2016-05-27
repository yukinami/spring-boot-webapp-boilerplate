FROM java:8-jdk-alpine
MAINTAINER Jyong <yuki-nami@hotmail.com>

ENV DIST build/libs/spring-boot-webapp-boilerplate.jar
ENV JAVA_OPTS "-Dspring.profiles.active=production"

VOLUME /tmp
ADD $DIST app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]

