package learn


object For extends App{
  //chercher toutes les fichiers dans la dossier
  var filesHere = (new java.io.File(".")).listFiles
  for( file <-filesHere)
    println(file)

  //filter
  //chercher toutes les fichiers scala
  for( file <-filesHere
       if file.isFile
       if file.getName.endsWith(".scala"))
    println(file)

  /*
  for clauses yield body

  cette fonctionne returne une valeur Array[File]
  def scalaFiles =
    for {
      file     if file.getName.endsWith(".scala")
    } yield file
   */

}
