pipeline {
    agent any

    tools {
        maven 'Maven 3' // Use the configured Maven tool
        nodejs 'NodeJS' // Install Node.js in Jenkins (Optional)
    }

    stages {
        stage('Check NodeJS Version') {
            steps {
                sh 'node -v'
                sh 'npm -v'
            }
        }
        stage('Check Recent Changes') {
            steps {
                sh 'git log -1 --pretty=format:"%h - %an, %ar : %s"'
              }
        }
        stage('Checkout Code') {
            steps {
                git 'https://github.com/chetansonawane40/EMS-Fullstack-App.git'
            }
        }

        stage('Build Angular Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build --prod'
                }
            }
        }

        stage('Build Spring Boot Backend') {
            steps {
                dir('backend') {
                    script {
                        def mvnHome = tool 'Maven 3'
                        sh "${mvnHome}/bin/mvn clean package"
                    }
                }
            }
        }

        stage('Deploy Backend') {
            steps {
                dir('backend') {
                    sh 'java -jar target/*.jar &'
                }
            }
        }

        stage('Deploy Frontend') {
            steps {
                dir('frontend') {
                    sh 'npx http-server dist/frontend -p 4200 &'
                }
            }
        }
    }

    post {
        success {
            echo "Build and Deployment Completed Successfully!"
        }
        failure {
            echo "Build Failed!"
        }
    }
}
