pipeline {

   environment {
        PATH = "$PATH:/usr/local/bin"
   }
   
   agent any

   stages {
       stage('Build') {
           steps {
              echo "Starting the build"
              echo "PATH is: $PATH"
              sh "/usr/local/bin/docker-compose up"
           }
       }
   }
   post {
      always {
         sh "docker-compose down || true"
      }
   }   
}
