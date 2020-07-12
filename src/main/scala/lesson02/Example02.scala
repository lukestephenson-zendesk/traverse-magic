package lesson02

import common.{User, Users}

object Example02 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Option[List[User]] = {
    ids.map(loadUser)
    ???
  }

  def loadUser(id: Int): Option[User] = {
    if (id > 10) None
    else Some(User(s"user $id"))
  }

}
