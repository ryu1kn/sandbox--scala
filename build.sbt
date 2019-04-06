
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.ryuichi",
      scalaVersion := "2.12.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "sandbox--scala",
    libraryDependencies ++= Seq(
      "io.argonaut" %% "argonaut" % "6.2.3" % Compile,
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    )
  )
