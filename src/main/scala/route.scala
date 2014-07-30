

package com.banno.finalProj

import spray.routing._
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



trait RoutePath extends App with SimpleRoutingApp  {
  implicit val system = ActorSystem("on-spray-can")
  val route: Route =


      get {

        pathSingleSlash {
        redirect("/home",StatusCodes.Found)

        } ~
        path("home") {
          complete {
            <html>
              <h1>Welcome, please get some updates from the verge<em> used by spray</em> on <em>spray-can</em>!</h1>in
              <p>(<a href="/stop?method=post">please click this to stop the server</a>)</p>
              <o>(<a href="/articles?method=post">please click this to get the latest article</a>)</o>
              <m>(<a href="/dictionary?method=post">please click this to get the dictionary</a>)</m>
            </html>
          }
        }
      } ~
      (post | parameter('method ! "post")) {
       path("stop") {
        complete {
          system.scheduler.scheduleOnce(1.second)(system.shutdown())(system.dispatcher)
          "Shutting down in 1 second..."

          }
        }

      } ~
      (post | parameter('method ! "post")) {
      path("dictionary"){
        complete{
          <html>
            <q>{DictionaryPath.dictPrint()}</q>
          </html>
        }
      }
      } ~
      (post | parameter('method ! "post")) {
      path("articles"){
        complete{
          StringSlice.xmlSlice(1)

          <html>
            <q>{ListStore.getList()}</q>
          </html>
        }
      }
      } ~
      path("titles"){
        get{

          parameter('q) { searchString =>
            val otherList: List[String] = Nil
            val newList = TitleSearch.titleSearcher(searchString,otherList,2)
            if(newList != Nil){
              complete(s"The query is '$searchString these are the results '$newList'")
            }
            else{
              complete(s"Sorry, nothing showed up in our records with the titles you wanted")
            }
          }
          }
      } ~
    //this will search the content and find the content in it that the user searched
      path("content"){
        get{
          parameter('q) { searchString =>
            val otherList: List[String] = Nil
            val newList = ContentSearch.contentSearcher(searchString,otherList,6)
            if(newList != Nil){
              complete(s"The query is '$searchString these are the results '$newList'")
            }
            else{
              complete(s"Sorry, nothing showed up in our records with the titles you wanted")
            }


          }
        }
      } ~
      path("article"){
        get{
          parameter('q) { searchString =>
            val otherList: List[String] = Nil
            val newList = ArticleSearch.articleSearcher(searchString,otherList,1)
            if(newList != Nil){
              complete(s"The query is: '$searchString these are the results '$newList'")
            }
            else{
              complete(s"Sorry, nothing showed up in our records with the titles you wanted")
            }
          }


        }
      } ~
      path("ping"){
        complete("pong")
      }


}
object Boot extends Server
