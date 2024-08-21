FROM maven:latest AS build

WORKDIR /code

RUN mkdir -p /root/.m2
COPY settings.xml /root/.m2/settings.xml
COPY pom.xml /code/pom.xml

COPY ["src/main", "/code/src/main"]
RUN mvn --batch-mode package

FROM eclipse-temurin:17-jdk

COPY --from=build /code/target/*.jar /app.jar

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app.jar"]