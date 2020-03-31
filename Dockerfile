FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
ADD build/libs/ms-consulta-trm-0.1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]