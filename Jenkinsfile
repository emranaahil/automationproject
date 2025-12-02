node {

    // ============================
    // Load Maven & JDK from Tools
    // ============================
    def mvnHome = tool name: 'Maven'
    env.PATH = "${mvnHome}/bin;${env.PATH}"

    def jdkHome = tool name: 'JDK-17'
    env.JAVA_HOME = jdkHome
    env.PATH = "${jdkHome}/bin;${env.PATH}"

    // Repo
    env.GITHUB_REPO = 'https://github.com/emranaahil/automationproject'

    stage('Checkout Code') {
        git branch: 'master', url: env.GITHUB_REPO
    }

    stage('Install Dependencies') {
        bat "\"${mvnHome}\\bin\\mvn\" clean install -DskipTests"
    }

    stage('Run Tests') {
        bat "\"${mvnHome}\\bin\\mvn\" test"
    }

    stage('Publish Test Results') {
        junit '**/target/surefire-reports/*.xml'
    }

    // ===== POST ACTIONS =====
    try {
    } finally {

        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

        echo "Searching for latest TestNG report folder..."

        def latestReportDir = bat(
            script: 'for /f "delims=" %i in (\'dir /ad /b /o-d reports\') do @echo %i & exit /b',
            returnStdout: true
        ).trim()

        if (latestReportDir) {
            echo "Latest TestNG Report Folder: ${latestReportDir}"

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: "reports/${latestReportDir}",
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
            ])

        } else {
            echo "❌ No TestNG HTML report found."
            bat "dir reports"
        }
    }
}
