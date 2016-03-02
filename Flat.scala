package flat_comprehension
import lib._
import org.cvogt.flow._
import scalaz.concurrent.Task 

case class Person(name: String, livesAt: Int, isRich: Boolean)
case class Address(city: String)

object DAO{
  def personById(id: Int):  Task[Person] = Task.now( Person( "Silvio", 1, true ) )
  def addressById(id: Int): Task[Address] = Task.now( Address( "Rome" ) )
}

object Main extends App{

  println( showPerson(1).unsafePerformSync )

  def showPerson(id: Int): Task[String] = Task.flat{
    val start = now()
    val p = DAO.personById( id ).value
    logger.log( "retrieved person" )
    val result = p.name ++ (
      if( p.isRich )
        " is rich and lives in " ++ DAO.addressById( p.livesAt ).value.city
      else ""
    )
    stats.write( now() - start, "showPerson" )
    result
  }

  implicit class TaskComprehension(f: Task.type){
    def flat = org.cvogt.flow.flat[Task]
  }
}
