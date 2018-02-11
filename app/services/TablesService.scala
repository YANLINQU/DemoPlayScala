package services

import javax.inject.Inject

import dao.TablesDao
import models.Tables

import scala.concurrent.{ExecutionContext, Future}

class TablesService @Inject()(tablesDao: TablesDao)(implicit executionContext: ExecutionContext) {

  def allTables(): Future[Seq[Tables]] = tablesDao.all()

  def allTablesByRestoId(id:Int) : Future[Seq[Tables]] = tablesDao.allTablesByRestoId(id)

  def allTablesByRestoNom(nom:String) : Future[Seq[Option[Tables]]] = tablesDao.allTablesByRestoNom(nom)
}
