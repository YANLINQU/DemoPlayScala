package services

import javax.inject.Inject

import dao.{RestoDao, TablesDao}
import models.Tables

import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import scala.concurrent.{Await, ExecutionContext, Future}

class TablesService @Inject()(tablesDao: TablesDao,restoDao: RestoDao)(implicit executionContext: ExecutionContext) {

  def allTables(): Future[Seq[Tables]] = tablesDao.all()

  def allTablesByRestoId(id:Int) : Future[Seq[Tables]] = tablesDao.allTablesByRestoId(id)

  def allTablesByRestoNom(nom:String) : Future[Seq[Option[Tables]]] = tablesDao.allTablesByRestoNom(nom)

  def tableExist (nom:String,numeroTable:Int):Boolean = {
    //onComplete
    var trouver:Boolean = false
    //val wait = Await.result(tablesDao.tableExist(nom,numeroTable),Duration.Inf)
    tablesDao.tableExist(nom,numeroTable).onComplete{
      case Success(result) => {
        println("table trouver")
        trouver = true
      }
      case Failure(e) => {
        println("table non trouver")
        trouver = false
      }
    }
    //println("exist:"+tablesDao.chercherUneTableByNumero(numeroTable))
    false
  }

  def addUneTable(nom:String,numeroTable:Int)={
    //val restoNo:Int = restoDao.chercherIDByRestoNom(nom)
    val trouver = tableExist(nom,numeroTable)
    //insertUneTable(restoNo,numeroTable)
    println("trouver?"+trouver)
  }

  def insertUneTable(numero:Int,id_resto:Int) = tablesDao.insertUneTable(numero,id_resto)

}
