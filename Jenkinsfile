node {
    
    def VERSIONS = ['master','2019.0 LTS','2019.3']
    def version
    def TARGETS = ['AXC F 2152','B','RFC 4072S','D','E']
    def target
    
    def repository
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
            string(name: 'BUILD_DESCRIPTION', defaultValue: '', description: 'Descripe your build.'),
            string(name: 'FW_SVN_BRANCH', defaultValue: 'trunk/', description: 'Which SVN branch should be used?'),
            booleanParam(name: 'BUILD_SDK', defaultValue: false, description: 'Should a SDK be built?'),
            booleanParam(name: 'BUILD_KERNEL_SDK', defaultValue: false, description: 'Should a SDK for the kernel be built?'),
            booleanParam(name: 'ALLOW_ROOT', defaultValue: true, description: 'Should root login allowed?'),
            booleanParam(name: 'TRUST_RELEASE_SIGNATURE', defaultValue: false, description: 'Trust update container the release signature?'),
            booleanParam(name: 'SIGN_RELEASE_SIGNATURE', defaultValue: false, description: 'Should the update container be signed with release signature?'),
            booleanParam(name: 'BOOTLOADER_UPDATE', defaultValue: false, description: 'Is a bootloader update necessary?'),
            booleanParam(name: 'SPECIAL_BUILD', defaultValue: false, description: 'A special build is needed?'),
            booleanParam(name: 'FW_UNIT_TEST', defaultValue: true, description: 'Should the unit tests be a part of the rootfs?'),
            booleanParam(name: 'FW_LICENCE_EVALUATION', defaultValue: false, description: 'Build with license evaluation mode?'),
        ])
    ]);

    env.FW_VERSION_STATE = "alpha"
    env.FW_SVN_BRANCH = "${FW_SVN_BRANCH}"
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
        
        repository = chooser.getRepository()
        machine = chooser.getMachine()
        platform = chooser.getPlatform()
        device = chooser.getDevice()
    }
    
    stage('Checkout') {
        // include groovy file to checkout repositories
        def checkout = load "${rootDir}/groovy/checkout.groovy"
        
        // check version to get correct branches for every version
        checkout.checkBranches(version)
        def yocto_version = checkout.getYoctoVersion()
        echo "Yocto Version: ${yocto_version}"
        def yocto_upstream_branch = checkout.getUpstreamBranch()
        echo "Yocto Upstream Branch: ${yocto_upstream_branch}"
        def yocto_branch = checkout.getYoctoBranch()
        echo "Yocto Branch: ${yocto_branch}"
        
        // checkout git repositories
        echo "checkout.checkoutGit(yocto-meta/poky, yocto_version)"
        echo "checkout.checkoutGit(yocto-meta/meta-openembedded, yocto_upstream_branch)"
        echo "checkout.checkoutGit(yocto-meta/meta-mingw, yocto_upstream_branch)"
        echo "checkout.checkoutGit(yocto-meta/meta-security, yocto_upstream_branch)"
        echo "checkout.checkoutGit(yocto-meta/meta-rauc, yocto_upstream_branch)"
        echo "checkout.checkoutGit(yocto-mymeta/meta-custom, yocto_branch)"
        echo "checkout.checkoutGit(yocto-mymeta/meta-fw, yocto_branch)"
        echo "checkout.checkoutGit(targets/${platform}/meta-${repository}_bsp, yocto_branch)"
        echo "checkout.checkoutGit(targets/${platform}/meta-${repository}_product, yocto_branch)"
        checkout.checkoutGit('asfjakl-', 'master')
        
        // checkout svn repositories
        echo "checkout.checkoutSvn(params.FW_SVN_BRANCH)"
    }
    stage('Prepare Environment') {
        sh "bash/init_env.sh 12345 ${machine} ${device}"
    }
    stage('Clean Firmware') {
        sh "bash/fw_cleanup.sh"
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
        sh 'bash/build_doc.sh'
    }
    stage('Copy Results') {        
        // include groovy file to copy results
        def result = load "${rootDir}/groovy/result.groovy"
        
        result.copyResults("12345", machine, device)
        result.archiveResults()
    }
    currentBuild.displayName = "#${BUILD_NUMBER}: [${device}, ${version}] ${params.FW_SVN_BRANCH} ${params.FW_VERSION_STATE}"
    currentBuild.description = "${BUILD_DESCRIPTION}"
}
