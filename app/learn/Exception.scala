package learn

import java.io.{FileNotFoundException, FileReader, IOException}

object Exception extends App {


  def testFileExiste() : String = {
    try {
      val f = new FileReader("input.txt")
      "existe"
    } catch {
      case ex: FileNotFoundException => "not find"//handle missing file
      case ex: IOException => "IO error"//handle other I/O error
    } finally {
      println("je suis finally")
    }
  }

  println(testFileExiste)
}
