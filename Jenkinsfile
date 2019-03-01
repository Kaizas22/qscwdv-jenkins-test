def choose_yocto_branch(version) {
    def result
    switch (version) {
        case 'master':
            result = 'master'
            break
        case 'master-next':
        case 'v1.2':
            result = 'v1.2'
            break
        case 'v1.0':
        case 'v1.1':
            result = 'master-next'
            break
        default:
            result = 'v1.0'
            break
    }
    return this
}

node {
    
    //liste = ["master","v1.0","v1.1","v1.2"]
    //options {
    //    buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '5', artifactDaysToKeepStr: '', artifactNumToKeepStr: '5'))
    //}
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
    //choose_yocto_branch(params.SOME_CHOICE)
    sh 'scripts/versionChooser.sh'
    
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
