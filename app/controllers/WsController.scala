package controllers

import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

class WsController @Inject() (ws: WSClient) extends Controller{

//  val request: WSRequest = ws.url("/savePlace")
//  val complexRequest: WSRequest =
//    request.addHttpHeaders("Accept" -> "application/json")
//      .addQueryStringParameters("search" -> "play")
//      .withRequestTimeout(10000.millis)
//  val futureResponse: Future[WSResponse] = complexRequest.get()
//  println(futureResponse)
}
