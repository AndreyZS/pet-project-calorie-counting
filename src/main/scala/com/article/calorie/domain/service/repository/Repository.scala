package com.article.calorie.domain.service.repository

import doobie.Fragment

import java.util.UUID

trait Repository[E] {
  val schema: Fragment
  def findById(id: UUID): doobie.ConnectionIO[Option[E]]
  def save(entity: E): doobie.ConnectionIO[Int]
}
