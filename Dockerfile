# select parent image maven
FROM maven:3.6.3-jdk-11-slim

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean package

# set the startup command to execute the jar
CMD ["java", "-jar", "target/prova-alelo-2.1.jar"]

# install image docker-compose
RUN aptitude -y install docker-compose
RUN ln -s /usr/local/bin/docker-compose /compose/docker-compose
