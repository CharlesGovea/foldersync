import mill._, scalalib._

object Foldersync extends ScalaModule {
  def scalaVersion = "3.2.0"

  def ivyDeps = Agg(
    ivy"org.scalameta::munit:0.7.29",
    ivy"com.lihaoyi::os-lib:0.8.1"
  )

  object test extends Tests {
    def testFrameworks = Seq("munit.Framework")
  }
}