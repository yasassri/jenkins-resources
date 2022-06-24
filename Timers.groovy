==== Setting a dynamictimeout

def flag = false
def to = Integer.MAX_VALUE
if(flag) {
    to = 500
}

pipeline {
    agent any
    options {
         timeout(time:to, unit:'SECONDS')
    }
  .......

 =======
    
// Following will timout when there is no avtivity in a Job for sometime. 
timeout(activity: true, time: 2, unit: 'MINUTES')
