package lesson04

import cats.Foldable

object Lists {

  def sequence[G[_], F[_], T](list: G[F[T]])(implicit f: Switchable[F], g: Foldable[G]): F[G[T]] = {
//    list match {
//      case head :: tail =>
//        f.map2(head, sequence(tail))(_ :: _)
//      case Nil => f.pure(List.empty)
//    }
    traverse(list)(identity)
  }

  def traverse[A, F[_], T](list: List[A])(fn: A => F[T])(implicit f: Switchable[F]): F[List[T]] = {
    list match {
      case head :: tail =>
        val headProcessed: F[T] = fn(head)
        val tailProcessed: F[List[T]] =traverse(tail)(fn)
        f.map2(headProcessed, tailProcessed)(_ :: _)
      case Nil => f.pure(List.empty)

    }
  }
}
