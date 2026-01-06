# ETAPA 1: Construcción (Build)
# Usamos Maven con Java 17 (Temurin)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila y empaqueta saltando tests
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Run)
# Usamos Java 17 JRE (versión ligera y oficial)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia el .jar generado
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]