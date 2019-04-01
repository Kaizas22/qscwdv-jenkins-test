REPO_URL = "https://github.com/Kaizas22/"

def checkout(repository) {
    def repos = "${REPO_URL}" + "${repository}" + ".git"
    echo "${repos}"
    
    checkout scm: [
        $class: 'GitSCM',
        userRemoteConfigs: [[url: "${repos}"]],
        branches: [[name: "${BRANCH}"]]
    ]
}

return this
