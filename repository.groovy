REPO_URL = "https://github.com/Kaizas22/"

def checkoutRepo(repository) {
    def repos = "${REPO_URL}" + "${repository}" + ".git"
    
    dir("repository")
    echo pwd()
    checkout scm: [
        $class: 'GitSCM',
        userRemoteConfigs: [[url: "${repos}"]],
        branches: [[name: "${BRANCH}"]]
    ]
}

return this
