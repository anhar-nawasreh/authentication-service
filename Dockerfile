FROM bellsoft/liberica-runtime-container:jre-17-stream-musl


WORKDIR /app

COPY target/authentication-service-0.0.1-SNAPSHOT.jar /app/authentication-service-0.0.1-SNAPSHOT.jar

EXPOSE 8082

CMD ["java", "-jar", "authentication-service-0.0.1-SNAPSHOT.jar"]
