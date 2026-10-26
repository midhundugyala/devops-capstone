pipeline {
    agent any
    environment {
        JAVA_HOME = '/opt/jdk21'
        PATH = "/opt/jdk21/bin:/usr/share/maven/bin:$PATH"
        IMAGE = 'mithun2011dmk/taskboard:latest'
    }
    stages {
        stage('Checkout') { steps { checkout scm } }
        stage('Build') { steps { sh 'mvn clean package -DskipTests=false' } }
        stage('Docker Build') { steps { sh 'docker build -t $IMAGE .' } }
        stage('Docker Push') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-token', variable: 'TOKEN')]) {
                    sh 'echo $TOKEN | docker login -u mithun2011dmk --password-stdin'
                    sh 'docker push $IMAGE'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker rm -f taskboard || true'
                sh 'docker run -d --name taskboard -p 8081:8080 $IMAGE'
            }
        }
    }
    post { always { echo 'Pipeline Done' } }
}
