# Estágio 1: Buildar o JAR usando Maven e JDK 17 (mude para 21 se o seu projeto usar o Java 21)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Imagem leve para rodar o app
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/projeto_turismo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]