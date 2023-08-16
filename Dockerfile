FROM openjdk:11-jre-slim
VOLUME /tmp-user-service
ADD target/user-service-0.0.1-SNAPSHOT.jar user-app.jar
ENTRYPOINT ["java","-jar","/user-app.jar"]