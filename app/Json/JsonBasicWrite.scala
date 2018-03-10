package Json

import play.api.libs.json.{JsPath, Json, Reads, Writes}
import play.api.libs.functional.syntax._
/*
reference: Json Basic
https://www.playframework.com/documentation/2.6.x/ScalaJson
 */

/*
models Json
 */
case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])



/*
Using Writes converters implicit
 */
  object Location {
    implicit val locationWrites = new Writes[Location] {
      def writes(location: Location) = Json.obj(
        "lat" -> location.lat,
        "long" -> location.long
      )
    }

    implicit val locationReads: Reads[Location] = (
      (JsPath \ "lat").read[Double] and
        (JsPath \ "long").read[Double]
      )(Location.apply _)
  }

  object Resident {
    implicit val residentWrites = new Writes[Resident] {
      def writes(resident: Resident) = Json.obj(
        "name" -> resident.name,
        "age" -> resident.age,
        "role" -> resident.role
      )
    }
    implicit val residentReads: Reads[Resident] = (
      (JsPath \ "name").read[String] and
        (JsPath \ "age").read[Int] and
        (JsPath \ "role").readNullable[String]
      )(Resident.apply _)
  }

  object Place {

    var list : List[Place] = (
      List(
        Place(
          "Watership Down",
          Location(51.235685, -1.309197),
          Seq(
            Resident("Fiver", 4, None),
            Resident("Bigwig", 6, Some("Owsla"))
          )
        )
      )
    )

    def save(place: Place) = {
      list = list ::: List(place)
    }

    implicit val placeWrites = new Writes[Place] {
      def writes(place: Place) = Json.obj(
        "name" -> place.name,
        "location" -> place.location,
        "residents" -> place.residents
      )
    }
    implicit val placeReads: Reads[Place] = (
      (JsPath \ "name").read[String] and
        (JsPath \ "location").read[Location] and
        (JsPath \ "residents").read[Seq[Resident]]
      )(Place.apply _)


  }
