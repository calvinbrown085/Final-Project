

package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._

object XmlParser {


  def xmlParser(searchCount: Int, bool: Boolean): Unit = {

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
      val newArt = ArticleStore.Article(newId.text,newTitle.text,newEAuthor.text,newPub.text,newUp.text,matchFind.trim)
      if (bool == false){
        ListBuilder.stringBuilder(newArt)
      }
      else {
        PrintFrontPage.stringBuilder(newArt)
      }

      xmlParser(searchCount + 1,bool)
    }
  }

}
