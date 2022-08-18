// FolderName you want get configfiles from
def folderName = "Folder2" 
def folderItem = Jenkins.instance.getAllItems(com.cloudbees.hudson.plugins.folder.AbstractFolder.class).find{ (it.name == folderName) }

// If you want to filter a specific configFile type, example Json configs here
def descriptor =  org.jenkinsci.plugins.configfiles.json.JsonConfig.JsonConfigProvider.class 

def allConfigFiles = folderItem.getProperties().get(org.jenkinsci.plugins.configfiles.folder.FolderConfigFileProperty.class).getConfigs();

def jonConfigFiles = folderItem.getProperties().get(org.jenkinsci.plugins.configfiles.folder.FolderConfigFileProperty.class).getConfigs(descriptor);
            
echo "$allConfigFiles"
echo "$jonConfigFiles"
