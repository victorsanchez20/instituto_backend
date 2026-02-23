# ETAPA 1: Construcción
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiar herramientas de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar código y compilar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Buscamos el jar que genera tu pom (instituto-api-...) y lo renombramos a app.jar
COPY --from=build /app/target/instituto-api-*.jar app.jar

EXPOSE 8080

# Ejecución limpia
ENTRYPOINT ["java", "-jar", "app.jar"]