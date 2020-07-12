package lesson04

import common.{User, Users}

object Example04 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    implicit val optionSwitch = new Switchable[Option] {
      override def pure[T](value: T): Option[T] = Some(value)

      override def map2[A, B, Z](a: Option[A], b: Option[B])(f: (A, B) => Z): Option[Z] = {
        a.flatMap(a2 => b.map(b2 => f(a2, b2)))
      }
    }

    Lists.sequence(ids.map(loadUser))
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 10) None
    else Some(User(s"user $id"))
  }
}
