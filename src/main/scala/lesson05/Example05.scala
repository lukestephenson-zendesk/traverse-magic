package lesson05

import common.{User, Users}
import lesson04.{Lists, Switchable}

import scala.util.{Failure, Success, Try}

object Example05 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Try[List[User]] = {
    implicit val trySwitchable = new Switchable[Try] {
      override def pure[T](value: T): Try[T] = Success(value)

      override def map2[A, B, Z](maybeA: Try[A], maybeB: Try[B])(fn: (A, B) => Z): Try[Z] = {
        maybeA.flatMap(a => maybeB.map(b => fn(a,b)))
      }
    }

    Lists.traverse(ids)(loadUser)
  }

  def loadUser(id: Int): Try[User] = {
    if (id > 2) Failure(new RuntimeException("db password invalid"))
    else Success(User(s"user $id"))
  }

}

