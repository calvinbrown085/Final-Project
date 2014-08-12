package com.banno.finalProj

object PrintFrontPage {


  def listStore(articleList: List[String],articleTitle: String): Option[List[String]] = {
    val completeArticleList: List[String] = ListStore.completeArticleList.flatten
    if (ListStore.completeArticleList == Nil){
      ListStore.completeArticleList = ListStore.completeArticleList :+ articleList

      return Some(completeArticleList)
    }

    else{

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
    val strId = "Id: "+id+"\n"
    val strTitle = "Title: "+title+"\n"
    val strAuthor = "Author: "+author+"\n"
    val strPub = "Published: "+pub+"\n"
    val strUp = "Updated: "+up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"

    ListStore.dictionaryList = ListStore.dictionaryList :+id
    ListStore.dictionaryList = ListStore.dictionaryList :+title
    ListStore.dictionaryList = ListStore.dictionaryList :+author
    ListStore.dictionaryList = ListStore.dictionaryList :+pub
    ListStore.dictionaryList = ListStore.dictionaryList :+up
    ListStore.dictionaryList = ListStore.dictionaryList:+ ab
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
