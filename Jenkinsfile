pipeline {
    agent any
    
    //buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    //liste = ["master","v1.0","v1.1","v1.2"]
    options {
        parameters {
            choice(choices: ["master","v1.0","v1.1","v1.2"].join("\n"), description: 'Some choice parameter', name: 'SOME_CHOICE')
        }
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
