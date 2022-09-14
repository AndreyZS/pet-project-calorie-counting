package com.article.calorie.domain.entity

import java.util.UUID
import cats.implicits._
import com.article.calorie.domain.entity.Compound.{CompoundAmount, CompoundName}
import doobie.Write
import doobie.implicits._
import doobie.postgres.implicits._
import doobie.util.{Get, Put, Read}
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.cats.CatsRefinedTypeOpsSyntax
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Positive

final case class Compound(id: UUID, name: CompoundName, amount: CompoundAmount)

object Compound {
  type CompoundName   = String Refined NonEmpty
  type CompoundAmount = Double Refined Positive

  final object CompoundName   extends RefinedTypeOps[CompoundName, String] with CatsRefinedTypeOpsSyntax
  final object CompoundAmount extends RefinedTypeOps[CompoundAmount, Double] with CatsRefinedTypeOpsSyntax

  object implicits {
    implicit val getCompoundName = Get[String].map(CompoundName.from)
    implicit val getCompoundAmount = Get[Double].map(CompoundAmount.from)

    implicit val putCompoundName: Put[CompoundName]     = Put[String].contramap(_.value)
    implicit val putCompoundAmount: Put[CompoundAmount] = Put[Double].contramap(_.value)

    implicit val readCompound = Read[(UUID, String, Double)].map {
      case (id, login, heft) => for {
          l <- CompoundName.from(login)
          d <- CompoundAmount.from(heft)
        } yield Compound(id, l, d)
    }

    implicit val writeCompound: Write[Compound] = Write[(UUID, String, Double)].contramap { compound =>
      (compound.id, compound.name.value, compound.amount.value)
    }
  }
}
