# Utiliza una imagen base con OpenJDK 21 y Maven
FROM maven:3.9.6-amazoncorretto-21-al2023 as builder
# Establece el directorio de trabajo
WORKDIR /app
# Copia el archivo pom.xml para descargar dependencias
COPY pom.xml .
# Descarga las dependencias de Maven (esto se realiza por separado para aprovechar la caché de Docker)
RUN mvn dependency:go-offline -B
# Copia el resto de la aplicación y realiza el paquete sin ejecutar las pruebas
COPY src src
RUN mvn clean package -DskipTests
# Utiliza una imagen más liviana para ejecutar la aplicación
FROM maven:3.9.6-amazoncorretto-21-al2023
# Establece el directorio de trabajo
WORKDIR /app
# Copia solo el JAR construido en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar
# Exponer el puerto 8080 (o el que hayas configurado en tu aplicación Spring Boot)
EXPOSE 8080
# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]