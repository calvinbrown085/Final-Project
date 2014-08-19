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
    def strSliceHelper(intCount: Int): String = intCount match {
      case intCount if(contentList(intCount).toString == "." && intCount > 50) => {
        contentList = contentList.slice(0,intCount)
        contentStr = contentList.mkString
        return contentStr
      }
      case _  =>  strSliceHelper(intCount + 1)
    }
    contentStr
  }



  //this builds the readable string for the user to read
  def stringBuilder(article: ArticleStore.Article): Unit  ={

    var articleList: List[String] = Nil
    val newAb = sliceHelper(article.ab)
    val strId = "Id: "+article.id+"\n"
    val strTitle = "Title: "+article.title+"\n"
    val strAuthor = "Author: "+article.author+"\n"
    val strPub = "Published: "+article.pub+"\n"
    val strUp = "Updated: "+article.up+"\n"
    val strAb = "Abstract: "+newAb+"...."+"\n"
    val newArt = ArticleStore.Article(strId,strTitle,strAuthor,strPub,strUp,strAb)
    ListStore.dictionaryList = ListStore.dictionaryList:+ newArt.toString
    articleList = newArt.toString +: articleList
    articleList = articleList.reverse
    JsonFormatting.createList(newArt)
    listStore(articleList,strTitle)


  }
}
