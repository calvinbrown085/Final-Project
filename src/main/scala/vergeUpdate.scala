
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
object ListBuilder {










  //this builds the readable string for the user to read
  def stringBuilder(article: ArticleStore.Article): Unit  ={

    var articleList: List[String] = Nil
    val newAb = PrintFrontPage.sliceHelper(article.ab)
    val strId = "Id: "+article.id+"\n"
    val strTitle = "Title: "+article.title+"\n"
    val strAuthor = "Author: "+article.author+"\n"
    val strPub = "Published: "+article.pub+"\n"
    val strUp = "Updated: "+article.up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"
    val newArt = ArticleStore.Article(strId,strTitle,strAuthor,strPub,strUp,strAb)
    DatabaseStore.storeArticle(newArt)
    JsonFormatting.createList(newArt)
    ListStore.articleString = article.ab
    articleList = articleList :+ "articles: ["+"\n"
    articleList = articleList :+ newArt.toString
    articleList = articleList :+ "]"

  }

}
