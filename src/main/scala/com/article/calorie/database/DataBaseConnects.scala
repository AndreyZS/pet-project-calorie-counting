package com.article.calorie.database

import cats.effect.{Async, IO, Resource, implicits}
import doobie.ExecutionContexts
import doobie.hikari.HikariTransactor
import org.flywaydb.core.Flyway

class DataBaseConnects[F[_]: Async] {
  private val dataBaseName     = sys.env.getOrElse("DATA_BASE_NAME", "test")
  private val dataBaseUser     = sys.env.getOrElse("DATA_BASE_USER", "postgres")
  private val dataBasePassword = sys.env.getOrElse("DATA_BASE_PASSWORD", "5213")
  private val sizeTreadPool    = sys.env.getOrElse("DATA_BASE_SIZE_POOL", "5").toInt

  val postgres: Resource[F, HikariTransactor[F]] = for {
    context <- ExecutionContexts.fixedThreadPool[F](sizeTreadPool)
    connect <- HikariTransactor.newHikariTransactor[F](
      "org.postgresql.Driver",
      "jdbc:postgresql:" + dataBaseName,
      dataBaseUser,
      dataBasePassword,
      context
    )
  } yield connect

  def initialize(transactor: HikariTransactor[IO]): IO[Unit] = {
    transactor.configure { dataSource =>
      IO {
        val flyWay = Flyway.configure().dataSource(dataSource).load()
        flyWay.migrate()
        ()
      }
    }
  }

}
