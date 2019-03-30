def choose_yocto_branch(version) {
  def result = 'v1.0'
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
            break
    }
    return result
  }
