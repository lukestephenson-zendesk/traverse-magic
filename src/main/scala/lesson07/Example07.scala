package lesson07

import cats.Applicative
import common.{User, Users}
import lesson04.{Lists, Switchable}
import cats.implicits._

object Example07 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    implicit val optionApplicative = new Applicative[Option] {
      override def pure[A](x: A): Option[A] = Some(x)

      //maybeA.flatMap(a => maybeB.map(b => fn(a,b)))
      override def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] = {
        fa.flatMap(a => ff.map(fn => fn(a)))
      }
    }

    ids.traverse(loadUser)

    ids.traverse()
//    Lists.traverse(ids)(loadUser)
  }

  def load2(id: Int): Either[]
  def loadUser(id: Int): Option[User] = {
    if (id > 2) None
    else Some(User(s"user $id"))
  }

}

