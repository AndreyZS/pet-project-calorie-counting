package com.article.calorie

import cats.effect.{ExitCode, IO, IOApp}
import com.article.calorie.database.DataBaseConnects
import com.article.calorie.domain.entity.Account
import com.article.calorie.domain.service.repository.{AccountRepository, Repository}
import org.flywaydb.core.Flyway

import java.time.LocalDate
import java.util.UUID
import doobie.implicits._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val connect = new DataBaseConnects[IO]()
    connect.postgres.use { c =>
      val idRandom = UUID.randomUUID()
      val t = for {
        save <- AccountRepository.save(
          Account(
            idRandom,
            Account.AccountLogin("Login"),
            Account.AccountHeft(53.4),
            LocalDate.of(2000, 12, 11)
          )
        )
        res <- AccountRepository.findById(idRandom)
      } yield (res)
      (connect.initialize(c) >> t.transact(c)) >> IO.println("я все")
    } >> IO(ExitCode.Success)
  }
}
