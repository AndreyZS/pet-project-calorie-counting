package com.article.calorie

import cats.effect.{ExitCode, IO, IOApp}
import com.article.calorie.database.DataBaseConnects
import com.article.calorie.domain.entity.Account
import com.article.calorie.domain.service.Repository
import org.flywaydb.core.Flyway

import java.time.LocalDate
import java.util.UUID
import doobie.implicits._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val connect = new DataBaseConnects[IO]()
    connect.postgres.use { c =>
      val t = for {
        save <- Repository.accountRepository.save(
          Account(
            UUID.randomUUID(),
            Account.AccountLogin("Login"),
            Account.AccountHeft(53.4),
            LocalDate.of(2000, 12, 11)
          )
        )
        res <- Repository.accountRepository.findById(UUID.randomUUID())
      } yield res
      connect.initialize(c) >> t.transact(c) >> IO.println("я все")
    } >> IO(ExitCode.Success)
  }
}
