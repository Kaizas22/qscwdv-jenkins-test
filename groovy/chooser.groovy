def version
def target

def chooseBranch(version) {
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
            break
    }
}

def getBranch() {
    return version
}

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

return this
