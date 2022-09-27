pipeline {
  agent any
  stages {
    stage('Example') {
      steps {
        script {
            def directories = getDirectories("$WORKSPACE")
            echo "$directories"
        }
      }
    }
  }
}

@NonCPS
def getDirectories(path) {
    def dir = new File(path);
    def dirs = [];
    dir.traverse(type: groovy.io.FileType.DIRECTORIES, maxDepth: -1) { d ->
        dirs.add(d) 
    };
    return dirs
}
