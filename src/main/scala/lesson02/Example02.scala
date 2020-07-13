package lesson02

import common.{User, Users}

object Example02 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    val users: List[Option[User]] = ids.map(loadUser)
    switch(users)
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 10) None
    else Some(User(s"user $id"))
  }

  def switch[T](list: List[Option[T]]): Option[List[T]] = {
    list match {
      case maybeHead :: tail =>
        maybeHead match {
          case Some(element) =>
            val tailSwitch: Option[List[T]] = switch(tail)
            tailSwitch.map(element :: _)
          case None => None
        }
      case Nil => Some(List.empty)
    }
  }
}
