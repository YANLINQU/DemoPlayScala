package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}
import services.{TablesService}

import scala.concurrent.ExecutionContext

class TablesController @Inject()(tablesService:TablesService, cc: ControllerComponents)(implicit executionContext: ExecutionContext) extends AbstractController(cc){

  //select all tables
  def allTables = Action.async{
    tablesService.allTables().map{case(tables)=>Ok(views.html tables(tables))}
  }

  /*
  chercher toutes les tables dans un resto par l'Id de resto
   */
  def allTablesByRestoId(id: Int) = Action.async {
    tablesService.allTablesByRestoId(id).map{case(tables)=>Ok(views.html tables(tables))}
  }

  /*
  chercher toures les tables ans un resto par le Nom de resto
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
      Changer la collection en vector(1,2,3,etc) pour la page recupere Seq[Tables]

      Si tables est une collection Seq[Option[Tables]]
      Il faudra utiliser @for(table <- tables.flatten)
      sinon @for(table <- tables)
    */
    tablesService.allTablesByRestoNom(nom).map{case(tables)=>Ok(views.html tables(tables.flatten))}
  }
}
