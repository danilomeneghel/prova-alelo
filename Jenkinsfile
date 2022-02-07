pipeline {

   environment {
        PATH = "$PATH:/usr/bin"
   }
   
   agent any

   stages {
       stage('Checkout') {
           steps {
              echo "Starting the Checkout"
              git url: "https://github.com/danilomeneghel/prova-alelo.git"
           }
       }
       stage('Test') {
           steps {
              echo "Starting the Test"
              sh "./mvnw test"
           }
       }
       stage('Deploy') {
           steps {
              echo "Starting the Build"
              sh "docker-compose up"
           }
       }
   }
   post {
      always {
         sh "./docker-compose down || true"
      }
   }   
}
