def createJenkinsJob(def jobName, def folderName) {

    echo "Creating the job ${jobName}"
  // Here I'm using a shared library in the pipeline, so I have loaded my shared library here
  // You can simply have the entire pipeline syntax here.
    def jobDSL="@Library('yasassri@master') _\n" +
                "Pipeline()"
    def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(jobDSL, true)
    def instance = Jenkins.instance
    def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(instance, jobName )
    job.definition = flowDefinition
    job.setConcurrentBuild(false)

    job.save()
    Jenkins.instance.reload()
    
    // After creating the Job move the job to a folder
    if (folderName != null && folderName != "") {
        def folder = Jenkins.instance.getItemByFullName(folderName)
        Items.move(job, folder)
    }  
}
