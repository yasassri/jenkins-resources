// This script will list the user view beloging to a given user.

script {
    def username = 'admin'
    def user = User.get(username, false, null)
    if (user != null) {
      for(def property: user.getAllProperties()) {
          if(property instanceof hudson.model.MyViewsProperty) {
              for(def view : property.getViews()){
                println(view.name)
              }
          }
      }
    }
}
