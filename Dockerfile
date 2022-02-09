# select image sonarqube
FROM sonarqube:7.8-community

COPY plugins /opt/sonarqube/extensions/plugins
COPY openjdk-11.0.3.tar.gz .

USER root

RUN \
tar -xvzf openjdk-11.0.3.tar.gz -C /usr/local && \ 
rm -rf openjdk-11.0.3.tar.gz 

ENV JAVA_HOME /usr/local/java-11-openjdk-11
ENV PATH="$JAVA_HOME/bin:${PATH}"

RUN chmod +x /opt/sonarqube/bin/linux-x86-64/sonar.sh

RUN chmod +x start.sh

RUN groupadd -r sonarqube && useradd -r -g sonarqube sonarqube

EXPOSE 9000

CMD /user/app/start.sh ; sleep infinity

# select image maven
FROM maven:3.6.3-jdk-11-slim

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean install sonar:sonar -Dsonar.host.url=http://localhost:9000

# set the startup command to execute the jar
CMD ["java", "-jar", "target/prova-alelo-2.1.jar"]
