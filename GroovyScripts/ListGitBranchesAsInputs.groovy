properties([
    parameters([
        [$class: 'ChoiceParameter', 
            choiceType: 'PT_SINGLE_SELECT', 
            description: 'Select the Branch', 
            name: 'Bracnh',
            script: [
                $class: 'GroovyScript', 
                fallbackScript: [
                    classpath: [], 
                    sandbox: false, 
                    script: 
                        'return [\'Could not get Branch\']'
                ], 
                script: [
                    classpath: [], 
                    sandbox: false, 
                    script: 
                        '''def token = 'xxxxxxxxxxx';
def content = new URL("https://api.github.com/repos/OWNER/REPO/branches").getText(useCaches: true, requestProperties: ['Aothorization': 'Bearer ' + token]);
def jsonSlurper = new groovy.json.JsonSlurper();
return jsonSlurper.parseText(content).name;'''
                ]
            ]
        ]
    ])
])
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    echo "Branch:::: ${params.Branch}"
                }
            }
        }
    }
}
