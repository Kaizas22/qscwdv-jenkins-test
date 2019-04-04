def version
def target

def A_VERSIONS = ["master","1.0","1.1","1.2","2.0","2.1"]
def B_VERSIONS = ["master","1.0","1.1","1.2"]
def C_VERSIONS = ["master","1.0","1.1"]
def D_VERSIONS = ["master","1.0"]
def E_VERSIONS = ["master"]

def chooseTarget(t) {
    switch (t) {
        case 'A':
            target = 'A'
            break
        case 'B':
            target = 'B'
            break
        case 'C':
            target = 'C'
            break
        case 'D':
            target = 'D'
            break        
        default:
            target = 'E'
    }
}

def getTarget() {
    return target
}

def chooseLinuxVersion(v) {
    switch (v) {
        case '1.0':
            version = 'v1.0'
            break
        case '1.1':
            version = 'v1.1'
            break
        case '1.2':
            version = 'v1.2'
            break
        case '2.0':
            version = 'v2.0'
            break
        case '2.1':
            version = 'v2.1'
            break
        default:
            version = 'master'
    }
}

def sanityCheck(t,v) {
    echo "${t} : ${v}"
    assert ["master",'1.0','1.1'].contains(v)
    //assert A_VERSIONS.contains(${v})
    echo "true"
}

def getVersion() {
    return version
}

return this
