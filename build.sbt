name := "caliban-sample"

version := "0.1"

scalaVersion := "2.13.1"

val calibanVersion = "0.4.1"

libraryDependencies ++= Seq(
  "com.github.ghostdogpr" %% "caliban" % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-http4s" % calibanVersion, // routes for http4s
  "com.github.ghostdogpr" %% "caliban-cats" % calibanVersion, // interop with cats effect
)
