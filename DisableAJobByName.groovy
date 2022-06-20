def disableJob(name) {
    def jobToDisable = name
    Jenkins.instance.getAllItems(Job.class).each { jobitem ->
                def jobName = jobitem.getFullName()
                def jobInfo = Jenkins.instance.getItemByFullName(jobName)

                if(jobName.equals(jobToDisable)) {
                    println("Disabling Job!!")
                    jobInfo.setDisabled(true)
                }   
       }
}
