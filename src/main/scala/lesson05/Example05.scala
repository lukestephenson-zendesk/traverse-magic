package lesson05

import common.{User, Users}
import lesson04.{Lists, Switchable}

import scala.util.{Failure, Success, Try}

object Example05 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Try[List[User]] = {
    implicit val trySwitch = new Switchable[Try] {
      override def pure[T](value: T): Try[T] = Success(value)

      override def map2[A, B, Z](a: Try[A], b: Try[B])(f: (A, B) => Z): Try[Z] = {
        a.flatMap(a2 => b.map(b2 => f(a2, b2)))
      }
    }

    Lists.sequence(ids.map(loadUser))
  }

  def loadUser(id: Int): Try[User] = {
    if (id > 10) Failure(new RuntimeException("bad"))
    else Success(User(s"user $id"))
  }

}
