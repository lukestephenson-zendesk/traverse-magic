package lesson04

import common.{User, Users}

object Example04 {
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

//    val users: List[Option[User]] = ids.map(loadUser)
//    Lists.sequence(users)
    ???
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 2) None
    else Some(User(s"user $id"))
  }

}

