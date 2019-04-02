node {
    
    def versions = ["master","v1.0","v1.1","v1.2"]
    def targets = ["asfjakl-","qayedcik"]
    
    properties([
        [
            $class: 'BuildDiscarderProperty',
            strategy: [
                $class: 'LogRotator',
                artifactDaysToKeepStr: '',
                artifactNumToKeepStr: '5', 
                daysToKeepStr: '',
                numToKeepStr: '5'
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
    
    currentBuild.displayName = "#${BUILD_NUMBER}, branch ${BRANCH}"
    currentBuild.description = "Hello World"
    
    stage('Prepare') {
        echo 'Prepare..'
        // include groovy file to choose something
        def branches = load "${rootDir}/groovy/chooser.groovy"

        def BRANCH = branches.chooseBranch(params.BRANCH)
        def TARGET = branches.chooseTarget(params.TARGET)
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        def repository = load "${rootDir}/groovy/repository.groovy"
        repository.checkoutGit(TARGET, BRANCH)
        //repository.checkoutSvn(BRANCH)
    }
    stage('Build') {
        echo 'Building..'
        sh "bash/init_env.sh"
    }
    stage('Test') {
        echo 'Testing..'
    }
    stage('Deploy') {
        echo 'Deploying....'
        archiveArtifacts '...'
        cifsPublisher (
            publishers: [[
                configName: '...',
                transfers: [[
                    cleanRemote: false,
                    excludes: '',
                    flatten: false,
                    makeEmptyDirs: false,
                    noDefaultExcludes: false,
                    patternSeparator: '[, ]+',
                    remoteDirectory: '...',
                    remoteDirectorySDF: false,
                    removePrefix: '...',
                    sourceFiles: '...'
                ]],
                usePromotionTimestamp: false,
                useWorkspaceInPromotion: false,
                verbose: false
            ]]
        )    
    }
}
