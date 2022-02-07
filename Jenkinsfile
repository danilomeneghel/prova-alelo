pipeline {

   environment {
        PATH = "$PATH:/usr/bin"
   }
   
   agent any

   stages {
       stage('Build') {
           steps {
              echo "Starting the build"
              sh "/usr/bin/docker-compose up"
           }
       }
   }
   post {
      always {
         sh "/usr/bin/docker-compose down || true"
      }
   }   
}
