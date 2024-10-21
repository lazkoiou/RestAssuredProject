pipeline {
    agent any

    parameters {
        string(name: 'SUITE_FILE', defaultValue: 'booksApiSuite.xml', description: 'Suite file to run')
    }

    stages {
        stage('Test') {
            steps {
                sh "mvn clean test -DsuiteFile=${params.SUITE_FILE}"
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
