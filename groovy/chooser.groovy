def version
def target

A_VERSIONS = ["master","1.0","1.1","1.2","2.0","2.1"]
B_VERSIONS = ["master","1.0","1.1","1.2"]
C_VERSIONS = ["master","1.0","1.1"]
D_VERSIONS = ["master","1.0"]
E_VERSIONS = ["master"]

def chooseTarget(t) {
    switch (t) {
        case 'A':
            target = 'A'
            sanityCheck(A_VERSIONS, version)
            break
        case 'B':
            target = 'B'
            sanityCheck(B_VERSIONS, version)
            break
        case 'C':
            target = 'C'
            sanityCheck(C_VERSIONS, version)
            break
        case 'D':
            target = 'D'
            sanityCheck(D_VERSIONS, version)
            break        
        default:
            target = 'E'
            sanityCheck(E_VERSIONS, version)
    }
}

def getTarget() {
    return target
}

def chooseLinuxVersion(v) {
    switch (v) {
        case "1.0":
            version = "v1.0"
            break
        case "1.1":
            version = "v1.1"
            break
        case "1.2":
            version = "v1.2"
            break
        case "2.0":
            version = "v2.0"
            break
        case "2.1":
            version = "v2.1"
            break
        default:
            version = "master"
    }
}

def sanityCheck(t,v) {
    if (t.contains(v)) {
        version = v
    }
    else {
        version = "master"
    }
}

def getVersion() {
    return version
}

return this
