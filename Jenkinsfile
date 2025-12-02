pipeline {
    agent any

    tools {
        maven 'Maven'    // Use the Maven tool configured in Jenkins
        jdk 'JDK-17'     // Use the JDK 17 configured in Jenkins
    }

    environment {
        GITHUB_REPO = 'https://github.com/emranaahil/automationproject'
        EMAIL_RECIPIENTS = 'youremail@example.com'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${GITHUB_REPO}"
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

            // Publish TestNG HTML report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',            // <-- your reports folder
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
            ])
        }
    }
}
