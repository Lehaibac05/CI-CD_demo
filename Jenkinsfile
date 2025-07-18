pipeline {
    agent any

    stages {
        stage('clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Lehaibac05/CI-CD_demo.git'
            }
        }

        stage('Copy to IIS Folder') {
            steps {
                echo 'Stopping IIS...'
                bat 'iisreset /stop'

                echo 'Cleaning existing deploy folder...'
                bat 'if exist C:\\WOR_cicd rd /s /q C:\\WOR_cicd'

                echo 'Creating IIS folder...'
                bat 'mkdir C:\\WOR_cicd'

                echo 'Copying to IIS folder...'
                bat 'xcopy /E /Y /I /R "%WORKSPACE%\\*" "C:\\WOR_cicd\\"'

                echo 'Starting IIS again...'
                bat 'iisreset /start'
            }
        }

        stage('Ensure IIS Site Exists') {
            steps {
                powershell '''
                    Import-Module WebAdministration
                    $siteName = "WOR_cicd"
                    $sitePath = "C:\\WOR_cicd"
                    $sitePort = 8089

                    if (-not (Test-Path "IIS:\\Sites\\$siteName")) {
                        New-Website -Name $siteName -Port $sitePort -PhysicalPath $sitePath -Force
                    } else {
                        Write-Host "Website $siteName already exists"
                    }
                '''
            }
        }
    }
}
