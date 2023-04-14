FROM gradle:7.4.0-jdk17

WORKDIR /project

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} docker.jar

CMD ["java", "-jar", "docker.jar"]

EXPOSE 3000