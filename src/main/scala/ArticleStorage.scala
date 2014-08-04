
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
import akka.actor.Actor
import akka._
import akka.actor.{ ActorSystem, Actor, Props }
import akka.routing.RoundRobinRouter

object ArticleStore {
  var totalArticleList: List[Article] = Nil
  val articleFile = scala.io.Source.fromFile("src/main/resources/testerFile.txt").mkString

  case class Article(id: String,title: String,author: String,pub: String,up: String,ab: String)

  def createArticle(newArt: Article): List[Article] = {
    val outFile = new java.io.FileWriter("src/main/resources/testerFile.txt",true)
    if(articleFile.toLowerCase contains newArt.id.toLowerCase){
      outFile.close
      Nil
    }
    else {
      println("New Article(s)"+"\n")
      totalArticleList = totalArticleList :+ newArt
      outFile.write(newArt.id)
      outFile.write(newArt.title)
      outFile.write(newArt.author)
      outFile.write(newArt.pub)
      outFile.write(newArt.up)
      outFile.write(newArt.ab)
      outFile.write("\n")
      jsonFormatting(newArt)

       outFile.close


    }

    totalArticleList


  }



  def jsonFormatting(newArt: Article): Unit = {

    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val format = jsonFormat6(Article)

    }

    import MyJsonProtocol._
    val toJson = Article(newArt.id,newArt.title,newArt.author,newArt.pub,newArt.up,newArt.ab).toJson
    val article = toJson.convertTo[Article]
    println("Aritlce: [")
    println(article.id)
    println(article.title)
    println(article.author)
    println(article.pub)
    println(article.up)
    println(article.ab)
    println("]")


  }


}
