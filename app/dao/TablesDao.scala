package dao

import javax.inject.Inject

import models.Tables
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
    //System.out.println("nom resto:"+nom)
    //joinLeft select
    val requestTablesLeftJoin  = for {
      // Si plusieurs filters est resto.filter(_.nom === nom).filter(_.id === 1)
      (a,b) <- resto.filter(_.nom === nom) joinLeft tables on (_.id === _.id_resto)
    } yield(b)

    db.run(requestTablesLeftJoin.result)
  }

  //entrer une tables
  def insert(table: Tables): Future[Unit] = db.run(tables += table).map { _ => () }

  def insertUneTable(numero:Int,id_resto:Int) = DBIO.seq(
    tables.map(t => (t.numero,t.qr,t.id_resto)) += (numero,"",id_resto)
    //db.run(tables.schema.create)
  )

  def tableExist(nomResto:String,numeroTableAdd:Int):Future[Tables] = {
    //chercher un resto par son nom

    val unResto = resto.filter(_.nom === nomResto)
    val requestTablesLeftJoin  = for {
      (a,b) <-  tables.filter(_.numero === numeroTableAdd) joinLeft unResto on(_.id_resto === _.id)
    }yield(a)
    db.run(requestTablesLeftJoin.result.head)
  }

  def chercherUneTableByNumero(numero:Int): DBIO[Boolean] ={
    tables.filter(_.numero === numero).exists.result
  }

  //un schema de la table "tables"
  class TablesSchema (tag: Tag) extends Table[Tables](tag, "tables") {
    def id=column[Int]("id",O.PrimaryKey,O.AutoInc)
    def numero=column[Int]("numero")
    def qr = column[String]("qr")
    def id_resto=column[Int]("id_resto")
    def * =(id,numero,id_resto)<>(Tables.tupled,Tables.unapply)
  }
}


