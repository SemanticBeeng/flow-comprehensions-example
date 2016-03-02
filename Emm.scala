/*
  {
    import emm._
    import emm.compat.scalaz._

    import scalaz._
    import scalaz.concurrent.Task
    import scalaz.std.option._

    def readName: Task[String] = Monad[Task].point("chris")
    def log(msg: String): Task[Unit] = Monad[Task].point(())

    type E = Task |: (String \/ ?) |: Option |: Base

    type MyEmm[T] = Emm[E, T]
    implicit def MyEmmFactory = new Constructor[MyEmm]{
      def create[T](v: T) = Emm.point(v)
    }

    //implicit containsTyped[E,T <: ]()

    import scala.language.implicitConversions
    implicit def liftTask[T](t: Task[T]) = t.liftM[E]
    implicit def liftOption[T](t: Option[T]) = t.liftM[E]
    implicit def liftEither[T](t: String \/ T) = t.liftM[E]

    val effect: MyEmm[String] = flat[MyEmm] { c =>
      val first = c?readName
      val last = c?readName

      val name = c?(if ((first.length * last.length) < 20) Some(s"$first $last") else (None:Option[String]))

      c?(if (name == "Daniel Spiewak") -\/("your kind isn't welcome here") else \/-(()))

      c?log(s"successfully read in $name")

      name
    }

    println(effect)
  }
*/