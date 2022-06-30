def files = findFiles excludes: '', glob: ''

files.each { f -> 
   if (f.directory && f.name == 'MyScripts'){
       echo "Folder: $f.name"   // Prints Folder: MyScripts
       dir('MyScripts') {
          sh "pwd"
          def fileList = findFiles(glob: '*.*')
          echo "$fileList"
        } 
   }
}
