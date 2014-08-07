
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
object ListBuilder {

  def xmlParser(searchCount: Int): Unit = {

    if (searchCount > 9){
      return
    }
    else {

      val newReg = """<(.|\n)*?>""".r
      val articleXml = xml.XML.load("http://www.theverge.com/rss/frontpage.xml")

      val newRawId = articleXml\"entry"\"id"
      val newId = newRawId(searchCount)
      //this gets the id

      val newRawTitle = articleXml\\"entry"\\"title"
      val newTitle = newRawTitle(searchCount)
      //This gets the title
      val newRawAuthor = articleXml\\"author"\\"name"
      val newEAuthor = newRawAuthor(searchCount)

      //this gets the author
      val newRawPub = articleXml\\"entry"\"published"
      val newPub = newRawPub(searchCount)
      //this gets the published date
      val newRawUp = articleXml\\"entry"\"updated"
      val newUp = newRawUp(searchCount)
      //this is got get the updated date
      val newRawContent = articleXml\"entry"\"content"
      val newContent = newRawContent(searchCount)
      val matchFind = newReg.replaceAllIn(newContent.text,"")
      //this is to get the content


      stringBuilder(newId.text,newTitle.text,newEAuthor.text,newPub.text,newUp.text,matchFind.trim)

      xmlParser(searchCount + 1)
    }
  }








  //this builds the readable string for the user to read
  def stringBuilder(id: String,title: String,author: String,pub: String,up:String,ab:String): Unit  ={

    var articleList: List[String] = Nil
    val newAb = PrintFrontPage.sliceHelper(ab)
    val strId = "id: "+id+"\n"
    val strTitle = "title: "+title+"\n"
    val strAuthor = "Author: "+author+"\n"
    val strPub = "Published: "+pub+"\n"
    val strUp = "Updated: "+up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"
    val newArt = ArticleStore.Article(strId,strTitle,strAuthor,strPub,strUp,strAb)
    ArticleStore.storeArticle(newArt)
    JsonFormatting.createList(newArt)
    ListStore.dictionaryList = ListStore.dictionaryList :+id
    ListStore.dictionaryList = ListStore.dictionaryList :+title
    ListStore.dictionaryList = ListStore.dictionaryList :+author
    ListStore.dictionaryList = ListStore.dictionaryList :+pub
    ListStore.dictionaryList = ListStore.dictionaryList :+up
    ListStore.dictionaryList = ListStore.dictionaryList:+ ab
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
