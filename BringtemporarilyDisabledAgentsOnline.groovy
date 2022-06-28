def jenkinsNodes = Jenkins.instance.getNodes()
  for(def node: jenkinsNodes) {
      if (node.getComputer().isOffline()){  
          node.getComputer().cliOnline()
      }
  }
