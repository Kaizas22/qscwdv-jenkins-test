node {
    
    def VERSIONS = ["master","1.0","1.1","1.2","2.0","2.1"]
    def version
    def TARGETS = ["A","B","C","D","E"]
    def target
    
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
            choice(name: 'TARGET', choices: TARGETS.join("\n"), description: 'Choose your target.'),
            choice(name: 'LINUX_VERSION', choices: VERSIONS.join("\n"), description: 'Choose the linux version.'),
            string(name: 'SVN', defaultValue: "TEST/", description: 'Which SVN branch should be used?')
        ])
    ]);

    // checkout repository with Jenkinsfile to set rootDir
    checkout scm
    def rootDir = pwd()
    
    stage('Prepare') {
        echo 'Prepare..'
        // include groovy file to choose 
        def chooser = load "${rootDir}/groovy/chooser.groovy"

        chooser.chooseLinuxVersion(params.LINUX_VERSION) 
        chooser.chooseTarget(params.TARGET)

        target = chooser.getTarget()
        version = chooser.getVersion()
        echo "Jenkins says: ${target}, ${version}"
        
        currentBuild.description = "For Target ${target}"
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        //def repository = load "${rootDir}/groovy/repository.groovy"
        //repository.checkoutGit("asfjakl-", version)
        //repository.checkoutSvn(params.SVN)
    }
    stage('Build') {
        echo 'Building..'
        //sh "bash/init_env.sh 12345 ${TARGET}"
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
    currentBuild.displayName = "#${BUILD_NUMBER}: branch ${version}"
}
