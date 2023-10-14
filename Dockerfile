#Maven Build
FROM maven:3.8.6-openjdk-17 AS builder
COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests

#Run
FROM openjdk:17
COPY --from=builder /app/target/bandla-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]