FROM gradle:jdk17-jammy AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/chatai.jar /app/artifacts/chatai.jar

ENTRYPOINT ["java", "-jar", "/app/artifacts/chatai.jar"]