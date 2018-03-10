package controllers

import javax.inject.Inject

import com.google.common.collect.Tables
import models.AddUneTable
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.TablesService

import scala.concurrent.ExecutionContext

class TablesController @Inject()(tablesService:TablesService, cc: ControllerComponents)(implicit executionContext: ExecutionContext) extends AbstractController(cc){

  //select all tables
  def allTables = Action.async{
    tablesService.allTables().map{case(tables)=>Ok(views.html tables(tables, "SUSHI KAN"))}
  }

  /*
  chercher toutes les tables dans un resto par l'Id de resto
   */
  def allTablesByRestoId(id: Int) = Action.async {
    tablesService.allTablesByRestoId(id).map{case(tables)=>Ok(views.html tables(tables, "SUSHI KAN"))}
  }

  /*
  chercher toures les tables dans un resto par le Nom de resto
   */
  def allTablesByRestoNom(nom: String) = Action.async{
    /*
      The flatten method will collapse the elements of a collection
      to create a single collection with elements of the same type.
      flatten将嵌套结构扁平化为一个层次的集合

      example:
      scala> List(List(1, 2), List(3, 4)).flatten
      res0: List[Int] = List(1, 2, 3, 4)

      la methode tablesService.allTablesByRestoNom(nom) reponse une collection
      vector(Some(1),Some(2),Some(3),etc)
      Changer la collection en vector(1,2,3,etc) pour la page recuperer Seq[Tables]

      Si tables est une collection Seq[Option[Tables]]
      Il faudra utiliser @for(table <- tables.flatten)
      sinon @for(table <- tables)
    */
    tablesService.allTablesByRestoNom(nom).map{case(tables)=>Ok(views.html tables(tables.flatten, nom))}
  }


  def addTableInTheResto(nomResto: String) = Action.async{ implicit request: Request[AnyContent] =>
    //POST: recupere les infos par un VO addUneTable avec mapping
    val addTableInfos = Form(
      mapping{
        "addnumero" -> nonEmptyText
      }(AddUneTable.apply)(AddUneTable.unapply)
    ).bindFromRequest.get

    tablesService.addUneTable(nomResto,addTableInfos.addnumero.toInt)

    tablesService.allTablesByRestoNom(nomResto).map{case(tables)=>Ok(views.html tables(tables.flatten, nomResto))}
  }
}
