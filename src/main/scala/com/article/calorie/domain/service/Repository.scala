package com.article.calorie.domain.service

import cats.effect.MonadCancelThrow
import com.article.calorie.domain.entity.Account
import com.article.calorie.domain.entity.Account.AccountLogin
import com.article.calorie.domain.entity.implicits._
import doobie.{Fragment, Transactor}
import doobie.implicits._
import doobie.postgres.implicits._
import cats.implicits._
import cats.data._
import com.article.calorie.domain.entity.Account.implicits._

import java.util.UUID

trait Repository[E] {
  def findById(id: UUID): doobie.ConnectionIO[Option[E]]
  def save(entity: E): doobie.ConnectionIO[Option[E]]
}

object Repository {

  val accountRepository: Repository[Account] = new Repository[Account] {
    def findById(id: UUID): doobie.ConnectionIO[Option[Account]] = sql"".query[Account].option

    def save(entity: Account) =
      sql"""
           insert into account
           (id, login, heft, dob)
           values (${entity.id}, ${entity.login}, ${entity.heft}, ${entity.dob})
           returning (id, login, heft, dob)
           """
        .query[Account].option
  }
}
