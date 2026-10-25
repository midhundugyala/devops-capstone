pipeline {
  agent any

  environment {
    JAVA_HOME = '/opt/jdk21'
    PATH = '/opt/jdk21/bin:/usr/share/maven/bin:$PATH'
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build') {
      steps { sh 'mvn clean package -DskipTests=false' }
    }
    stage('Test') {
      steps { sh 'mvn test' }
    }
    stage('Docker Build') {
      steps { sh 'docker build -t taskboard:1.0 .' }
    }
    stage('Deploy') {
      steps { sh 'docker rm -f taskboard || true; docker run -d --name taskboard -p 8081:8080 taskboard:1.0' }
    }
  }
  post {
    always { echo 'Pipeline Done' }
  }
}
