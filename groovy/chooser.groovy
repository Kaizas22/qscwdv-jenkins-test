def version
def target

class Target {
    String repository
    String machine
    String platform
    String device
}

AXC_F_2152_VERSIONS = ['master','2019.0 LTS','2019.3','v1.2','v2.0']
B_VERSIONS = ['master','v1.0','v1.1','v1.2']
RFC_4072S_VERSIONS = ['master','2019.0 LTS','v1.1']
D_VERSIONS = ['master','v1.0']
E_VERSIONS = ['master']



def chooseLinuxVersion(v) {
    switch (v) {
        case '2019.0 LTS':
            version = 'v1.0'
            break
        case 'v1.1':
            version = 'v1.1'
            break
        case 'v1.2':
            version = 'v1.2'
            break
        case '2019.3':
            version = 'v2.0'
            break
        case 'v2.1':
            version = 'v2.1'
            break
        default:
            version = 'master'
    }
}

def getVersion() {
    return version
}

def chooseTarget(t) {
    switch (t) {
        case 'AXC F 2152':
            sanityCheck(AXC_F_2152_VERSIONS, version)
            target = new Target([repository: 'axc2152', machine: 'axcf2152', platform: 'axcf2152', device: 'axcf2152'])
            break
        case 'B':
            sanityCheck(B_VERSIONS, version)
            target = new Target([repository: 'axc', machine: 'axcf', platform: 'axcf', device: 'bxcf'])
            break
        case 'RFC 4072S':
            sanityCheck(RFC_4072S_VERSIONS, version)
            target = new Target([repository: 'rfc480', machine: 'rfc480-64', platform: 'rfc480', device: 'rfc480'])
            break
        case 'D':
            sanityCheck(D_VERSIONS, version)
            target = new Target([repository: 'rfc', machine: 'rfc-64', platform: 'rfc', device: 'dfc'])
            break        
        default:
            target = new Target([repository: 'ec', machine: 'ec-32', platform: 'ec', device: 'ekg'])
            sanityCheck(E_VERSIONS, version)
    }
}

def getTarget() {
    return target
}

def getRepository() {
    return target.repository
}

def getMachine() {
    return target.machine
}

def getPlatform() {
    return target.platform
}

def getDevice() {
    return target.device
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
