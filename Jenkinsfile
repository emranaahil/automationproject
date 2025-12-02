pipeline {
    agent any

    tools {
        maven 'Maven'    // Use the Maven tool configured in Jenkins
        jdk 'JDK-17'     // Use the JDK 17 configured in Jenkins
    }

    environment {
        GITHUB_REPO = 'https://github.com/emranaahil/automationproject'  // Your GitHub repo URL
        EMAIL_RECIPIENTS = 'youremail@example.com'  // Your email for report notifications
    }

    stages {
        // 1. Checkout the code from the GitHub repository
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${GITHUB_REPO}"
            }
        }

        // 2. Install project dependencies (skip tests during installation)
        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        // 3. Run the TestNG tests
        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        // 4. Publish test results (JUnit format)
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    // Post-build actions
post {
    always {
        // Archive JAR files
        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

       script {
            // Dynamically get the latest timestamped report directory
            def latestReportDir = bat(
                script: 'for /f "delims=" %i in (\'dir /b /ad /o-d test-output\') do (echo %i & goto :done) & :done',
                returnStdout: true
            ).trim()

            echo "Latest TestNG Report Directory: ${latestReportDir}"

            // Publish the HTML report from the latest directory
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: "test-output/${latestReportDir}",
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
    }
}
}

