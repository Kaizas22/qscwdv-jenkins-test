def chooseBranch(version) {
    def result
    switch (version) {
        case 'master':
            result = 'master'
            break
        case 'master-next':
        case 'v1.2':
            result = 'v1.2'
            break
        case 'v1.0':
        case 'v1.1':
            result = 'master-next'
            break
        default:
            result = 'master'
            break
    }
    return result
}

def chooseTarget(target) {
    def result
    switch (target) {
        case 'A':
            result = 'A'
            break
        case 'B':
            result = 'B'
            break
        case 'C':
            result = 'C'
            break
        case 'D':
            result = 'D'
            break        
        default:
            result = 'E'
    }
    return result
}

return this
