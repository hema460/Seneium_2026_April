pipeline {
    // 1. Where to run
    agent any 

    stages {
        // 2. Pull the code
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        // 3. Run the automation
        stage('Build & Test') {
            steps {
                // 'bat' is for Windows; use 'sh' for Linux/Mac
                bat "mvn clean test -PRegression -Dbrowser=${browserName}"
            }
        }
    }

    // 4. Handle Results (The "Insurance Policy")
    post {
        always {
            // This runs even if a test fails in the previous stage
            publishHTML target: [
               reportName: 'Test Report',
            reportDir:  'Screenshots',
            reportFiles: 'index.html'
            ]
        }
    }
}
