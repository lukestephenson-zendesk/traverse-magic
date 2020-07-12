package lesson02

import common.{User, Users}

object Example02 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    switcharoo(ids.map(loadUser))
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 10) None
    else Some(User(s"user $id"))
  }

  def switcharoo[T](list: List[Option[T]]): Option[List[T]] = {
    list match {
      case maybeHead :: tail =>
        maybeHead match {
          case Some(head) =>
            switcharoo(tail) match {
              case Some(tailSwitched) => Some(head :: tailSwitched)
              case None => None
            }
          case None => None
        }
      case Nil => Some(List.empty)
    }
  }
}
