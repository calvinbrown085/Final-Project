
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
import akka.actor.Actor
import akka._
import akka.actor.{ ActorSystem, Actor, Props }
import akka.routing.RoundRobinRouter


import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.PostgreSqlAdapter
import java.util.Calendar

object ArticleStore {

  Class.forName("org.postgresql.Driver")
  SessionFactory.concreteFactory = Some(() =>
    Session.create(
      java.sql.DriverManager.getConnection("jdbc:postgresql://localhost/ArticleStorage","calvinb","calwil100"),
      new PostgreSqlAdapter()))
  case class Article(@Column("id") id: String,
                     @Column("title") title: String,
                     @Column("author") author: String,
                     @Column("published") pub: String,
                     @Column("updated") up: String,
                     @Column("abstract") ab: String)

  def storeArticle(newArt: Article): Unit = {
      transaction {
        ArticleSystem.articles.insert(Article(newArt.id,
                                              newArt.title,
                                              newArt.author,
                                              newArt.pub,
                                              newArt.up,
                                              newArt.ab))
      }
      transaction {
        val queriedArticles: List[Article] = from(ArticleSystem.articles)(e => select(e)).toList




      }
  }
  def createList(newArt: Article): Unit = {
    ListStore.totalArticleList = ListStore.totalArticleList :+ newArt
    jsonFormatting(newArt)
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
