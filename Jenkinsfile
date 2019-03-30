node {
    checkout scm
    
    def rootDir = pwd()
    def branches = load "${rootDir}/chooser.groovy"
    
    //
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
    
    versions = ["master","v1.0","v1.1","v1.2"]
    targets = ["asfjakl-","qayedcik"]
    parameters ([
        choice(name: 'BRANCH', choices: versions.join(), description: 'Some choice parameter'),
        choice(name: 'TARGET', choices: targets.join(), description: 'Another choice parameter')
    ]);
    
    stage('Prepare') {
        echo "first Parameter: ${params.SOME_CHOICE}"
        def CHOICE1 = branches.chooseBranch(params.BRANCH)
        echo "first Parameter: ${CHOICE1}"
        echo "second Parameter: ${params.TARGET}"
        def CHOICE2 = branches.chooseTarget(params.TARGET)
        echo "second Parameter: ${CHOICE2}"
    }
    
    stage('Checkout') {
        //checkout scm: [
        //    $class: 'GitSCM',
        //    userRemoteConfigs: [[url: "https://github.com/Kaizas22/asfjakl-.git"]],
        //    branches: [[name: "${params.SOME_CHOICE}"]]
        //]
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
