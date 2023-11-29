FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]




# Используем образ Ubuntu с OpenJDK 21
#FROM openjdk:21

# Устанавливаем bash и mysql-client
#RUN apt-get update && \
  #  apt-get install -y bash mysql-client && \
  #  rm -rf /var/lib/apt/lists/*

# Остальные инструкции Dockerfile
#ENV SPRING_APPLICATION_NAME=my-hero-app
#ENV DB_HOST=mysql-container
#ENV DB_PORT=3306
#ENV DB_DATABASE=my_db
#ENV DB_USERNAME=freshhuk
#ENV DB_PASSWORD=freshhuk

#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
