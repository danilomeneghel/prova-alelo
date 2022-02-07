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
       stage('Build') {
           steps {
              echo "Starting the Build"
              sh "./deploy.sh"
           }
       }
   }
   post {
      always {
         sh "./docker-compose down || true"
      }
   }   
}
