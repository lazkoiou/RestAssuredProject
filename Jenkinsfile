pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'maven', type: 'maven' // Replace 'Maven' with your Maven installation name
    }

    stages {
        stage('Test') {
                    steps {
                        echo "MAVEN_HOME is set to: ${MAVEN_HOME}"
                        bat "${MAVEN_HOME}\\bin\\mvn clean test"
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
