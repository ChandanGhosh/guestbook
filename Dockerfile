FROM openjdk:11.0.7-jre-slim-buster
EXPOSE 8081
EXPOSE 8082
WORKDIR /app
ARG JAR=guestbook-0.0.1-SNAPSHOT.jar

COPY /build/libs/$JAR /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]