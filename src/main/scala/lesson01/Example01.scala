package lesson01

import common.{User, Users}

object Example01 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): List[User] = {
    ids.map(loadUser)
  }

  def loadUser(id: Int): User = {
    User(s"user $id")
  }
}
