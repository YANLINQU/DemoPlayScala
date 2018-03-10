package learn

object Match extends App {

  val firstArg = "sdfsdf"
  val friend = firstArg match {
    case "salt" => "pepper"
    case "chips" => "salsa"
    case "eggs" => "bacon"
    case _ => "huh?"
  }

  println(friend)
}
