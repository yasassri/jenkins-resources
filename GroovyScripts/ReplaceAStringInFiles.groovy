def dirToSearchIn = "/where/to/replace"

// Change the content on specific files. You can improve the regex pattern below to fine-tune it. With the following pattern only files with extensions .java and .md will be changed.  
def filterFilePattern = ~/.*\.java|.*\.md$/

def oldString = "replaceme"
def newString = "newme"

new File(dirToSearchIn).traverse(type: groovy.io.FileType.FILES, nameFilter: filterFilePattern) { file ->
  println "Processing file: " + file.getPath()
  def fileContent = file.text;
  if (fileContent.contains(oldString)) {
    println "Replacing the content of the file: " + file.getPath()
    file.write(fileContent.replaceAll(oldString, newString));
  } else {
    println "Skipping file: " + file.getPath()
  }
}
