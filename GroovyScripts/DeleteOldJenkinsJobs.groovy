def deleteBefore = "2022/07/01"

Jenkins.instance.getAllItems(Job.class).each { jobitem ->
      def jobName = jobitem.getFullName()
  
      def deleteBeforeTime = new Date(deleteBefore).getTime()

      def build = jobitem.getLastBuild() 
  
    if(build == null || build.getTimeInMillis() <= deleteBeforeTime){ // If no builds, build is null
        
      println build == null ? "Job " + jobName + " has never run, deleting the Job" : "Job " + jobName + " last ran on (" + build.getTime() + ") hence deleting"
      jobitem.delete()
        
    }    
}
