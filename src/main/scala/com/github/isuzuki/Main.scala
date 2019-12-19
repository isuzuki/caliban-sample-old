package com.github.isuzuki

import caliban.schema.GenericSchema
import caliban.{GraphQL, Http4sAdapter, RootResolver}
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.CORS
import zio.clock.Clock
import zio.console.Console
import zio.interop.catz._
import zio.{RIO, Task, ZIO}

object Main extends CatsApp with GenericSchema[Console with Clock] {
  type ExampleTask[A] = RIO[Console with Clock, A]

  // resolver
  val queries = Queries(
    () => Service.getCharacters,
    args => Service.findCharacter(args),
  )
  val mutations = Mutations(
    args => Task(Service.createCharacter(args)),
    args => Task(Service.deleteCharacter(args)),
  )

  val interpreter = GraphQL.graphQL(RootResolver(queries, mutations))

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    (for {
      _ <- BlazeServerBuilder[ExampleTask]
        .withHttpApp(
          Router[ExampleTask](
            "/api/graphql" -> CORS(Http4sAdapter.makeRestService(interpreter)),
          ).orNotFound,
        )
        .resource
        .toManaged
        .useForever
    } yield 0).catchAll(e => zio.console.putStrLn(e.toString).as(1))
}
