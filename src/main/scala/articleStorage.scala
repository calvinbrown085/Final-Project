
package com.banno.finalProj

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.PostgreSqlAdapter
import java.util.Calendar

object ArticleStore {
  case class Article(@Column("id") id: String,
                     @Column("title") title: String,
                     @Column("author") author: String,
                     @Column("published") pub: String,
                     @Column("updated") up: String,
                     @Column("abstract") ab: String)

  Class.forName("org.postgresql.Driver")
  SessionFactory.concreteFactory = Some(() =>
    Session.create(
      java.sql.DriverManager.getConnection("jdbc:postgresql://localhost/ArticleStorage","calvinb","calwil100"),
      new PostgreSqlAdapter()))

  def storeArticle(newArt: Article): Unit = {
      transaction {
        ArticleSchema.articles.insert(Article(newArt.id,
                                              newArt.title,
                                              newArt.author,
                                              newArt.pub,
                                              newArt.up,
                                              newArt.ab))
        val queriedArticles: List[Article] = from(ArticleSchema.articles)(e => select(e)).toList
      }
  }






}
