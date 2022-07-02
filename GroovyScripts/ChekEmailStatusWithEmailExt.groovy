pipeline {
    agent any

    stages {
        stage('MailAndFail') {
            steps {
                script{
                    echo "Starting Mailing"
                    def script = "String response = transport.getLastServerResponse();println \"Mail Response: \" + response;File file = new File(\"/var/jenkins_home/workspace/EMAIEXT2222/MailResponse.txt\");file.write response"
                    emailext(
                       to: "test@gmail.com",
                       subject: "Test",
                       body: "Example test",
                       replyTo: 'test@test.com',
                       postsendScript: "$script"
                    )
                   sh "cat MailResponse.txt"
                   def response = readFile(file: 'MailResponse.txt')
                   if(!response.contains("2.0.0 OK")) {
                       echo "BUILD FAILURE!!!!"
                       currentBuild.result = 'FAILURE'
                   }
                }

            }
        }
    }
}
