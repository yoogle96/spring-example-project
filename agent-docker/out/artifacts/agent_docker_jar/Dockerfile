FROM openjdk:8-jre-alpine
ADD agent-docker.jar agent-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/agent-docker.jar"]
