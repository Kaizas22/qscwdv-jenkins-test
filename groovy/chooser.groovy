def version
def target

def A_VERSIONS = ["master","1.0","1.1","1.2","2.0","2.1"]
def B_VERSIONS = ["master","1.0","1.1","1.2"]
def C_VERSIONS = ["master","1.0","1.1"]
def D_VERSIONS = ["master","1.0"]
def E_VERSIONS = ["master"]

def chooseTarget(target) {
    switch (target) {
        case 'A':
            this.target = 'A'
            break
        case 'B':
            this.target = 'B'
            break
        case 'C':
            this.target = 'C'
            break
        case 'D':
            this.target = 'D'
            break        
        default:
            this.target = 'E'
    }
}

def getTarget() {
    return target
}

def chooseLinuxVersion(version) {
    switch (version) {
        case '1.0':
            this.version = 'v1.0'
            break
        case '1.1':
            this.version = 'v1.1'
            break
        case '1.2':
            this.version = 'v1.2'
            break
        case '2.0':
            this.version = 'v2.0'
            break
        case '2.1':
            this.version = 'v2.1'
            break
        default:
            this.version = 'master'
    }
    this.checkTargetVersion(target,version)
}

def checkTargetVersion(target,version) {
    echo "${this.getTarget()} : ${this.getVersion()}"
    assert this.getVersion() in ${A_VERSIONS}
    echo "true"
}

def getVersion() {
    return version
}

return this
