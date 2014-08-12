
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
object ListBuilder {










  //this builds the readable string for the user to read
  def stringBuilder(id: String,title: String,author: String,pub: String,up:String,ab:String): Unit  ={

    var articleList: List[String] = Nil
    val newAb = PrintFrontPage.sliceHelper(ab)
    val strId = "Id: "+id+"\n"
    val strTitle = "Title: "+title+"\n"
    val strAuthor = "Author: "+author+"\n"
    val strPub = "Published: "+pub+"\n"
    val strUp = "Updated: "+up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"
    val newArt = ArticleStore.Article(strId,strTitle,strAuthor,strPub,strUp,strAb)
    ArticleStore.storeArticle(newArt)
    JsonFormatting.createList(newArt)
    ListStore.articleString = ab
    articleList = articleList :+ "articles: ["+"\n"
    articleList = articleList :+"    "+ strId
    articleList = articleList :+"    "+ strTitle
    articleList = articleList :+"    "+ strAuthor
    articleList = articleList :+"    "+ strPub
    articleList = articleList :+"    "+ strUp
    articleList = articleList :+"    "+ strAb
    articleList = articleList :+ "]"

  }

}
