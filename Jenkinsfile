node {
    
    def VERSIONS = ['master','v1.0','v1.1','v1.2','v2.0','v2.1']
    def version
    def TARGETS = ['A','B','C','D','E']
    def target
    
    def repo
    def machine
    def platform
    def device
    
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
            choice(name: 'TARGET', choices: TARGETS.join('\n'), description: 'Choose your target.'),
            choice(name: 'LINUX_VERSION', choices: VERSIONS.join('\n'), description: 'Choose the linux version.'),
            string(name: 'SVN', defaultValue: 'TEST/', description: 'Which SVN branch should be used?'),
            booleanParam(name: 'BUILD_SDK', defaultValue: false, description: 'Should a SDK be built?'),
            booleanParam(name: 'BUILD_KERNEL_SDK', defaultValue: false, description: 'Should a SDK for the kernel be built?'),
            booleanParam(name: 'BOOTLOADER_UPDATE', defaultValue: false, description: 'Is a bootloader update necessary?'),
            booleanParam(name: 'SPECIAL_BUILD', defaultValue: false, description: 'A special build is needed?')
        ])
    ]);

    // checkout repository with Jenkinsfile to set rootDir
    checkout scm
    def rootDir = pwd()
    
    stage('Prepare Jenkins') {
        // include groovy file to choose 
        def chooser = load "${rootDir}/groovy/chooser.groovy"

        chooser.chooseLinuxVersion(params.LINUX_VERSION) 
        chooser.chooseTarget(params.TARGET)

        version = chooser.getVersion()
        target = chooser.getTarget()
        echo "Repo: ${target.repo}"
        
        repo = 'axc'
        //repo = chooser.getRepo()
        machine = 'axcf'
        //machine = chooser.getMachine()
        platform = 'axcf'
        //platform = chooser.getPlatform()
        device = 'axcf'
        //device = chooser.getDevice()
        
        currentBuild.description = "For Device ${device}"
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        def repository = load "${rootDir}/groovy/repository.groovy"
        echo "Checkout poky Repository"
        echo "Checkout meta-openembedded Repository"
        echo "Checkout meta-mingw Repository"
        echo "Checkout meta-security Repository"
        echo "Checkout meta-rauc Repository"
        echo "Checkout meta-custom Repository"
        echo "Checkout meta-fw Repository"
        echo "Checkout meta-${repo}_bsp Repository"
        echo "Checkout meta-${repo}_product Repository"
        repository.checkoutGit('asfjakl-', version)
        //repository.checkoutSvn(params.SVN)
    }
    stage('Prepare Environment') {
        sh "bash/init_env.sh 12345 ${machine}"
    }
    stage('Clean Firmware') {
        echo 'Clean Firmware..'
    }
    stage('Build Image') {
        sh "bash/build_image.sh ${device}"
    }
    stage('Build Bundle') {
        sh "bash/build_bundle.sh ${platform}"
    }
    stage('Build Linux 64bit SDK') {
        if (params.BUILD_SDK == true) {
            sh "bash/build_sdk.sh ${platform}-image-sdk x86_64"
        }
        else {
            echo 'Skip Build Linux 64bit SDK'
        }
    }
    stage('Build Linux 32bit SDK') {
        if (params.BUILD_SDK == true) {
            sh "bash/build_sdk.sh ${platform}-image-sdk i686"
        }
        else {
            echo 'Skip Build Linux 32bit SDK'
        }
    }
    stage('Build Windows 64bit SDK') {
        if (params.BUILD_SDK == true) {
            sh "bash/build_sdk.sh ${platform}-image-mingw mingw32-64"
        }
        else {
            echo 'Skip Build Windows 64bit SDK'
        }
    }
    stage('Build 64bit Linux Kernel SDK') {
        if (params.BUILD_KERNEL_SDK == true) {
            sh "bash/build_sdk.sh ${platform}-image-kernel-sdk x86_64"
        }
        else {
            echo 'Skip Build 64bit Linux Kernel SDK'
        }
    }
    stage('Build 32bit Linux Kernel SDK') {
        if (params.BUILD_KERNEL_SDK == true) {
            sh "bash/build_sdk.sh ${platform}-image-kernel-sdk i686"
        }
        else {
            echo 'Skip Build 32bit Linux Kernel SDK'
        }
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
