Jenkins.instance.getAllItems(Job.class).each { jobitem ->
      def jobName = jobitem.getFullName()
      def jobInfo = Jenkins.instance.getItemByFullName(jobName)
  
  	  // Current time in Miliseconds
      def now = new Date().getTime()
      def before24Hours = now - (24*60*60*1000)
      println("Now: " + now + " Before24H: " + before24Hours)
      
      jobInfo.getBuilds().byTimestamp(before24Hours, now).each{ build ->       	
        if(build.getResult().toString().equals('SUCCESS')){
          println("Job : " + jobName + " || BuildNumber: " + build.getNumber() + " || Timestamp: " + build.getTime())
        }
      }
}
