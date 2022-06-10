package domain.service

import cats.effect.MonadCancelThrow
import domain.entity.{Account, AccountLogin, Entity, Test}
import doobie.Transactor
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.postgres.implicits._
import java.util.UUID

trait Repository[F[_], G[_], Ent <: Entity] {
  def findById(id: UUID): F[Option[Ent]]
  def findAll: F[G[Ent]]
  def save(entity: Ent): F[Option[Ent]]
  def delete(id: UUID): F[Int]
  def findBy(where: Fragment): F[G[Ent]]
}

object Repository {
  def userRepository[F[_]: MonadCancelThrow](postgres: Transactor[F]): Repository[F, Seq, Account] =
    new Repository[F, Seq, Account] {
      private val selectFrom = sql"SELECT id, login, heft, dob FROM account "
      override def findById(id: UUID): F[Option[Account]] =
        (selectFrom ++ fr"where id = $id limit 1").query[Account].option.transact(postgres)
      override def findAll: F[Seq[Account]] = selectFrom.query[Account].to[Seq].transact(postgres)

      override def save(entity: Account): F[Option[Account]] =
        sql"""
            insert into account
                (id, login, heft, dob)
            values (${entity.id}, ${entity.login}, ${entity.heft}, ${entity.dob})
            returning id, login, heft, dob
        """.query[Account].option.transact(postgres)

      override def delete(id: UUID): F[Int] =
        sql"delete FROM account where id = $id ".update.run.transact(postgres)

      override def findBy(where: Fragment): F[Seq[Account]] =
        (selectFrom ++ where).query[Account].to[Seq].transact(postgres)

    }

  def testRepository[F[_]: MonadCancelThrow](postgres: Transactor[F]): Repository[F, Seq, Test] =
    new Repository[F, Seq, Test] {
      override def findById(id: UUID): F[Option[Test]] = ???

      override def findAll: F[Seq[Test]] = sql"select (id,login) from test".query[Test].to[Seq].transact(postgres)

      override def save(entity: Test): F[Option[Test]] = ???

      override def delete(id: UUID): F[Int] = ???

      override def findBy(where: Fragment): F[Seq[Test]] = ???
    }
}
