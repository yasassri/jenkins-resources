// This cript prints all the installed plugins and if there ae updates, the latest version available.
jenkins.model.Jenkins.getInstance().getUpdateCenter().getSites().each { site ->
 site.updateDirectlyNow(hudson.model.DownloadService.signatureCheck)
}

hudson.model.DownloadService.Downloadable.all().each { downloadable ->
 downloadable.updateNow();
}

def pluginList = new ArrayList(Jenkins.instance.pluginManager.plugins)

pluginList.sort { it.getShortName() }.each{ plugin -> 
  	println ("${plugin.getDisplayName()} (${plugin.getShortName()}): ${plugin.getVersion()} : ${plugin.hasUpdate() ? plugin.getUpdateInfo().version : 'No Update'}")
}
