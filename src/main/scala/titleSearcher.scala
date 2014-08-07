
package com.banno.finalProj

object TitleSearch {

   def titleSearcher(searchString: String,otherList: List[String],searchCount : Int): List[String] = {
    val articleList = ListStore.getList()
    var titleList = otherList
    if (searchCount >= articleList.length - 2) {

      return titleList
    }
    else if (articleList(searchCount).toString.toLowerCase contains " "+searchString.toLowerCase){
      titleList = otherList :+ articleList(searchCount).trim
      println("These are the articles I found with your search criteria"+" "+searchString+" "+"on the titles")
      println(articleList(searchCount - 1))
      println(articleList(searchCount))
      println(articleList(searchCount + 1))
      println(articleList(searchCount + 2))
      println(articleList(searchCount + 3))
      println(articleList(searchCount + 4))
      println("______________________________________________________________________")
    }
    titleSearcher(searchString,titleList,searchCount + 8)


  }
}
