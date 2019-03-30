node {
    checkout scm
    
    def rootDir = pwd()
    def branches = load "${rootDir}/chooser.groovy"
    
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
        choice(name: 'SOME_CHOICE', choices: ['master', 'master-next', 'v1.0', 'v1.1', 'v1.2'], description: 'Some choice parameter'),
        choice(name: 'ANOTHER_CHOICE', choices: ['asfjakl-', 'qayedcik'], description: 'Another choice parameter')
    }
    
    stage('Prepare') {
        def CHOICE1 = branches.chooseBranch(params.SOME_CHOICE)
        echo "first Parameter: ${CHOICE1}"
        def CHOICE2 = branches.chooseTarget(params.ANOTHER_CHOICE)
        echo "second Parameter: ${CHOICE2}"
    }
    
    stage('Checkout') {
        checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: "https://github.com/Kaizas22/asfjakl-.git"]],
            branches: [[name: "${CHOICE1}"]]
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
