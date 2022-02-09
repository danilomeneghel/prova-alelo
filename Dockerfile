# select image sonarqube
FROM docker.io/sonarqube:8.9.2-community

ENV CUSTOM_PLUGINS_DIR=/opt/sonarqube/extensions/plugins

ADD https://github.com/rht-labs/sonar-auth-openshift/releases/download/v1.2.0/sonar-auth-openshift-plugin.jar ${CUSTOM_PLUGINS_DIR}
ADD https://github.com/dmeiners88/sonarqube-prometheus-exporter/releases/download/v1.0.0-SNAPSHOT-2018-07-04/sonar-prometheus-exporter-1.0.0-SNAPSHOT.jar ${CUSTOM_PLUGINS_DIR}

RUN chmod -R a+rx ${CUSTOM_PLUGINS_DIR}

# select image maven
FROM maven:3.6.3-jdk-11-slim

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean install sonar:sonar -Dsonar.host.url=http://localhost:9000

# set the startup command to execute the jar
CMD ["java", "-jar", "target/prova-alelo-2.1.jar"]
