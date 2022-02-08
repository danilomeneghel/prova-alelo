pipeline {

   agent any
   
   stages {
       stage('Checkout') {
           steps {
              echo "Starting the Checkout"
              sh "git clone https://github.com/danilomeneghel/prova-alelo.git"
              sh "cd ./prova-alelo"
           }
       }
       stage('Test') {
           steps {
              echo "Starting the Test"
              sh "./mvnw test"
           }
       }
       stage('Docker Build') {
       	   steps {
       	      echo "Starting the Build"
       	      sh "docker build -t api ."
       	   }
       }
       stage('Docker Run') {
           steps {
              echo "Starting the Deploy"
              sh "docker run -p 8181:8181 -d api"
           }
       }
   } 
}
