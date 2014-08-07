package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._
// turn the storage into a case class to store the objects
// only return json articles not anything else
// cut down the number of lists and make it only like 1 or 2 and then decide if you still need list store or not
object PrintFrontPage {

  def xmlSlice(searchCount: Int): Unit = {

    if (searchCount > 9){
      return
    }
    else {

      val newReg = """<(.|\n)*?>""".r
      val newXml = xml.XML.load("http://www.theverge.com/rss/frontpage.xml")

      val newRawId = newXml\"entry"\"id"
      val newId = newRawId(searchCount)
      //this gets the id

      val newRawTitle = newXml\\"entry"\\"title"
      val newTitle = newRawTitle(searchCount)
      //This gets the title
      val newRawAuthor = newXml\\"author"\\"name"
      val newEAuthor = newRawAuthor(searchCount)

      //this gets the author
      val newRawPub = newXml\\"entry"\"published"
      val newPub = newRawPub(searchCount)
      //this gets the published date
      val newRawUp = newXml\\"entry"\"updated"
      val newUp = newRawUp(searchCount)
      //this is got get the updated date
      val newRawContent = newXml\"entry"\"content"
      val newContent = newRawContent(searchCount)
      val matchFind = newReg.replaceAllIn(newContent.text,"")
      //this is to get the content


      stringBuilder(newId.text,newTitle.text,newEAuthor.text,newPub.text,newUp.text,matchFind.trim)

      xmlSlice(searchCount + 1)
    }
  }
  def listStore(articleList: List[String],articleTitle: String): Option[List[String]] = {
    val completeArticleList: List[String] = ListStore.completeArticleList.flatten
    if (ListStore.completeArticleList == Nil){
      ListStore.completeArticleList = ListStore.completeArticleList :+ articleList

      return Some(completeArticleList)
    }

    else if(articleList(2).trim == articleTitle.trim){

      ListStore.completeArticleList = ListStore.completeArticleList :+ articleList
    }

    Some(completeArticleList)
  }




  // this slices the abstract to make it more readable for the user
  def sliceHelper(content: String): String = {
    var contentList = content.toList
    var contentStr = ""
    strSliceHelper(0)
    def strSliceHelper(intCount: Int): String = {
      if(contentList(intCount).toString == "." && intCount > 50){
        contentList = contentList.slice(0,intCount)
        contentStr = contentList.mkString
        return contentStr
      }
      strSliceHelper(intCount + 1)
    }
    contentStr
  }



  //this builds the readable string for the user to read
  def stringBuilder(id: String,title: String,author: String,pub: String,up:String,ab:String): Unit  ={

    var articleList: List[String] = Nil
    val newAb = sliceHelper(ab)
    val strId = "id: "+id+"\n"
    val strTitle = "title: "+title+"\n"
    val strAuthor = "Author: "+author+"\n"
    val strPub = "Published: "+pub+"\n"
    val strUp = "Updated: "+up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"

    articleList = articleList :+ "articles: ["+"\n"
    articleList = articleList :+"    "+ strId
    articleList = articleList :+"    "+ strTitle
    articleList = articleList :+"    "+ strAuthor
    articleList = articleList :+"    "+ strPub
    articleList = articleList :+"    "+ strUp
    articleList = articleList :+"    "+ strAb
    articleList = articleList :+ "]"
    listStore(articleList,strTitle)
    println(articleList)

  }
}
