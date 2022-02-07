pipeline {

   environment {
        PATH = "$PATH:/usr/bin"
   }
   
   agent any

   stages {
       stage('Build') {
           steps {
              echo "Starting the build"
              echo "PATH is: $PATH"
              sh "/usr/bin/docker-compose up"
           }
       }
   }
   post {
      always {
         sh "docker-compose down || true"
      }
   }   
}
