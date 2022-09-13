package com.article.calorie.domain.entity

import doobie.util.Read

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID
import doobie.postgres.implicits._
sealed trait Entity {
  val id: UUID
}

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


//case class Test(id: UUID, login: AccountLogin) extends Entity
