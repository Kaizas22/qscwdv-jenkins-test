node {
    
    def VERSIONS = ["master","v1.0","v1.1","v1.2","v2.0","v2.1"]
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
            choice(name: "TARGET", choices: TARGETS.join("\n"), description: "Choose your target."),
            choice(name: "LINUX_VERSION", choices: VERSIONS.join("\n"), description: "Choose the linux version."),
            string(name: "SVN", defaultValue: "TEST/", description: "Which SVN branch should be used?"),
            booleanParam(name: "BUILD_SDK", defaultValue: false, description: "Should a SDK be built?"),
            booleanParam(name: "BUILD_KERNEL_SDK", defaultValue: false, description: "Should a kernel SDK be built?")
        ])
    ]);

    // checkout repository with Jenkinsfile to set rootDir
    checkout scm
    def rootDir = pwd()
    
    stage('Prepare Jenkins') {
        echo 'Prepare..'
        // include groovy file to choose 
        def chooser = load "${rootDir}/groovy/chooser.groovy"

        chooser.chooseLinuxVersion(params.LINUX_VERSION) 
        chooser.chooseTarget(params.TARGET)

        version = chooser.getVersion()
        target = chooser.getTarget()
        
        currentBuild.description = "For Target ${target}"
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        def repository = load "${rootDir}/groovy/repository.groovy"
        
        repository.checkoutGit("asfjakl-", version)
        //repository.checkoutSvn(params.SVN)
    }
    stage('Prepare Environment') {
        echo 'Building..'
        sh "bash/init_env.sh 12345 ${TARGET}"
    }
    stage('Clean Firmware') {
        echo 'Clean Firmware..'
    }
    stage('Build Image') {
        echo 'Build Image..'
    }
    stage('Build Bundle') {
        echo 'Build Bundle..'
    }
    stage('Build Linux 64bit SDK') {
        echo 'Build Linux 64bit SDK..'
    }
    stage('Build Linux 32bit SDK') {
        echo 'Build Linux 32bit SDK..'
    }
    stage('Build Windows 64bit SDK') {
        echo 'Build Windows 64bit SDK..'
    }
    stage('Build 64bit Linux Kernel SDK') {
        echo 'Build 64bit Linux Kernel SDK'
    }
    stage('Build 32bit Linux Kernel SDK') {
        echo 'Build 32bit Linux Kernel SDK..'
    }
    stage('Build API Documentation') {
        echo 'Build API Documentation..'
    }
    stage('Copy Result') {
        echo 'Copy Result..'
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
