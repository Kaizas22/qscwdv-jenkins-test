pipeline {
    agent any
    
    options {
        buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    }
    
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                git url: "https://github.com/Kaizas22/asfjakl-.git", branch: "master"
                checkout scm: [
                    $class: 'GitSCM',
                    userRemoteConfigs: [[url: "https://github.com/Kaizas22/asfjakl-.git"]],
                    branches: [[name: "v1.2"]]
                ]
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
