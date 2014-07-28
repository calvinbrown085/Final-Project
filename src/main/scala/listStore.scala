
package com.banno.finalProj
//switch the if statments to pattern matching

object ListStore  {
  var completeArticleList: List[List[String]] = Nil
  var articleStr: String = ""
  var dictList: List[String] = Nil

  def getDictList(): List[String] = {

    dictList
  }
  def getArticle(): String = {
    articleStr
  }
  //this will get the list and print it out to the user
  def getList(): List[String] = {
    if (completeArticleList == Nil){

      Nil
    }
    else{
      val newList = completeArticleList.flatten
      newList
    }
  }

}
