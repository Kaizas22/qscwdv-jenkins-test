import branchChooser.groovy

node {
    
    //liste = ["master","v1.0","v1.1","v1.2"]
    properties([
        [
            $class: 'BuildDiscarderProperty',
            strategy: [
                $class: 'LogRotator',
                artifactDaysToKeepStr: '',
                artifactNumToKeepStr: '', 
                daysToKeepStr: '',
                numToKeepStr: '3'
            ]
        ]
    ]);    
    parameters {
        choice(name: 'SOME_CHOICE', choices: ['master', 'master-next', 'v1.0', 'v1.1', 'v1.2'], description: 'Some choice parameter')
    }
    def chosenOne = choose_yocto_branch(params.SOME_CHOICE)
    echo "${chosenOne}"
    
    stage('Checkout') {
        checkout scm
        checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: "https://github.com/Kaizas22/asfjakl-.git"]],
            branches: [[name: "${params.SOME_CHOICE}"]]
        ]
    }
    stage('Build') {
        echo 'Building..'
    }
    stage('Test') {
        echo 'Testing..'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}
