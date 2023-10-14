#Run
FROM openjdk:17-jdk-alpine
COPY --from=builder /app/target/bandla-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]