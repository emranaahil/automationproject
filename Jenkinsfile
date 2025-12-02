node {

    // ============================
    // Load Maven & JDK from Tools
    // ============================
    def mvnHome = tool name: 'Maven'
    env.PATH = "${mvnHome}\\bin;${env.PATH}"

    def jdkHome = tool name: 'JDK-17'
    env.JAVA_HOME = jdkHome
    env.PATH = "${jdkHome}\\bin;${env.PATH}"

    // Repo
    env.GITHUB_REPO = 'https://github.com/emranaahil/automationproject'

    try {
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

    } finally {
        // ============================
        // POST ACTIONS
        // ============================
        echo "Archiving JAR files..."
        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

        echo "Publishing TestNG report..."
        def reportFolder = 'target/surefire-reports'

        if (fileExists("${reportFolder}/emailable-report.html")) {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: reportFolder,
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
            ])
        } else {
            echo "❌ TestNG emailable-report.html not found in ${reportFolder}"
            echo "Available files:"
            bat "dir ${reportFolder}"
        }
    }
}
