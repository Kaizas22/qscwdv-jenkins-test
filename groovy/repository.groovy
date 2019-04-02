GIT_URL = "https://github.com/Kaizas22/"
SVN_URL = "https://svn.test.com/"

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
