pipeline {
    agent any
    stages{
        stage('Build') { 
            steps{
                echo "RUNNING THE BUILD!!!!" 
            }
        }
    }
    post { 
        always { 
            script{
                echo 'Checking the time'
                def timeToCheckBefore = [hourOfDay: 10, minute: 0, second: 0] // 10PM will be 23, 0, 0
                def now = new Date()
                def check = now.clone()
                check.set(timeToCheckBefore)
                
                if(now.after(check)) {
                    echo "Time is Passed: Current Time : $now, Should finish before: $check"
                } else {
                    echo "Job finished timely: Current Time : $now, Should finish before: $check"
                }
            }
            
        }
    }
}
