FROM gradle:9-jdk17-alpine AS BUILD
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=BUILD /app/build/libs/*.jar  /app/Agendadortarefas.jar
EXPOSE 8082

CMD ["java",  "-jar",   "/app/Agendadortarefas.jar"]