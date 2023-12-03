FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/HeroesApp.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]




