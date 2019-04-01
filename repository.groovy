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
    
    echo "${svnRepository}"
    if (svnRepository [-1] != "/") {
        svnRepository = ${svnRepository} + "/"
    }
    echo svnRepository
}

return this
