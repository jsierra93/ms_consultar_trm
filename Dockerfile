FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Jorge Sierra "jsierra93@hotmail.com"
VOLUME  /tmp
EXPOSE 8081
COPY build/libs/ms-consulta-trm-0.1.0.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]