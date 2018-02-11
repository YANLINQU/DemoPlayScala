package dao

import javax.inject.Inject

import models.Resto
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext}

class RestoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val restos = TableQuery[RestoSchema]

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
