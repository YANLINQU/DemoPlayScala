package learn

object Variable {
  //modifier possible
  var hello_var:String = "je suis un variable"

  //modifier impossible
  val hello_val:String = "je suis un valeur"

  def modif_hello_var:String = {
    hello_var = "je suis un variable(modif)"
    hello_var
  }

  def main(args: Array[String]): Unit = {
    println(hello_var)
    println(modif_hello_var)
    println(hello_val)
  }
}
