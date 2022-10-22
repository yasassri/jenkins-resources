pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                        def cause = currentBuild.getBuildCauses('org.jenkinsci.plugins.workflow.support.steps.build.BuildUpstreamCause')
                        if(cause.size() > 0) { // This is triggred by a upstream Job
                            def parentJobName = cause[0].upstreamProject
                            def parentBuildNumber = cause[0].upstreamBuild
                            echo "Parent JOb: $parentJobName"
                            echo "Parent Build Number: $parentBuildNumber" 
                        }
                    }
                   
               }
            }

        }
}
