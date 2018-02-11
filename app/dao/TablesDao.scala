package dao

import javax.inject.Inject

import models.{Resto, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TablesDao @Inject() (restoDao: RestoDao, protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val tables = TableQuery[TablesSchema]
  private val resto = TableQuery[restoDao.RestoSchema]

  //chercher toutes les tables
  def all(): Future[Seq[Tables]] = db.run(tables.result)

  //chercher toutes les tables par l'id Resto
  def allTablesByRestoId(id:Int): Future[Seq[Tables]] = {
    val newQuery = tables.filter(_.id_resto === id)
    db.run(newQuery.result)
  }

  //chercher toures les tables par le nom Resto
  def allTablesByRestoNom(nom:String): Future[Seq[Option[Tables]]]={
    //joinLeft select
    val requestTablesLeftJoin  = for {
      (a,b) <- resto joinLeft tables on (_.id === _.id_resto)
    } yield(b)
    db.run(requestTablesLeftJoin.result)
  }

  //entrer une tables
  def insert(table: Tables): Future[Unit] = db.run(tables += table).map { _ => () }

  //un schema de la table "tables"
  class TablesSchema (tag: Tag) extends Table[Tables](tag, "tables") {
    def id=column[Int]("id",O.PrimaryKey,O.AutoInc)
    def numero=column[Int]("numero")
    def id_resto=column[Int]("id_resto")
    def * =(id,numero,id_resto)<>(Tables.tupled,Tables.unapply)
  }
}


