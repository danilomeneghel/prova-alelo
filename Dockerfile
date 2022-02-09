# select image sonarqube
FROM openjdk:11-jre-slim

RUN apt-get update \
    && apt-get install -y curl gnupg2 unzip \
    && rm -rf /var/lib/apt/lists/*

USER root
ENV SONARQUBE_VERSION 5.0.1
ENV SONARQUBE_HOME /opt/sonarqube

# Http port
EXPOSE 9000

RUN set -x \
    && cd /opt \
    && curl -o sonarqube.zip -fSL https://downloads.sonarsource.com/sonarqube/sonarqube-${SONARQUBE_VERSION}.zip \
    && unzip sonarqube.zip \
    && mv sonarqube-${SONARQUBE_VERSION} sonarqube \
    && rm sonarqube.zip* \
    && rm -rf ${SONARQUBE_HOME}/bin/*

VOLUME "$SONARQUBE_HOME/data"

WORKDIR ${SONARQUBE_HOME}
COPY run.sh ${SONARQUBE_HOME}/bin/
RUN chmod +x ${SONARQUBE_HOME}/bin/run.sh
RUN useradd sonar
RUN chown -R sonar /opt/sonarqube
ENTRYPOINT ["./bin/run.sh"]

# select image maven
FROM maven:3.6.3-slim

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean install sonar:sonar -Dsonar.host.url=http://localhost:9000

# set the startup command to execute the jar
CMD ["java", "-jar", "target/prova-alelo-2.1.jar"]
