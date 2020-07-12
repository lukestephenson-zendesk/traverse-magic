package lesson03

import common.{User, Users}

import scala.util.{Failure, Success, Try}

object Example03 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Try[List[User]] = {
    ids.map(loadUser)
    ???
  }

  def loadUser(id: Int): Try[User] = {
    if (id > 10) Failure(new RuntimeException("hello"))
    else Success(User(s"user $id"))
  }

}
