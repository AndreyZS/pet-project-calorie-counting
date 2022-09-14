package com.article.calorie.domain.entity

import com.article.calorie.domain.entity.FoodProduct.FoodProductCompound
import doobie.util.{Get, Put}
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.cats.CatsRefinedTypeOpsSyntax
import eu.timepit.refined.collection.NonEmpty
import com.article.calorie.domain.entity.Compound.implicits._
import doobie.Read
import doobie.postgres.implicits._
import shapeless.HList

import java.util.UUID

final case class FoodProduct(
    id: UUID,
    name: ProductName,
    //compounds:ProductCompound
)

object FoodProduct{
  type FoodProductName = String Refined NonEmpty
  type FoodProductCompound = Seq[Compound] Refined NonEmpty

  final object FoodProductName extends RefinedTypeOps[FoodProductName, String] with CatsRefinedTypeOpsSyntax
  final object ProductCompound extends RefinedTypeOps[FoodProductCompound, Seq[Compound]] with CatsRefinedTypeOpsSyntax

  object implicits {
    implicit val getProductName = Get[String].map(FoodProductName.unsafeFrom)

    implicit val putProductName: Put[FoodProductName] = Put[String].contramap(_.value)

    implicit val readFoodProduct: Read[Either[String, FoodProduct]] = Read[(UUID,String)].map{
      case (id,name)=> for{
        validName <- FoodProductName.from(name)
      } yield FoodProduct(id,validName)
    }
  }
}
