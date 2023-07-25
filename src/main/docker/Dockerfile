FROM eclipse-temurin:17-alpine
RUN mkdir /opt/app
WORKDIR /opt/app
COPY target/site-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD [ "java", "-jar", "site-service-0.0.1-SNAPSHOT.jar" ]
