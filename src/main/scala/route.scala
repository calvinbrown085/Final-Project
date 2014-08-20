

package com.banno.finalProj



import akka.actor.{ ActorSystem, Actor, Props, ActorRef }
import akka.util.Timeout
import akka.pattern.{ ask, pipe }


import scala.concurrent.duration._
import scala.concurrent.Await
import spray.routing._
import spray.routing.SimpleRoutingApp
import spray.http._





trait RoutePath extends App with SimpleRoutingApp  {

  implicit val system = ActorSystem()
  val masterActor = system.actorOf(Props[MyActor], name = "myActor1")

  implicit val timeout = Timeout(30 seconds)

  val route: Route =


      get {

        pathSingleSlash {
        redirect("/homePage",StatusCodes.Found)

        } ~
        path("homePage") {
          complete {
            <html>
              <h1>Welcome, please get some updates from the verge!</h1>in
              <p>(<a href="/stop?method=post">please click this to stop the server</a>)</p>
              <o>(<a href="/articles?method=post">please click this to get the latest article</a>)</o>"         "
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
            <q>{masterActor ! "dictPrint"}</q>
          </html>
        }
      }
      } ~
      (post | parameter('method ! "post")) {
      path("articles"){
        complete{
          println("####################### This is the front page of articles #############################")
          masterActor ? "PrintFrontPage"
          masterActor ! "stringSlice"
          <html>
            {}
          </html>
        }
      }
      } ~
      path("titles"){
        get{

          parameter('q) { searchString =>
            val otherList: List[ArticleStore.Article] = Nil
            val newList = TitleSearch.titleSearcher(searchString,otherList)
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
            val otherList: List[ArticleStore.Article] = Nil
            val newList = ContentSearch.contentSearcher(searchString,otherList)
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
            val otherList: List[ArticleStore.Article] = Nil
            val newList = ArticleSearch.articleSearcher(searchString,otherList)
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

class MyActor extends Actor {
  def receive = {
    case "PrintFrontPage" => XmlParser.xmlParser(0,true)
    case "stringSlice" => XmlParser.xmlParser(0,false)
    case "dictPrint" => DictionaryPath.dictPrint()
    case _      =>  println("received unknown message")

  }



}
object Boot extends Server
