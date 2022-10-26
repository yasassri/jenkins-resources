pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                        echo "Waiting"
                        def jobName = "JobA"
                        def buildNum = "92"
                        waitUntil { !isPending(jobName, buildNum) }

                        if(getStatus(jobName, buildNum).equals('SUCCESS')) {
                            echo "Job A is Successful"
                        } else {
                            echo "Job A Failed"
                        }
                    }
                   
               }
            }

        }
}

def isPending(def JobName, def buildNumber) {
    def buildA = Jenkins.instance.getItemByFullName(JobName).getBuild(buildNumber)
    return buildA.isInProgress()
}

def getStatus(def JobName, def buildNumber) {
    def status = Jenkins.instance.getItemByFullName(JobName).getBuild(buildNumber).getResult().toString()
    return status
}
