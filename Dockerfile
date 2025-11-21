# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# pom.xml kopyala
COPY pom.xml .

# Dependencies indir
RUN mvn dependency:resolve

# Source kodu kopyala
COPY src ./src

# Build et
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Builder stage-dən JAR-ı kopyala
COPY --from=builder /app/target/*.jar app.jar

# Port aç
EXPOSE 8080

# Environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Application başlat
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]