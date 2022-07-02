// When you have a Job that gets the Pipeline scripts from SCM you can use the following script to List all such Jobs and the configured git URLs. 
Jenkins.instance.getAllItems(Job.class).each { jobitem ->
      if(jobitem instanceof org.jenkinsci.plugins.workflow.job.WorkflowJob) {
        if(jobitem.definition instanceof org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition) {
          jobitem.definition.getScm().getRepositories().each { repo ->
                println("Job Name: " + jobitem.getName() + " URLs: " + repo.getURIs())

            } 
        }       
    }     
}
