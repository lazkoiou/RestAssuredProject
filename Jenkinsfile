pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'maven', type: 'maven' // Replace 'Maven' with your Maven installation name
    }

    stages {
        stage('Build') {
            steps {
                echo "MAVEN_HOME is set to: ${MAVEN_HOME}"
                sh "${MAVEN_HOME}/bin/mvn -version" // Check if Maven is accessible
                bat '"${MAVEN_HOME}/bin/mvn" clean install -DskipTests'
            }
        }

        stage('Test') {
                    steps {
                        bat '"${MAVEN_HOME}/bin/mvn" clean test'
                    }
                }

        stage('Report') {
            steps {
                script {
                    // Check if allure-results directory exists
                    if (fileExists('allure-results')) {
                        // Generate the Allure report
                        bat "allure generate allure-results --clean -o allure-report"
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
