node {
    
    def versions = ["master","v1.0","v1.1","v1.2"]
    def targets = ["asfjakl-","qayedcik"]
    
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
        ],
        parameters ([
            choice(name: 'BRANCH', choices: versions.join("\n"), description: 'Some choice parameter'),
            choice(name: 'TARGET', choices: targets.join("\n"), description: 'Another choice parameter')
        ])
    ]);

    // checkout repository with Jenkinsfile
    checkout scm
    def rootDir = pwd()
    
    stage('Prepare') {
        // include additional groovy files
        def branches = load "${rootDir}/chooser.groovy"
        def repository = load "${rootDir}/repository.groovy"
        
        def BRANCH = branches.chooseBranch(params.BRANCH)
        echo "first Parameter: ${BRANCH}"
        def TARGET = branches.chooseTarget(params.TARGET)
        echo "second Parameter: ${TARGET}"
    }
    
    stage('Checkout') {
        def REPO = repository.checkout(${TARGET})
        //checkout scm: [
        //    $class: 'GitSCM',
        //    userRemoteConfigs: [[url: "https://github.com/Kaizas22/${TARGET}.git"]],
        //    branches: [[name: "${BRANCH}"]]
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
