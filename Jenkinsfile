pipeline {
    agent any
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
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
                    branches: [[name: "v1.0"]]
                ]
                git url "https://github.com/Kaizas22/asfjakl-.git", branches: "v1.0"
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
