def version
def target

class Target {
    String repository
    String machine
    String platform
    String device
}

AXC_F_2152_VERSIONS = ['master','2019.0 LTS','2019.3']
B_VERSIONS = ['master','2019.0 LTS']
RFC_4072S_VERSIONS = ['master','2019.0 LTS']
D_VERSIONS = ['master','2019.0 LTS']
E_VERSIONS = ['master']



def chooseLinuxVersion(v) {
    switch (v) {
        case '2019.0 LTS':
            version = '2019.0 LTS'
            break
        case '2019.3':
            version = '2019.3'
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
            target = new Target([repository: 'axc2152', machine: 'axcf2152', platform: 'axcf2152', device: 'bxcf'])
            break
        case 'RFC 4072S':
            sanityCheck(RFC_4072S_VERSIONS, version)
            target = new Target([repository: 'rfc480', machine: 'rfc480-64', platform: 'rfc480', device: 'rfc480'])
            break
        case 'D':
            sanityCheck(D_VERSIONS, version)
            target = new Target([repository: 'rfc480', machine: 'rfc480-64', platform: 'rfc480', device: 'dfc'])
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
