post {
    always {

        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

        script {
            echo "Publishing TestNG report..."

            // TestNG report is located in target/surefire-reports
            def reportFolder = "target/surefire-reports"

            if (fileExists("${reportFolder}/emailable-report.html")) {

                echo "TestNG Report Found. Publishing..."

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
}
