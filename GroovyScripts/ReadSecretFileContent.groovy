def jenkinsCredentials = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
com.cloudbees.plugins.credentials.Credentials.class,
Jenkins.instance,
null,
null
);
//SecretFileCred is type secret file
for (creds in jenkinsCredentials) {
  if(creds.id == "SecretFileCred"){
  	println creds.getContent().getText()
  }
}
