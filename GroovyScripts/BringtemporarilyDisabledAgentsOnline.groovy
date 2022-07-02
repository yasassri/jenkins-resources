// cliOnline() seems to be depricated
def jenkinsNodes = Jenkins.instance.getNodes()
  for(def node: jenkinsNodes) {
      if (node.getComputer().isOffline()){  
          node.getComputer().cliOnline()
      }
  }


// A non depricated approach. Build cause is only needed when setting to offline
import hudson.slaves.OfflineCause.UserCause

def jenkinsNodes = Jenkins.instance.getNodes()
  for(def node: jenkinsNodes) {
      if (node.getComputer().isTemporarilyOffline()){  
           println(node.getComputer().class)
           UserCause cause =  new UserCause(User.current(), "This is a automated process!!")
           node.getComputer().setTemporarilyOffline(false, cause)
      }
  }
