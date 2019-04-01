def REPO_URL = "https://github.com/Kaizas22/"

def checkout(repository) {
    echo "${REPO_URL}${TARGET}.git
    checkout scm: [
        $class: 'GitSCM',
        userRemoteConfigs: [[url: "${REPO_URL}${TARGET}.git"]],
        branches: [[name: "${BRANCH}"]]
    ]
}

return this
