
package com.banno.finalProj
//switch the if statments to pattern matching

object ListStore  {
  var completeArticleList: List[ArticleStore.Article] = Nil
  var articleString: String = ""
  var dictionaryList: List[String] = Nil
  var totalArticleList: List[ArticleStore.Article] = Nil
  def getDictList(): List[String] = {

    dictionaryList
  }
  def getArticle(): String = {
    articleString
  }
  //this will get the list and print it out to the user
  def getList(): List[ArticleStore.Article] = {
    if (completeArticleList == Nil)  Nil
    else completeArticleList



  }


}
