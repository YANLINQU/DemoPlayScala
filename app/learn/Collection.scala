package learn

/*
Référence : https://www.tutorialspoint.com/scala/scala_lists.htm
 */
object Collection {
  /*
  List :
  Impossibilite de modifier les elements dans une list
  declarer une list avec les element oblicatoir

  Array:
  possibilite de modifier les elements dans une list
  decalarer une Array avec sa longueur et mettre les element a la suite

  Efficacite:
  Array prends un seul élément d'efficacité plus élevé que List
   */
  def main(args: Array[String]): Unit = {
    val arrayTest = Array(1,2,3,4) // c'est marche declarer avec les elements directe

    // utiliser new pour declarer une nouvelle class avec le type generic [] , pas de <>
    var greetStrings =new Array[String](3)
    println("---------------------Initialiser une List : un----------------------------")
    /*
    indiquer l'index de la liste avec (), pas de []
    greetStrings(1) === greetString.apply(1)
     */
    greetStrings(0)="Hello"
    greetStrings(1)=","
    greetStrings(2)="world!\n"
    for(i <- 0 to greetStrings.size-1)
      print(greetStrings(i))

    println("---------------------Initialiser une Array : deux----------------------------")
    greetStrings = Array("Hello",",","World!\n")
    for(i <- 0 to greetStrings.size-1)
      print(greetStrings(i))

    println("---------------------Initialiser une Array : trois----------------------------")
    greetStrings =Array.apply("Hello",",","World!\n")
    for(i <- 0 to greetStrings.size-1)
      print(greetStrings(i))

    println("---------------------update une Array----------------------------")
    greetStrings.update(0,"I'm")
    greetStrings.update(1," a ")
    greetStrings.update(2,"new!\n")
    for(i <- 0 to greetStrings.size-1)
      print(greetStrings.apply(i))


    var str:String = ""
    // List of Strings
    val fruit: List[String] = List("apples", "oranges", "pears")

    fruit.foreach(f => println(f))

    println("---------------------print direct----------------------------")
    fruit foreach println

    println("----------------------foreach---------------------------")
    fruit.foreach(println)

    println("---------------------modif----------------------------")
    fruit.foreach(f => {
      str = f
      println(str)
    })

    println("---------------------for----------------------------")
    for(ff <- fruit)
      println(ff)

    println("---------------------count----------------------------")
    /*
    a <- 0 to 10 : 10 est include
    a <- 0 until 10 : 10 n'est pas include
     */
    for( a <- 0 until fruit.size){
      println( fruit(a) )
    }

    println("---------------------Rajouter un element dans la list----------------------------")
    val oneTowThree = 1 :: 2 :: 3 :: Nil //Nil est oblicatoir
    println(oneTowThree)

    println("---------------------Fusionner deux lists ----------------------------")
    val oneTwo = List(1,2)
    val threeFour = List(3,4)
    val oneTwoThreeFour=oneTwo ::: threeFour
    println ( oneTwoThreeFour )
    /*
    This method returns a list consisting of all elements except the first.
     */
    println("---------------------Afficher les elements sauf le premiere ----------------------------")
    println ( oneTwoThreeFour.tail )

    println("---------------------reverse ----------------------------")
    println ( oneTwoThreeFour.reverse )

    println("---------------------sum ----------------------------")
    println ( oneTwoThreeFour.sum )

    /*
    list:
    head : A
    last : A
    length : Int
    isEmpty : Boolean
    max : A
    min : A
    contains(elem: Any): Boolean  existe
    reverse : List[A] inverser le classment
    tail : list[A] Returns all elements except the first.
    init : List[A] Returns all elements except the last.
    sorted : List[A] Trier par ordre alphabetique
    sum : add tous les elements dans la list
     */

    println("---------------------Tuple ----------------------------")
    /*
    l'index de tuple commence a 1 , pas de 0
     */
    val tupleTest = (1,2,"aaa")
    println(tupleTest._1)

    println("--------------------- Set ----------------------------")
    var jetSet = Set ("Boeing","Airbus")
    jetSet +="Lear"
    println(jetSet.contains("Cessna"))

    println("--------------------- Map ----------------------------")
    val romanNumeral = Map ( 1 -> "I" , 2 -> "II",
      3 -> "III", 4 -> "IV", 5 -> "V")
    println (romanNumeral(4))
  }
}
