package lib

import scalaz.concurrent.Task 

object now{
  def apply() = 0
}
object logger{
  def log(msg: String) = System.err.println(msg)
}
object stats{
  def write(time: Int, name: String): Task[Unit] = Task.now(())
}