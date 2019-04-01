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

    // checkout repository with Jenkinsfile to set rootDir
    checkout scm
    def rootDir = pwd()
    
    currentBuild.description = "#${BUILD_NUMBER}, branch ${BRANCH}"
    
    stage('Prepare') {
        // include groovy file to choose something
        def branches = load "${rootDir}/chooser.groovy"

        def BRANCH = branches.chooseBranch(params.BRANCH)
        echo "first Parameter: ${BRANCH}"
        def TARGET = branches.chooseTarget(params.TARGET)
        echo "second Parameter: ${TARGET}"
    }
    
    stage('Checkout') {
        // include groovy file to checkout git repositories
        def repository = load "${rootDir}/repository.groovy"
        def REPO = repository.checkoutGit(TARGET, BRANCH)
        REPO = repository.checkoutSvn(BRANCH)
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
