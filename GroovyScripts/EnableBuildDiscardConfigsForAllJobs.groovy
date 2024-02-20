def maxDaysToKeep = 30
def maxNumToKeep = 6

Jenkins.instance.getAllItems(Job.class).each { job ->
  println("Updating the job: " + job.getName())
  job.setBuildDiscarder(new hudson.tasks.LogRotator(maxDaysToKeep, maxNumToKeep))         
}
