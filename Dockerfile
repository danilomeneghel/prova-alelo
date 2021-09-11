FROM openjdk:11-oracle
VOLUME /tmp
COPY target/*.jar api.jar
ENTRYPOINT ["java", "-jar","/api.jar", "--spring.profiles.active=docker"]
