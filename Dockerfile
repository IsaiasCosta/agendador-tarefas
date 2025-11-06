FROM gradle:7.6.4-jdk21  AS BUILD

WORKDIR /app

COPY . .
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon


FROM openjdk:21

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas.jar

EXPOSE 8082

CMD ["java","-jar","/app/agendador-tarefas.jar"]
