package dao

import javax.inject.Inject

import models.Resto
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class RestoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val restos = TableQuery[RestoSchema]

  //chercher toutes les tables par l'id Resto
  def chercherIDByRestoNom(nom:String): Future[Int] = {
    //chercher un seul column id
    //.take(1) est le premier column dans la resultat
    //head === .take(1)
    val newQuery = restos.filter(_.nom === nom).map(_.id)
    db.run(newQuery.result.head)
  }

  def restoExist(nom:String):Future[Boolean] = {
    val newQuery = restos.filter(_.nom === nom).exists
    db.run(newQuery.result)
  }

  //un schema de la table "resto"
  class RestoSchema (tag: Tag) extends Table[Resto](tag, "resto") {
    def id=column[Int]("id",O.PrimaryKey,O.AutoInc)
    def nom=column[String]("nom")
    def adresse=column[String]("adresse")
    def ville=column[String]("ville")
    def postal=column[String]("postal")
    def revenu=column[Float]("revenu")
    def id_restaurateur=column[Int]("id_restaurateur")
    def * =(id,nom,adresse,ville,postal,revenu,id_restaurateur)<>(Resto.tupled,Resto.unapply)
  }
}
