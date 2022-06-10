package domain.entity

import doobie.util.Read

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID
import doobie.postgres.implicits._
import domain.entity.AccountLogin
sealed trait Entity {
  val id: UUID
}

final case class Account(id: UUID, login: String, heft: Double, dob: LocalDate)                  extends Entity
final case class Group(id: UUID, name: String)                                                   extends Entity
final case class Meal(id: UUID, typeMeat: TypeMeat, date: LocalDateTime, products: Seq[Product]) extends Entity

final case class Product(
    id: UUID,
    name: String,
    kilocalories: Float,
    squirrels: Float,
    fats: Float,
    carbohydrates: Float
) extends Entity

object Account {
  implicit val read: Read[Account] =
    Read[(UUID, String, Double, LocalDate)].map { case (id: UUID, login: String, heft: Double, dob: LocalDate) =>
      Account(id, login, heft, dob)
    }
}

case class Test(id: UUID, login: AccountLogin) extends Entity
