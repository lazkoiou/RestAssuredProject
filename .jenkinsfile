pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }

    post {
        always {
            // Publish the Allure report
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }

}
