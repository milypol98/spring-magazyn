# =================================================================
# ETAP 1: Budowanie aplikacji (kompilacja kodu do pliku .jar)
# =================================================================
# Używamy obrazu, który zawiera narzędzia do budowania: JDK i Maven
# Nazwaliśmy ten etap "build", aby móc się do niego odwołać później
FROM maven:3.8-openjdk-17 AS build

# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy tylko plik pom.xml, aby zoptymalizować cache'owanie warstw
# Docker pobierze zależności tylko wtedy, gdy pom.xml się zmieni
COPY pom.xml .
RUN mvn dependency:go-offline

# Kopiujemy resztę kodu źródłowego aplikacji
COPY src ./src

# Uruchamiamy komendę Mavena, która kompiluje kod i tworzy plik .jar
# Plik .jar znajdzie się w katalogu /app/target/
RUN mvn clean package -DskipTests

# =================================================================
# ETAP 2: Tworzenie finalnego, lekkiego obrazu do uruchomienia
# =================================================================
# Używamy bardzo małego obrazu, który zawiera tylko środowisko
# uruchomieniowe Javy (JRE), bez zbędnych narzędzi
FROM openjdk:17-jre-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kluczowy moment: Kopiujemy plik .jar z poprzedniego etapu ("build")
# do naszego finalnego obrazu i zmieniamy jego nazwę na app.jar
COPY --from=build /app/target/*.jar app.jar

# Ustawiamy komendę, która uruchomi aplikację przy starcie kontenera
ENTRYPOINT ["java", "-jar", "app.jar"]
