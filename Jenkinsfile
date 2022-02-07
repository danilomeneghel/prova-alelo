pipeline {

   agent any

   stages {
       stage('Teste') {
           steps {
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
