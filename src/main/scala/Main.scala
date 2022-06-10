import cats.effect.{ExitCode, IO, IOApp}
import database.DataBaseConnects
import domain.entity.Account
import domain.service.Repository

import java.time.LocalDate
import java.util.UUID

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val connect = new DataBaseConnects()
    val useRepository: IO[Unit] = connect.postgres.use { w =>
      val user = Repository.userRepository(w)
      for {
        account <- user.save(Account(
          id = UUID.randomUUID(),
          login = "TEST",
          heft = 50.42f,
          dob = LocalDate.now(),
        ))
        userWithOneID <- user.findById(account.get.id) >> user.delete(account.get.id)
        _             <- IO.println(userWithOneID)

      } yield ()
    }

    useRepository.as(ExitCode.Success)
  }
}
