# select image sonarqube
FROM sonarqube:8.9-community

COPY sonar-custom-plugin-1.0.jar /opt/sonarqube/extensions/

# select image maven
FROM maven:3.6.3-jdk-11-slim

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean install sonar:sonar -Dsonar.host.url=http://localhost:9000

# set the startup command to execute the jar
CMD ["java", "-jar", "target/prova-alelo-2.1.jar"]
