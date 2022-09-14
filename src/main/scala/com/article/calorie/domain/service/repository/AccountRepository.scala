package com.article.calorie.domain.service.repository
import com.article.calorie.domain.entity.Account
import doobie.implicits._
import doobie.postgres.implicits._
import com.article.calorie.domain.entity.Account.implicits._
import java.util.UUID

object AccountRepository{
  val schema = sql"select * from account "

  def findById(id: UUID) = (schema++ fr"where id = $id").query[Account].option

  def save(entity: Account) =
      sql"""
         insert into account
         (id, login, heft, dob)
         values (${entity.id}, ${entity.login}, ${entity.heft}, ${entity.dob})
         """
        .update.run
}
