Jenkins.instance.queue.clear() // Clear all the Pending Jobs

// Killing all Paused Jobs
Jenkins.instance.getAllItems(Job.class).each { job ->
      job.getBuilds().each { b ->        
        if(b.isInProgress()) {
           if(b.getExecution().isPaused()){
           		println "Build: " + b.getNumber() + " of Job: " +  job.getName() + " is Paused. Hence Killing!!!"
             	b.doKill()
           }
        } 
   }  
}
