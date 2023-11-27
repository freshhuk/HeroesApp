FROM openjdk:21-jdk-alpine

RUN apk add --no-cache bash mysql-client

ENV SPRING_APPLICATION_NAME=my-app
ENV DB_HOST=db
ENV DB_PORT=3306
ENV DB_DATABASE=my_db
ENV DB_USERNAME=freshhuk
ENV DB_PASSWORD=freshhuk

WORKDIR /app

COPY target/my-app.jar .

CMD ["java", "-jar", "my-app.jar"]
