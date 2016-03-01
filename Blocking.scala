package blocking
import lib._
case class Person(name: String, livesAt: Int, isRich: Boolean)
case class Address(city: String)

object DAO{
  def personById(id: Int):  Person = ???
  def addressById(id: Int): Address = ???
}

object Blocking extends App{

  println( showPerson(1) )

  def showPerson(id: Int): String = {
    val start = now()
    val p = DAO.personById(id)
    logger.log("retrieved person")
    val suffix = (
      if(p.isRich)
        " is rich and lives in " ++ DAO.addressById( p.livesAt ).city
      else ""
    )
    stats.write(now() - start, "showPerson")
    p.name ++ suffix
  }
}
