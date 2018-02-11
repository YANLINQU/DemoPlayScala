package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class IndexController @Inject()(cc: ControllerComponents)(implicit executionContext: ExecutionContext) extends AbstractController(cc){

  //Action index
  def index = Action {
    Ok(views.html index("Your new application is ready."))
  }

}
