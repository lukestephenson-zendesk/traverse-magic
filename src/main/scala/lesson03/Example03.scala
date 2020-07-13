package lesson03

import common.{User, Users}

import scala.util.{Failure, Success, Try}

object Example03 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Try[List[User]] = {
    val users: List[Try[User]] =ids.map(loadUser)
    switch(users)
  }

  def loadUser(id: Int): Try[User] = {
    if (id > 2) Failure(new RuntimeException("db password invalid"))
    else Success(User(s"user $id"))
  }

  def switch[T](list: List[Try[T]]): Try[List[T]] = {
    list match {
      case maybeHead :: tail =>
        maybeHead match {
          case Success(element) =>
            val tailSwitch: Try[List[T]] = switch(tail)
            tailSwitch.map(element :: _)
          case Failure(e) => Failure(e)
        }
      case Nil => Success(List.empty)
    }
  }
}

// List[Option[User]] => Option[List[User]]
// List[F[User]] => F[List[User]]
