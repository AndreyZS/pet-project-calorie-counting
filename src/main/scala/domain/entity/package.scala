package domain

import doobie.util.{Get, Put}
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.cats.CatsRefinedTypeOpsSyntax
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Positive
package object entity {
  type AccountLogin = String Refined NonEmpty
  type AccountHeft  = Float Refined Positive
  final object AccountLogin extends RefinedTypeOps[AccountLogin, String] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[AccountLogin] = Get[String].map(AccountLogin.unsafeFrom)
    implicit val actorNamePut: Put[AccountLogin] = Put[String].contramap(_.value)
  }
  final object AccountHeft extends RefinedTypeOps[AccountHeft, Float] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[AccountHeft] = Get[Float].map(AccountHeft.unsafeFrom)
    implicit val actorNamePut: Put[AccountHeft] = Put[Float].contramap(_.value)
  }

  type ProductName         = String Refined NonEmpty
  type ProductKilocalories = Float Refined Positive
  type ProductSquirrels    = Float Refined Positive
  type ProductFats         = Float Refined Positive
  type ProductFloat        = Float Refined Positive
  final object ProductName extends RefinedTypeOps[ProductName, String] with CatsRefinedTypeOpsSyntax
  final object ProductKilocalories extends RefinedTypeOps[ProductKilocalories, Float] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[ProductKilocalories] = Get[Float].map(ProductKilocalories.unsafeFrom)
    implicit val actorNamePut: Put[ProductKilocalories] = Put[Float].contramap(_.value)
  }
  final object ProductSquirrels extends RefinedTypeOps[ProductSquirrels, Float] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[ProductSquirrels] = Get[Float].map(ProductSquirrels.unsafeFrom)
    implicit val actorNamePut: Put[ProductSquirrels] = Put[Float].contramap(_.value)
  }
  final object ProductFats extends RefinedTypeOps[ProductFats, Float] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[ProductFats] = Get[Float].map(ProductFats.unsafeFrom)
    implicit val actorNamePut: Put[ProductFats] = Put[Float].contramap(_.value)
  }
  final object ProductFloat extends RefinedTypeOps[ProductFloat, Float] with CatsRefinedTypeOpsSyntax {
    implicit val actorNameGet: Get[ProductFloat] = Get[Float].map(ProductFloat.unsafeFrom)
    implicit val actorNamePut: Put[ProductFloat] = Put[Float].contramap(_.value)
  }

}
