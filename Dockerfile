FROM maven:3.9.16-eclipse-temurin-17 AS build

WORKDIR /workspace
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src/ src/
RUN ./mvnw -B clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /workspace/target/aiva-studio-api-0.0.1-SNAPSHOT.jar app.jar
USER 10001:10001
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
