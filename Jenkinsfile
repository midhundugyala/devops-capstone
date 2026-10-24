pipeline {
  agent any
  tools { maven 'maven3'; jdk 'jdk21' }
  environment { IMAGE = 'taskboard:1.0' }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build') { steps { sh './mvnw clean package -DskipTests=false' } }
    stage('Test') { steps { sh './mvnw test' } }
    stage('Docker Build') { steps { sh 'docker build -t taskboard:1.0 .' } }
    stage('Deploy') { steps { sh 'docker rm -f taskboard || true; docker run -d --name taskboard -p 8081:8080 taskboard:1.0' } }
  }
  post { always { echo 'Pipeline Done' } }
}
