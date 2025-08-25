# Użyj oficjalnego obrazu Javy jako bazy
FROM openjdk:17-jdk-slim

# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Skopiuj plik JAR aplikacji do kontenera
# Zmień 'target/*.jar' jeśli używasz Gradle ('build/libs/*.jar')
COPY target/*.jar app.jar

# Uruchom aplikację
ENTRYPOINT ["java","-jar","app.jar"]