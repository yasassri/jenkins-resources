Jenkins.instance.getAllItems(Job.class).each { jobitem ->
      if(jobitem instanceof org.jenkinsci.plugins.workflow.job.WorkflowJob) {
        if(jobitem.getDefinition() instanceof org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition) {
          println("JobName " + jobitem.getName() + " || Pipeline Script: "+ jobitem.getDefinition().getScript())
        }       
    }     
}
