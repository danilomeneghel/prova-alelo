pipeline {

   agent any
   
   stages {
       stage('Checkout Project') {
           steps {
              echo "Starting the Checkout Project"
              sh "git clone https://github.com/danilomeneghel/prova-alelo.git"
           }
       }
       stage('Docker Build and Run SonarQube') {
       	   steps {
       	      echo "Starting the Build SonarQube"
       	      sh "pwd"
	      dir('prova-alelo/sonarqube') {
	        sh "pwd"
	        sh "docker build -t sonarqube:8.4.1-community ."
       	        sh "docker run -p 9000:9000 -d sonarqube:8.4.1-community"
	      }
	      sh "./mvnw clean install sonar:sonar -Dsonar.host.url=http://localhost:9000"
       	   }
       }
       stage('Docker Build Project') {
       	   steps {
       	      echo "Starting the Build Project"
       	      sh "pwd"
       	      sh "ls"
       	      sh "docker build -t prova-alelo ."
       	   }
       }
       stage('Docker Run') {
           steps {
              echo "Starting the Deploy"
              sh "docker run -p 8181:8181 -d prova-alelo"
           }
       }
   } 
}
