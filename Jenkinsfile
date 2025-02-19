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
                sh 'mvn clean package' // Example for Java project
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
