def version
def target

class Target {
    String repo
    String machine
    String platform
    String device
}

A_VERSIONS = ["master","v1.0","v1.1","v1.2","v2.0"]
B_VERSIONS = ["master","v1.0","v1.1","v1.2"]
C_VERSIONS = ["master","v1.0","v1.1"]
D_VERSIONS = ["master","v1.0"]
E_VERSIONS = ["master"]



def chooseLinuxVersion(v) {
    switch (v) {
        case "v1.0":
            version = "v1.0"
            break
        case "v1.1":
            version = "v1.1"
            break
        case "v1.2":
            version = "v1.2"
            break
        case "v2.0":
            version = "v2.0"
            break
        case "v2.1":
            version = "v2.1"
            break
        default:
            version = "master"
    }
}

def getVersion() {
    return version
}

def chooseTarget(t) {
    switch (t) {
        case 'A':
            sanityCheck(A_VERSIONS, version)
            target = new Target([repo: 'axc', machine: 'axcf', platform: 'axcf', device: 'axcf'])
            break
        case 'B':
            sanityCheck(B_VERSIONS, version)
            target = new Target([repo: 'axc', machine: 'axcf', platform: 'axcf', device: 'bxcf'])
            break
        case 'C':
            sanityCheck(C_VERSIONS, version)
            target = new Target([repo: 'rfc', machine: 'rfc-64', platform: 'rfc', device: 'rfc'])
            break
        case 'D':
            sanityCheck(D_VERSIONS, version)
            target = new Target([repo: 'rfc', machine: 'rfc-64', platform: 'rfc', device: 'dfc'])
            break        
        default:
            target = new Target([repo: 'ec', machine: 'ec-32', platform: 'ec', device: 'ekg'])
            sanityCheck(E_VERSIONS, version)
    }
}

def getTarget() {
    return target
}

def sanityCheck(t,v) {
    if (t.contains(v)) {
        version = v
    }
    else {
        version = "master"
    }
}

return this
