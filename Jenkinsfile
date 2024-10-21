pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean test"
            }
        }

        stage('Report') {
            steps {
                script {
                    // Check if allure-results directory exists
                    if (fileExists('allure-results')) {
                        // Generate the Allure report
                        sh "allure generate allure-results --clean -o allure-report"
                        echo 'Allure report generated successfully.'
                    } else {
                        echo 'No allure-results found. Skipping report generation.'
                    }
                }
            }
        }

    }

    post {
        always {
            // Publish the Allure report
            allure commandline: 'Allure', results: [[path: 'target/allure-results']]
        }
    }

}
