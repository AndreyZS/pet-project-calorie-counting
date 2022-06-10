package database

import cats.effect.{IO, Resource}
import doobie.ExecutionContexts
import doobie.hikari.HikariTransactor
class DataBaseConnects {
  private val dataBaseName     = sys.env.getOrElse("DATA_BASE_NAME", "test")
  private val dataBaseUser     = sys.env.getOrElse("DATA_BASE_USER", "postgres")
  private val dataBasePassword = sys.env.getOrElse("DATA_BASE_PASSWORD", "5213")

  val postgres: Resource[IO, HikariTransactor[IO]] = for {
    context <- ExecutionContexts.fixedThreadPool[IO](5)
    connect <- HikariTransactor.newHikariTransactor[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql:" + dataBaseName,
      dataBaseUser,
      dataBasePassword,
      context
    )
  } yield connect

}
