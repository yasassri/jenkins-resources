def jobToCopy = "OldJob"
def newJobName = "NEWJOB"

def j = Jenkins.instance

def copyJob = j.getItemByFullName(jobToCopy)

def script = copyJob.getDefinition().getScript()
def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(script, true)

def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(j, newJobName)
job.definition = flowDefinition
job.setConcurrentBuild(copyJob.isConcurrentBuild()) // You can add other options as nneded like this
job.save()
Jenkins.instance.reload()
