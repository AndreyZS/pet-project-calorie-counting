scalaVersion := "2.13.8"

name := "graph"
organization := "ch.epfl.scala"
version := "1.0"

val doobieVersion = "1.0.0-RC1"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect"      % "3.3.12",
  "org.tpolecat"  %% "doobie-core"      % doobieVersion,
  "org.tpolecat"  %% "doobie-postgres"  % doobieVersion,
  "org.tpolecat"  %% "doobie-hikari"    % doobieVersion,
  "org.tpolecat"  %% "doobie-scalatest" % doobieVersion % "test",
  "eu.timepit"    %% "refined"          % "0.9.29",
  "eu.timepit"    %% "refined-cats"     % "0.9.29",
  "org.slf4j"      % "slf4j-api"        % "2.0.0-alpha7",
  "org.slf4j"      % "slf4j-simple"     % "2.0.0-alpha7"
)

//enablePlugins(CalibanPlugin)
