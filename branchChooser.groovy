class Example {
  def choose_yocto_branch(version) {
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
            result = 'v1.0'
            break
    }
    return this
  }
}
