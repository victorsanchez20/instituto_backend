# --- ETAPA 1: Construcción (Build) ---
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiamos los archivos de configuración de Maven primero para aprovechar el cache
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
# Descarga las dependencias sin compilar (esto ahorra tiempo en futuros deploys)
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente y construimos
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- ETAPA 2: Ejecución (Runtime) ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiamos solo el JAR desde la etapa anterior
# Esto soluciona el problema de la ruta y el nombre
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Comando optimizado
ENTRYPOINT ["java", "-jar", "app.jar"]