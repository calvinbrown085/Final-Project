
package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._

object StringSlice {

  def xmlSlice(intCount: Int): Unit = {

    if (intCount > 8){
      return
    }
    else {

      val newReg = """<(.|\n)*?>""".r
      val newXml = xml.XML.load("http://www.theverge.com/rss/frontpage.xml")

      val newRawId = newXml\"entry"\"id"
      val newId = newRawId(intCount)
      //this gets the id

      val newRawTitle = newXml\\"entry"\\"title"
      val newTitle = newRawTitle(intCount)
      //This gets the title
      val newRawAuthor = newXml\\"author"\\"name"
      val newEAuthor = newRawAuthor(intCount)

      //this gets the author
      val newRawPub = newXml\\"entry"\"published"
      val newPub = newRawPub(intCount)
      //this gets the published date
      val newRawUp = newXml\\"entry"\"updated"
      val newUp = newRawUp(intCount)
      //this is got get the updated date
      val newRawContent = newXml\"entry"\"content"
      val newContent = newRawContent(intCount)
      val matchFind = newReg.replaceAllIn(newContent.text,"")
      //this is to get the content


      stringBuilder(newId.text,newTitle.text,newEAuthor.text,newPub.text,newUp.text,matchFind.trim)
      xmlSlice(intCount + 1)
    }
  }
  //This will store the complete article list in a list to print out to users and be used else where
  def listStore(newList: List[String],title: String): Option[List[String]] = {
    val anotherList: List[String] = ListStore.completeArticleList.flatten
    if (ListStore.completeArticleList == Nil){
      ListStore.completeArticleList = ListStore.completeArticleList :+ newList

      return Some(anotherList)
    }

    else if(newList(2).trim == title.trim){

      ListStore.completeArticleList = ListStore.completeArticleList :+ newList
    }

    Some(anotherList)
  }



  // this slices the abstract to make it more readable for the user
  def sliceHelper(ab: String): String = {
    var newList = ab.toList
    var newStr = ""
    strSliceHelper(0)
    def strSliceHelper(intCount: Int): String = {
      if(newList(intCount).toString == "." && intCount > 50){
        newList = newList.slice(0,intCount)
        newStr = newList.mkString
        return newStr
      }
      strSliceHelper(intCount + 1)
    }
    newStr
  }



  //this builds the readable string for the user to read
  def stringBuilder(id: String,title: String,author: String,pub: String,up:String,ab:String): List[String]  ={

    var articleList: List[String] = Nil

    val newAb = sliceHelper(ab)
    val strId = "id: "+id+"\n"
    val strTitle = "title: "+title+"\n"
    val strAuthor = "Author: "+author+"\n"
    val strPub = "Published: "+pub+"\n"
    val strUp = "Updated: "+up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"
    jsonFormatting(strId,strTitle,strAuthor,strPub,strUp,strAb)
    ListStore.dictList = ListStore.dictList :+id
    ListStore.dictList = ListStore.dictList :+title
    ListStore.dictList = ListStore.dictList :+author
    ListStore.dictList = ListStore.dictList :+pub
    ListStore.dictList = ListStore.dictList :+up
    ListStore.dictList = ListStore.dictList :+ ab
    ListStore.articleStr = ab
    articleList = articleList :+ "articles: ["+"\n"
    articleList = articleList :+"    "+ strId
    articleList = articleList :+"    "+ strTitle
    articleList = articleList :+"    "+ strAuthor
    articleList = articleList :+"    "+ strPub
    articleList = articleList :+"    "+ strUp
    articleList = articleList :+"    "+ strAb
    articleList = articleList :+ "]"
    listStore(articleList,strTitle)
    articleList

  }

  def jsonFormatting(id: String,title: String,author: String,pub: String,up:String,ab:String): Unit = {
    case class ArticleInfo(id: String,title:String, author: String, pub: String, up: String,  ab:String)
    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val colorFormat = jsonFormat6(ArticleInfo)
    }
    import MyJsonProtocol._
    //val json = ArticleInfo(StringSlice.newId.text,StringSlice.newTitle,StringSlice.newAuthor,StringSlice.newPub,StringSlice.newUp,StringSlice.matchFind).toJson
    val json = ArticleInfo(id,title,author,pub,up,ab).toJson
    val color = json.convertTo[ArticleInfo]
    println("Aritlces: [")
    println(color.id)
    println(color.title)
    println(color.author)
    println(color.pub)
    println(color.up)
    println(color.ab)
    println("]")

  }
}
