package badpractice.getOrCreateTransaction.bad

import badpractice.getOrCreateTransaction.shared.User

import scala.util.{Success, Try}

class UserRepo {
  // fails if no transaction
  def save(u: User): Try[Unit] = Success(())
}
