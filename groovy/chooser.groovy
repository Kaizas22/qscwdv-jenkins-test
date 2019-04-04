def version
def target

def chooseBranch(version) {
    switch (version) {
        case 'v1.0':
            this.version = '1.0'
            break
        case 'v1.1':
            version = '1.1'
            break
        case 'v1.2':
            version = '1.2'
            break
        case 'v2.0':
            version = '2.0'
            break
        case 'v2.1':
            version = '2.1'
            break
        default:
            version = 'master'
            break
    }
}

def getBranch() {
    return version
}

def chooseTarget(device) {
    switch (device) {
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

return this
