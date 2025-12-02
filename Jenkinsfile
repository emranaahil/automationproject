node {

    // ====================
    // Tools Configuration
    // ====================
    env.GITHUB_REPO = 'https://github.com/emranaahil/automationproject'
    tool name: 'Maven'
    tool name: 'JDK-17'

    stage('Checkout Code') {
        git branch: 'master', url: env.GITHUB_REPO
    }

    stage('Install Dependencies') {
        bat "mvn clean install -DskipTests"
    }

    stage('Run Tests') {
        bat "mvn test"
    }

    stage('Publish Test Results') {
        junit '**/target/surefire-reports/*.xml'
    }

    // ================================
    // ALWAYS RUN POST ACTIONS
    // ================================
    try {
        // test stages go here
    } finally {

        echo "Archiving JAR files..."
        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

        echo "Searching for latest TestNG report folder..."

        // Find newest folder inside 'reports'
        def latestReportDir = bat(
            script: 'for /f "delims=" %i in (\'dir /ad /b /o-d reports\') do @echo %i & exit /b',
            returnStdout: true
        ).trim()

        if (latestReportDir) {

            echo "Latest TestNG Report Folder: reports/${latestReportDir}"

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: "reports/${latestReportDir}",
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
            ])

        } else {
            echo "❌ No TestNG HTML report found!"
            bat "dir reports"
        }
    }
}
