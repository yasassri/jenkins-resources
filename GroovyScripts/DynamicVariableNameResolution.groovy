food = ['chips']
chips_juice = ['lovely-meal']

food.each { grub ->
  def meal = grub + '_juice'
  def newList = this.getBinding().getVariable(meal);

  newList.each {dinner ->
    println dinner
  }
}
