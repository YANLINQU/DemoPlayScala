package services

import javax.inject.Inject

import dao.RestoDao

import scala.concurrent.ExecutionContext

class RestoService @Inject()( restoDao: RestoDao)(implicit executionContext: ExecutionContext){

    def chercherIDByRestoNom(nom:String):Unit ={
      restoDao.chercherIDByRestoNom(nom)
    }

}
