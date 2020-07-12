package lesson04

trait Switchable[F[_]] {
  def pure[T](value: T): F[T]

  def map2[A, B, Z](a: F[A], b: F[B])(f: (A, B) => Z): F[Z]
}
