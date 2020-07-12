package lesson03

import common.{User, Users}

import scala.util.{Failure, Success, Try}

object Example03 {
  def main(args: Array[String]): Unit = {
    println(loadUsers(Users.ids))
  }

  def loadUsers(ids: List[Int]): Try[List[User]] = {
    switcharoo(ids.map(loadUser))
  }

  def loadUser(id: Int): Try[User] = {
    if (id > 10) Failure(new RuntimeException("hello"))
    else Success(User(s"user $id"))
  }

  def switcharoo[T](list: List[Try[T]]): Try[List[T]] = {
    list match {
      case maybeHead :: tail =>
        maybeHead match {
          case Success(head) =>
            switcharoo(tail) match {
              case Success(tailSwitched) => Success(head :: tailSwitched)
              case Failure(t) => Failure(t)
            }
          case Failure(t) => Failure(t)
        }
      case Nil => Success(List.empty)
    }
  }
}
