def copyResults() {
    echo 'Copy Results..'
    sh 'bash/init_env.sh 123342 sdfas'
}

def archiveResults() {
    /*archiveArtifacts '...'
    cifsPublisher (
        publishers: [[
            configName: '...',
            transfers: [[
                cleanRemote: false,
                excludes: '',
                flatten: false,
                makeEmptyDirs: false,
                noDefaultExcludes: false,
                patternSeparator: '[, ]+',
                remoteDirectory: '...',
                remoteDirectorySDF: false,
                removePrefix: '...',
                sourceFiles: '...'
            ]],
            usePromotionTimestamp: false,
            useWorkspaceInPromotion: false,
            verbose: false
        ]]
    )*/
    echo 'Archive Results..'
}

return this
