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
            // Use Groovy to safely get the latest TestNG report folder
            def reportsDir = new File("${env.WORKSPACE}/reports")
            def latestReportDir = ''
            
            if (reportsDir.exists() && reportsDir.isDirectory()) {
                def dirs = reportsDir.listFiles().findAll { it.isDirectory() }
                if (dirs) {
                    latestReportDir = dirs.max { it.lastModified() }.name
                }
            }

            if (latestReportDir) {
                echo "Latest TestNG Report Directory: ${latestReportDir}"

                // Publish HTML report
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
