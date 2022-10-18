pipeline {
    agent any
    stages {
        stage('Report') {
            steps {
                script {
                    def jobsToInclude = ['Job1', 'Job2']
                    generateReport(jobsToInclude)
                }
            }
        }
    }
}

def generateReport(def jobs) {
      Jenkins.instance.getAllItems(Job.class).each { jobitem ->
          def jobName = jobitem.getFullName()
          if(jobs.contains(jobName)) {
               
              def jobInfo = Jenkins.instance.getItemByFullName(jobName)
              // Current time in Miliseconds
              def now = new Date().getTime()
              def before24Hours = now - (24 * 60 * 60 * 1000)

              jobInfo.getBuilds().byTimestamp(before24Hours, now).each { build ->  
                
                if(build.getResult().toString().equals('SUCCESS')) {
                  println("Job : " + jobName + " || BuildNumber: " + build.getNumber() + " || Timestamp: " + build.getTime())
                  // Get the commits
                  build.getChangeSets().each{change -> 
                    change.getItems().each { item ->
                      println("---" + "COMMITID: " + item.getCommitId() +  " || Message: " + item.getMsg() + " || Author: " + item.getAuthorName())
                    }
                  }   
                }
            }  
         }
    }  
}
