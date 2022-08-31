pipeline {
    agent any

    stages {
        stage('CleanupWorkers') {
            steps {
                script {
                    echo "Something"
                    parallel parallelJobs()
                }
            }
        }
    }
}

def parallelJobs() {
  jobs = [:]
  for (def nodeName in getAllNodes()) {
    jobs[nodeName] = getStage(nodeName)
  }
  return jobs
}

def getStage(def nodeName){
    return { 
        stage("Cleaning $nodeName") {
           node(nodeName){
                sh'''
                  echo "Srating cleaning"
                  docker container prune -f
                  docker image prune -f
                  docker images | awk '{print $1 ":" $2}' | xargs docker image rm || true
                  docker network prune -f
                  docker volume prune -f
                '''
           }
         }
    }
}

def getAllNodes() {
    def nodeNames = [] 
    def jenkinsNodes = Jenkins.instance.getNodes().each { node ->
    // Ignore offline agents
    if (!node.getComputer().isOffline()){
      nodeNames.add(node.getNodeName())
    } 
  }
  return nodeNames
}
