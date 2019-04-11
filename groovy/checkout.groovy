GIT_URL = 'https://github.com/Kaizas22/'
SVN_URL = 'https://svn.test.com/'

def yocto_version 
def yocto_upstream_branch
def yocto_branch

def checkBranches(version) {
    switch (version) {
        case 'v1.0':
            yocto_version = 'v2.2.1'
            yocto_upstream_branch = 'v1.0'
            yocto_branch = 'v1.0'
            break
        case 'v1.1':
            yocto_version = 'v2.3.3'
            yocto_upstream_branch = 'v1.0'
            yocto_branch = 'v1.1'
            break
        case 'v1.2':
            yocto_version = 'v2.3.4'
            yocto_upstream_branch = 'v1.0'
            yocto_branch = 'v1.2'
            break
        case 'v2.0':
            yocto_version = 'v2.4.4'
            yocto_upstream_branch = 'v2.0'
            yocto_branch = 'v2.0'
            break
        case 'v2.1':
            yocto_version = 'v2.5.4'
            yocto_upstream_branch = 'v2.0'
            yocto_branch = 'v2.1'
            break
        default:
            yocto_version = 'v2.6.2'
            yocto_upstream_branch = 'v2.0'
            yocto_branch = 'v3.0'
    }   
}

def getYoctoVersion() {
    return yocto_version
}
def getUpstreamBranch() {
    return yocto_upstream_branch
}
def getYoctoBranch() {
    return yocto_branch
}

def checkoutGit(repository, branch) {
    def gitRepository = "${GIT_URL}" + "${repository}" + ".git"
    
    dir("${repository}") {
        deleteDir()
        checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: "${gitRepository}"]],
            branches: [[name: "${branch}"]]
        ]
    }
}

def checkoutSvn(branch) {
    def svnRepository = "${SVN_URL}" + "${branch}"
    
    if (svnRepository [-1] != "/") {
        svnRepository = "${svnRepository}" + "/"
    }
    def svnData = checkout ([
        $class: 'SubversionSCM',
        additionalCredentials: [],
        excludedCommitMessages: '',
        excludedRegions: '',
        excludedRevprop: '',
        excludedUsers: '',
        filterChangelog: false,
        ignoreDirPropChanges: false,
        includeRegions: '',
        locations: [[
            credentialsId: '...',
            depthOption: 'empty',
            ignoreExternalOption: true,
            local: 'testdir',
            remote: ${svnRepository}
        ]],
        quietOperation: true,
        workspaceUpdater: [
            $class: 'UpdateUpdater'
        ]
    ])
    version = svnData.SVN_REVISION
    return version
}

return this
