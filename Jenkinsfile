pipeline {
    agent any
    
    //liste = ["master","v1.0","v1.1","v1.2"]
    options {
        buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    }
    parameters {
        choice(name: 'SOME_CHOICE', choices: ['master', 'master-next', 'v1.0', 'v1.1', 'v1.2'], description: 'Some choice parameter')
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    def function = load "branchChooser.groovy"
                    def branch = function.branchChooser(params.SOME_CHOICE)
                    echo 'branch'
                }
                checkout scm: [
                    $class: 'GitSCM',
                    userRemoteConfigs: [[url: "https://github.com/Kaizas22/asfjakl-.git"]],
                    branches: [[name: "${params.SOME_CHOICE}"]]
                ]
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
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
