pipeline {

   agent any
   
   stages {
       stage('Checkout') {
           steps {
              echo "Starting the Checkout"
              sh "git clone https://github.com/danilomeneghel/prova-alelo.git"
              sh "cd prova-alelo"
           }
       }
       stage('Docker Build Project') {
       	   steps {
       	      echo "Starting the Build Project"
       	      sh "docker build -t prova-alelo ."
       	   }
       }
       stage('Docker Build SonarQube') {
       	   steps {
       	      echo "Starting the Build SonarQube"
       	      sh "docker build -t bitnami/sonarqube:latest 'https://github.com/bitnami/bitnami-docker-sonarqube.git#master:9/debian-10'"
       	   }
       }
       stage('Docker Run') {
           steps {
              echo "Starting the Deploy"
              sh "docker run -p 8181:8181 -d prova-alelo"
              sh "docker run -p 9000:9000 -d bitnami/sonarqube"
           }
       }
   } 
}
