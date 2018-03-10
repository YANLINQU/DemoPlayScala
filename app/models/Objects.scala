package models

case class Tables(id:Int,numero:Int,id_resto:Int)

case class Resto(id:Int,nom:String,adresse:String,ville:String,postal:String,revenu:Float,id_restaurateur:Int)
