pipeline {
    agent any

    parameters {
        string(name: 'SUITE_FILE', defaultValue: "api/booksApiSuite.xml", description: 'Suite file to run (Leave empty for default)')
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean test -DsuiteFile=${params.SUITE_FILE ?: "api/booksApiSuite.xml"'"}"
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
            allure commandline: 'Allure', results: [[path: 'target/allure-results']]
        }
    }

}
