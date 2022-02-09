# select image sonarqube
FROM openjdk:11-jre-slim

RUN apt-get update 
    && apt-get install -y curl gnupg2 unzip 
    && rm -rf /var/lib/apt/lists/*

ENV SONAR_VERSION=7.9.6 
    SONARQUBE_HOME=/opt/sonarqube 
    SONARQUBE_JDBC_USERNAME=sonar 
    SONARQUBE_JDBC_PASSWORD=sonar 
    SONARQUBE_JDBC_URL=""

# Http port
EXPOSE 9000

RUN groupadd -r sonarqube && useradd -r -g sonarqube sonarqube

RUN set -x 
    && cd /opt 
    && curl -o sonarqube.zip -fSL https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-$SONAR_VERSION.zip 
    && curl -o sonarqube.zip.asc -fSL https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-$SONAR_VERSION.zip.asc 
    && gpg --batch --verify sonarqube.zip.asc sonarqube.zip 
    && unzip -q sonarqube.zip 
    && mv sonarqube-$SONAR_VERSION sonarqube 
    && chown -R sonarqube:sonarqube sonarqube 
    && rm sonarqube.zip* 
    && rm -rf $SONARQUBE_HOME/bin/*

VOLUME "$SONARQUBE_HOME/data"

WORKDIR $SONARQUBE_HOME
COPY run.sh $SONARQUBE_HOME/bin/
USER sonarqube
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
