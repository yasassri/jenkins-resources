def consoleLog = Jenkins.getInstance().getItemByFullName(env.JOB_NAME).getBuildByNumber(Integer.parseInt(env.BUILD_NUMBER)).logFile.text

// THis would extract "123456" from the console output build_id="1234567"
def buildId = (consoleLog =~ 'build_id="(.*)"')[0][1]
echo "build_id: $buildId"
env.build_id = buildId
