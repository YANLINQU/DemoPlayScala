package learn

object Function extends App {
  //resemble a Ocaml
  val increase = (x :Int ) => x +1
  println(increase(10))

  def makeIncreaser(more:Int) = (x:Int) => x + more
  val inc1=makeIncreaser(1)
  println(inc1(10))

  //lambda java 8
  val someNumbers = List ( -11, -10, - 5, 0, 5, 10)
  someNumbers.filter( x => x >0).foreach(x => print(" "+x))
  println()
  //par defaut
  someNumbers.filter( _ >0).foreach(x => print(" "+x))
  println()
  //déduire les paramètres automatiquement:自行推断
  val f = (_ :Int ) + ( _ :Int)
  print(f (5,10))

}
