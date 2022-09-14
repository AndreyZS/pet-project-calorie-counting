package com.article.calorie.domain.entity

import doobie.util.Read

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID
import doobie.postgres.implicits._
sealed trait Entity {
  val id: UUID
}

final case class Group(id: UUID, name: String)                                                   extends Entity
final case class Meal(id: UUID, typeMeat: TypeMeat, date: LocalDateTime, products: Seq[FoodProduct]) extends Entity




//case class Test(id: UUID, login: AccountLogin) extends Entity
