def subFolderToCheck = "folder1" // We will only check Jobs in a specific sub directory
Jenkins.instance.getAllItems(Job.class).each { jobitem ->
      def jobName = jobitem.getFullName()
      def jobInfo = Jenkins.instance.getItemByFullName(jobName)
      
      // We will check if the last successfull build has any tests attached. 
      if(jobName.contains(subFolderToCheck) && jobInfo.getLastSuccessfulBuild() != null) {
        def results = jobInfo.getLastSuccessfulBuild().getActions(hudson.tasks.junit.TestResultAction.class).result
        println("Job : " + jobName + " Tests " + results.size())
        
        if(results == null || results.size() <= 0) {
          print("Job " + jobName + " Does not have any tests!!!!!")
          
        }               
    }    
}
