name := "DemoPlayScala"
 
version := "1.0" 
      
lazy val `demoplayscala` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.11.11"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies ++= Seq(
  jdbc ,
  evolutions,
  ehcache ,
  ws ,
  specs2 % Test ,
  guice ,
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)