pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/chetansonawane40/EMS-Fullstack-App.git'
            }
        }
        stage('Build') {
    steps {
        script {
            def mvnHome = tool 'Maven 3' // Use the name you set in Jenkins
            sh "${mvnHome}/bin/mvn clean package"
        }
    }
}
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo Deploying Application...'
            }
        }
    }
}
