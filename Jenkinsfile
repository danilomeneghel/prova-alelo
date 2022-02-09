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
       stage('Docker Deploy') {
       	   steps {
       	      echo "Starting the Deploy"
       	      sh "docker-compose up"
       	   }
       }
   } 
}
