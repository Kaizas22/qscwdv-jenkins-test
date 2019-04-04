node {
    
    def VERSIONS = ["master","v1.0","v1.1","v1.2","2.0","2.1"]
    def TARGETS = ["A","B","C","D","E"]
    
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
            string(name: 'SVN', defaultValue: "TEST/", description: 'What SVN?'),
            choice(name: 'VERSION', choices: VERSIONS.join("\n"), description: 'Some choice parameter'),
            choice(name: 'TARGET', choices: TARGETS.join("\n"), description: 'Another choice parameter')
        ])
    ]);

    // checkout repository with Jenkinsfile to set rootDir
    checkout scm
    def rootDir = pwd()
    
    stage('Prepare') {
        echo 'Prepare..'
        // include groovy file to choose something
        def branches = load "${rootDir}/groovy/chooser.groovy"

        BRANCH = branches.chooseBranch(params.VERSION)
        TARGET = branches.chooseTarget(params.TARGET)
        
        currentBuild.description = "For Target ${TARGET}"
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        def repository = load "${rootDir}/groovy/repository.groovy"
        repository.checkoutGit("asfjakl-", BRANCH)
        //repository.checkoutSvn(params.SVN)
    }
    stage('Build') {
        echo 'Building..'
        sh "bash/init_env.sh 12345 ${TARGET}"
    }
    stage('Test') {
        echo 'Testing..'
    }
    stage('Deploy') {
        echo 'Deploying....'
        /*archiveArtifacts '...'
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
        )*/    
    }
    currentBuild.displayName = "#${BUILD_NUMBER}, branch ${VERSION}"
}
