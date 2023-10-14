#Maven Build
FROM openjdk:17 AS builder
COPY pom.xml /app/
COPY src /app/src
RUN ./mvnw -B package

#Run
FROM openjdk:17
COPY --from=builder /app/target/bandla-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]