package lesson04

object Lists {

  def sequence[F[_], T](list: List[F[T]])(implicit f: Switchable[F]): F[List[T]] = {
    list match {
      case maybeHead :: tail =>
        f.map2(maybeHead, sequence(tail))(_ :: _)
      case Nil => f.pure(List.empty)
    }
  }

  def traverse[F[_], A, T](list: List[A])(fn: A => F[T])(implicit f: Switchable[F]): F[List[T]] = {
    list match {
      case head :: tail =>
        f.map2(fn(head), traverse(tail)(fn))(_ :: _)
      case Nil => f.pure(List.empty)
    }
  }
}
