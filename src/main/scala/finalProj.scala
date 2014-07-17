//fix all of the while loops and use recursion instead
//try to replace all of the vars with vals
package com.banno.finalProj
import spray.routing.SimpleRoutingApp
import akka.actor.{ActorSystem,Props}
import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import spray.http._
import MediaTypes._
import scala.util.{Failure,Success}
import scala.concurrent.duration._
import scala.io.Source
import spray.routing._

trait Server extends SimpleRoutingApp with RoutePath {


  // this is where the server is starting
  startServer(interface = "localhost", port = 8080)(route)
  println("The server is starting")

}
