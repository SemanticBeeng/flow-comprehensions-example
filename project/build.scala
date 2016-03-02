import sbt._
import Keys._

object MyBuild extends Build{
  lazy val root = Project(id = "root", base = file("."),
    settings = Seq(
      scalaVersion := "2.11.7",
      libraryDependencies ++= Seq(
        "org.scala-comprehensions" %% "flow-comprehensions" % "0.0.4-SNAPSHOT",
        "org.scalaz" %% "scalaz-concurrent" % "7.2.0"
      ),
      scalacOptions ++= Seq("-deprecation")
    )
  )
}
