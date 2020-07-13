package lesson06

import common.{User, Users}
import lesson04.{Lists, Switchable}

object Example06 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    implicit val optionSwitchable = new Switchable[Option] {
      override def pure[T](value: T): Option[T] = Some(value)

      override def map2[A, B, Z](maybeA: Option[A], maybeB: Option[B])(fn: (A, B) => Z): Option[Z] = {
        maybeA.flatMap(a => maybeB.map(b => fn(a,b)))
      }
    }

    Lists.traverse(ids)(loadUser)
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 10) None
    else Some(User(s"user $id"))
  }

}

