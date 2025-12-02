pipeline {
    agent any

    tools {
        maven 'Maven'    // Jenkins configured Maven
        jdk 'JDK-17'     // Jenkins configured JDK 17
    }

    environment {
        GITHUB_REPO = 'https://github.com/emranaahil/automationproject'  
        EMAIL_RECIPIENTS = 'youremail@example.com'
    }

    stages {
        // 1. Checkout the code from GitHub
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${GITHUB_REPO}"
            }
        }

        // 2. Install dependencies (skip tests)
        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        // 3. Run TestNG tests
        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        // 4. Publish JUnit test results
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    // Post-build actions
    post {
        always {
            // Archive built JAR artifacts
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

            script {
                // Find the latest folder in "reports" (Windows compatible)
                def latestReportDir = bat(
                    script: '@for /f "delims=" %%i in (\'dir /b /ad /o-d reports\') do @echo %%i & exit /b',
                    returnStdout: true
                ).trim()

                echo "Latest TestNG Report Directory: ${latestReportDir}"

                if (latestReportDir) {
                    // Publish the HTML report from the latest folder
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
