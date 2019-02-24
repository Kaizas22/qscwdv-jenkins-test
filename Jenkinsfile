pipeline {
    agent any
    
    //buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    //liste = ["master","v1.0","v1.1","v1.2"]
    options {
        buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    }
    parameters {
        choice(name: 'SOME_CHOICE', choices: ['master', 'v1.0', 'v1.1', 'v1.2'], description: 'Some choice parameter')
    }
    
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "${params.SOME_CHOICE}"
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
