def allChangeSetsFromLastSuccessfulBuild() {
    def job = Jenkins.instance.getItem("$JOB_NAME")
    def lastSuccessBuild = job.lastSuccessfulBuild.number as int
    def currentBuildId = "$BUILD_ID" as int
    
    def changeSets = []

    for(int i = lastSuccessBuild + 1; i < currentBuildId; i++) {
        echo "Getting Change Set for the Build ID : ${i}"
        def chageSet = job.getBuildByNumber(i).getChangeSets()
        changeSets.addAll(chageSet)
    }
     changeSets.addAll(currentBuild.changeSets) // Add the current Changeset
     return changeSets
}
