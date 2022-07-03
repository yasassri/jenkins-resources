from api4jenkins import Jenkins

j = Jenkins('http://localhost:8080', auth=('admin', 'admin'))
job = j['Sample'] # Getting the Job by name
for line in job.get_build(1).console_text():
    print(line)