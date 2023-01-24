FROM gradle:jdk17-alpine
ARG JAR_FILE="websocket-0.0.1-SNAPSHOT.jar"
WORKDIR /app
COPY . /app
RUN gradle build -x test

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/build/libs/websocket-0.0.1-SNAPSHOT.jar"]