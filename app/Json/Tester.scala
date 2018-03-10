package Json

import play.api.libs.json._

object Tester extends App {
  val place = Place(
    "Watership Down",
    Location(51.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  val json = Json.toJson(place)
  println(Json.prettyPrint(json))

  //definir path name Reads
  val nameReads: Reads[String] = (JsPath \ "name").read[String]
  //validation with Reads
  val nameResult: JsResult[String] = json.validate[String](nameReads)

  val result = nameResult match {
    case s: JsSuccess[String] => println("Name: " + s.get)
    case e: JsError => println("Errors: " + JsError.toJson(e).toString())
  }

  //validation with Reads directement
  val nameResult2: JsResult[String] = (json \ "name").validate[String]
  val result2 = nameResult2 match {
    case s: JsSuccess[String] => println("Name: " + s.get)
    case e: JsError => println("Errors: " + JsError.toJson(e).toString())
  }
  println(result)
  println(result2)

  val placeResult: JsResult[Place] = json.validate[Place]
  println("placeResultValidate:"+placeResult)

  val locationResult: JsResult[Location] = (json \ "location").validate[Location]
  println(locationResult)

  val residantResult: JsResult[Resident] = (json \ "residents").validate[Resident]
  residantResult foreach println
}
