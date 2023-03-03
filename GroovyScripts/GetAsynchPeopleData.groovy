# Get information shown in http://localhost:8080/asynchPeople/

import hudson.model.View.UserInfo;
import hudson.util.RunList;
import jenkins.scm.RunWithSCM;
import hudson.scm.ChangeLogSet;

def jen = Jenkins.instance

Collection<TopLevelItem> items = jen.items;

Map<User, UserInfo> users = new HashMap<>();
Set<User> modified = new HashSet<>();

for (Item item : items) {
    for (Job<?, ?> job : item.getAllJobs()) {
        RunList<? extends Run<?, ?>> builds = job.getBuilds();
        int buildCount = 0;
        for (Run<?, ?> r : builds) {
            if (!(r instanceof RunWithSCM)) {
                continue;
            }

            RunWithSCM<?, ?> runWithSCM = (RunWithSCM<?, ?>) r;
            for (ChangeLogSet<? extends ChangeLogSet.Entry> c : runWithSCM.getChangeSets()) {
                for (ChangeLogSet.Entry entry : c) {
                    User user = entry.getAuthor();
                    UserInfo info = users.get(user);
                    if (info == null) {
                        UserInfo userInfo = new UserInfo(user, job, r.getTimestamp());
                        users.put(user, userInfo);
                        modified.add(user);

                    } else if (info.getLastChange().before(r.getTimestamp())) {
                        info.project = job;
                        info.lastChange = r.getTimestamp();
                        modified.add(user);

                    }
                }
            }
        }
    }
}


users.each { key, val ->
    println "UserName : " + val.getUser() + " ,CommitActivity: " + val.getLastChangeTimeString() + " ,ProjectName: " + val.getJob().getFullDisplayName() + " ,URL: " + val.getJob().getUrl()
}
