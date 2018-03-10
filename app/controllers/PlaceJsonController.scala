package controllers

import javax.inject.Inject

import Json.Place
import Json.{Location, Resident}
import play.api.libs.json.{Json, _}
import play.api.mvc.{AbstractController, ControllerComponents}

class PlaceJsonController @Inject()(cc:ControllerComponents) extends AbstractController(cc){

  /*
  tester Json Http en Get
  http://localhost:9000/jsonPlaces
   */
  val place = Place(
    "Watership Down",
    Location(51, -1),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  def jsonTester = Action {
    Ok(views.html jsonPost("JsonPost Tester"))
  }

  //entrerer le lieu pour afficher l'information json sur la page
  //http://localhost:9000/jsonPlaces
  def listPlaces = Action {
    val json = Json.prettyPrint(Json.toJson(place))
    Ok(json)
  }

  //recuperer une request GET et rentrerer une message json a la suite
  def jsonGet = Action {
    println("get request jsonGet")
    val json = Json.prettyPrint(Json.toJson(place))
    Ok(json)
  }

  //recuperer une request POST et rentrerer une message json a la suite
  def jsonPost = Action (parse.json){ request =>
    println("savePlace")
    val placeResult = request.body.validate[Place]
    println(placeResult)
    val json = Json.prettyPrint(Json.toJson(place))
    Ok(json)
  }

  def savePlace = Action(parse.json) { request =>
    println("savePlace")
    val placeResult = request.body.validate[Place]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      place => {
        Place.save(place)
        Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+place.name+"' saved.") ))
      }
    )
  }
}
