def viewObj = Jenkins.instance.getView("VIEWNAME")
List<hudson.views.ViewJobFilter> jobFilters = new ArrayList<>()

jobFilters.add(new hudson.views.RegExJobFilter('.*NEWREGEX', "includeMatched", "NAME"))
viewObj.setJobFilters(jobFilters)
