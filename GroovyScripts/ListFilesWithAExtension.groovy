@NonCPS
def checkPath(def path) {
     // Lists files like test.bak
     def filterGroovyFiles = ~/.*\.back$/
     new File(path).traverse(type: groovy.io.FileType.FILES, nameFilter: filterGroovyFiles) { file ->
                        println file
                    }
}
