
# Utilise une image Java 17 légère
FROM eclipse-temurin:17-jdk-alpine

# Dossier de travail dans le conteneur
WORKDIR /app

# Copie du .jar compilé dans le conteneur
COPY build/libs/SportApp-0.0.1-SNAPSHOT.jar app.jar

# Commande pour lancer ton app
ENTRYPOINT ["java", "-Xmx256m", "-jar", "/app/app.jar"]
