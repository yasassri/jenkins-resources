def waitThreshold = 300 // In seconds

Jenkins.instance.getAllItems(Job.class).each { job ->
      job.getBuilds().each { b ->     
        if(b.isInProgress()) {
           def waitTime = (System.currentTimeMillis() - b.getStartTimeInMillis())/1000;
           println "Build: " + b.getNumber() + " is Idling for : " + waitTime
           if(b.getLog().contains("There are no nodes with the label") && waitTime > waitThreshold){
                println "Build: " + b.getNumber() + " of Job: " +  job.getName() + " is Waiting. Hence Killing!!!"
                //b.doKill()
           }
        } 
   }  
}
