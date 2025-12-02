pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK-17'
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

            // Archive JAR
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

            echo "Searching for latest TestNG report folder..."

            script {

                // Find the newest directory inside reports/ using a Windows shell command
                def latestReportDir = bat(
                    script: 'for /f "delims=" %i in (\'dir /ad /b /o-d reports\') do @echo %i & exit /b',
                    returnStdout: true
                ).trim()

                if (latestReportDir) {
                    echo "Latest TestNG Report Directory: ${latestReportDir}"

                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: "reports/${latestReportDir}",
                        reportFiles: 'emailable-report.html',
                        reportName: 'TestNG Report'
                    ])
                } else {
                    echo "No TestNG report found to publish."
                }
            }
        }
    }
}
