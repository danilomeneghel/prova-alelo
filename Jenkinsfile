pipeline {

   agent any

   stages {
       stage('Build') {
           steps {
              echo "Starting the build"
              sh "docker-compose build"
              sh "docker-compose up -d"
           }
       }
   }
   post {
      always {
         sh "docker-compose down || true"
      }
   }   
}
