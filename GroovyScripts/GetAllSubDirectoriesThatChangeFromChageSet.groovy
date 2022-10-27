def getSubDirectoriesChanged() {
    def filesList = []
    def changeLogSets = currentBuild.changeSets

    // First Lets get all the files that changed. 
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            def files = new ArrayList(entry.affectedFiles)
                for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                filesList.add(file.path)
            }
        }
    }
    
    // Let's filter the directories, having a / means it's a directory, there can be multiple files changed
    // in the same directory, so we need to drop the duplicates.
    return filesList.findAll{ it.contains("/") }.collect { it.split('/')[0] }.unique()
}
