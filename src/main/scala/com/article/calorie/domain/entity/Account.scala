package com.article.calorie.domain.entity

import com.article.calorie.domain.entity.Account.{AccountHeft, AccountLogin}
import doobie.util.{Get, Put, Read}
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.cats.CatsRefinedTypeOpsSyntax
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Positive
import doobie.postgres.implicits._
import cats.implicits._
import java.time.LocalDate
import java.util.UUID

final case class Account(id: UUID, login: AccountLogin, heft: AccountHeft, dob: LocalDate)

object Account {

  type AccountLogin = String Refined NonEmpty
  type AccountHeft  = Double Refined Positive

  final object AccountLogin extends RefinedTypeOps[AccountLogin, String] with CatsRefinedTypeOpsSyntax

  final object AccountHeft extends RefinedTypeOps[AccountHeft, Double] with CatsRefinedTypeOpsSyntax

  object implicits {
    implicit val getAccountLogin: Get[AccountLogin] = Get[String].map(AccountLogin.unsafeFrom)
    implicit val getAccountHeft: Get[AccountHeft] = Get[Double].map(AccountHeft.unsafeFrom)

    implicit val putAccountLogin: Put[AccountLogin] = Put[String].contramap(_.value)
    implicit val putAccountHeft: Put[AccountHeft]   = Put[Double].contramap(_.value)

    implicit val read = Read[(UUID, String, Double, LocalDate)].map {
      case (id, login, heft, dob) => for {
          l <- AccountLogin.from(login)
          d <- AccountHeft.from(heft)
        } yield Account(id, l, d, dob)
    }
  }

}
