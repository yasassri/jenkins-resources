
def labelSelected = getLabels()
println("Running on Label:" + labelSelected)


pipeline {
    agent {
       label "${labelSelected}"
    }

    stages {
        stage('Build') {
            steps {
                echo "Running!!!"
            }
        }
    }
}


def getLabels() {
    def labelOrder = ["test", "label2", "label3"]
    def whereToSchedule = "test || label2 || label3"
        
    def jenkinsNodes = Jenkins.instance.getNodes()
    for(String label : labelOrder) {
      for(def node: jenkinsNodes) {
        	if(node.labelString.contains(label)){
          	    println("Found a node with labels!!" + node.getComputer().class)
              	if (!node.getComputer().isOffline()){           
                  if(node.getComputer().countIdle() > 0){
                     println("The Slave does have executors")
                     whereToSchedule = label
                     break
                  }
        		}
        	}
    	}
    }
    return whereToSchedule
}
