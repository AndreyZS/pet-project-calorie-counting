package com.article.calorie.domain.service.repository
import com.article.calorie.domain.entity.FoodProduct

import java.util.UUID
import doobie.implicits._
import doobie.postgres.implicits._
import com.article.calorie.domain.entity.FoodProduct.implicits._

object ProductRepository extends Repository[FoodProduct]{
  val schema = sql"""select * from FoodProduct """

  def findById(id: UUID) = (schema++fr"where id = $id").query[FoodProduct].option

  def save(entity: FoodProduct) =
    sql"""
      insert into FoodProduct (id,name) values (${entity.id},${entity.name})
      """.update.run

}
