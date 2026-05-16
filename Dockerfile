# Etapa de construcción (Build)
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
# Le damos permisos al wrapper de Maven y compilamos
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución (Run)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto que usa tu app
EXPOSE 8093
# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]