package domain

import doobie.util.{Get, Put}
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.cats.CatsRefinedTypeOpsSyntax
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Positive
package object entity {
  type AccountLogin = String Refined NonEmpty
  type AccountHeft  = Float Refined Positive

  type ProductName         = String Refined NonEmpty
  type ProductKilocalories = Float Refined Positive
  type ProductSquirrels    = Float Refined Positive
  type ProductFats         = Float Refined Positive
  type ProductFloat        = Float Refined Positive
  final object AccountLogin extends RefinedTypeOps[AccountLogin, String] with CatsRefinedTypeOpsSyntax
  final object AccountHeft  extends RefinedTypeOps[AccountHeft, Float] with CatsRefinedTypeOpsSyntax

  final object ProductName         extends RefinedTypeOps[ProductName, String] with CatsRefinedTypeOpsSyntax
  final object ProductKilocalories extends RefinedTypeOps[ProductKilocalories, Float] with CatsRefinedTypeOpsSyntax
  final object ProductSquirrels    extends RefinedTypeOps[ProductSquirrels, Float] with CatsRefinedTypeOpsSyntax
  final object ProductFats         extends RefinedTypeOps[ProductFats, Float] with CatsRefinedTypeOpsSyntax
  final object ProductFloat        extends RefinedTypeOps[ProductFloat, Float] with CatsRefinedTypeOpsSyntax

  object implicits {
    implicit val getAccountLogin: Get[AccountLogin]               = Get[String].map(AccountLogin.unsafeFrom)
    implicit val putAccountLogin: Put[AccountLogin]               = Put[String].contramap(_.value)
    implicit val getAccountHeft: Get[AccountHeft]                 = Get[Float].map(AccountHeft.unsafeFrom)
    implicit val putAccountHeft: Put[AccountHeft]                 = Put[Float].contramap(_.value)
    implicit val getProductKilocalories: Get[ProductKilocalories] = Get[Float].map(ProductKilocalories.unsafeFrom)
    implicit val putProductKilocalories: Put[ProductKilocalories] = Put[Float].contramap(_.value)
    implicit val getProductSquirrels: Get[ProductSquirrels]       = Get[Float].map(ProductSquirrels.unsafeFrom)
    implicit val putProductSquirrels: Put[ProductSquirrels]       = Put[Float].contramap(_.value)
    implicit val getProductFats: Get[ProductFats]                 = Get[Float].map(ProductFats.unsafeFrom)
    implicit val putProductFats: Put[ProductFats]                 = Put[Float].contramap(_.value)
    implicit val getProductFloat: Get[ProductFloat]               = Get[Float].map(ProductFloat.unsafeFrom)
    implicit val putProductFloat: Put[ProductFloat]               = Put[Float].contramap(_.value)
  }
}
