package for_comprehension
import lib._
import scalaz.concurrent.Task 

case class Person(name: String, livesAt: Int, isRich: Boolean)
case class Address(city: String)

object DAO{
  def personById(id: Int):  Task[Person] = ???
  def addressById(id: Int): Task[Address] = ???
}

object Main extends App{

  println( showPerson(1).unsafePerformSync )

  def showPerson(id: Int): Task[String] = {
    val start = now()
    for{
      p <- DAO.personById(id)
      _ = logger.log("retrieved person")
      suffix <- (
        if(p.isRich)
          DAO.addressById( p.livesAt ).map( " is rich and lives in " + _.city )
        else Task.now("")
      )
      _ <- stats.write(now() - start, "showPerson")
    } yield p.name ++ suffix
  }
}
