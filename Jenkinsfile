pipeline {

   agent any
   
   stages {
       stage('Checkout Project') {
           steps {
              echo "Starting the Checkout Project"
              sh "git clone https://github.com/danilomeneghel/prova-alelo.git"
              sh "cd prova-alelo"
              sh "ls"
           }
       }
       stage('Docker Build and Run SonarQube') {
       	   steps {
       	      echo "Starting the Build SonarQube"
       	      sh "cd sonarqube"
       	      sh "ls"
       	      sh "docker build --tag=sonarqube ."
       	      sh "docker run -p 9000:9000 -ti sonarqube"
       	   }
       }
       stage('Docker Build Project') {
       	   steps {
       	      echo "Starting the Build Project"
       	      sh "cd .."
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
