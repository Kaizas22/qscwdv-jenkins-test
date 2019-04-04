def branch
def target

def chooseBranch(version) {
    switch (version) {
        case 'v1.0':
            branch = 'v1.0'
            break
        case 'v1.1':
            branch = 'v1.1'
            break
        case 'v1.2':
            branch = 'v1.2'
            break
        case 'v2.0':
            branch = 'v2.0'
            break
        case 'v2.1':
            branch = 'v2.1'
            break
        default:
            branch = 'master'
            break
    }
}

def getBranch() {
    return branch
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
