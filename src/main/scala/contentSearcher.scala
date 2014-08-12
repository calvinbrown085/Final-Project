package com.banno.finalProj



object ContentSearch {


  def contentSearcher(searchString: String,returnList: List[String],searchCount : Int): List[String] = {
    val articleList = ListStore.getList()
    var totalContentList = articleList
    if (searchCount >= articleList.length - 2) {
      return totalContentList
    }
    else if (articleList(searchCount).toString.toLowerCase contains " "+searchString.toLowerCase){
      totalContentList = returnList :+ articleList(searchCount).trim
      println("These are the articles I found with your search criteria"+" "+searchString+" "+"on the content")
      println(articleList(searchCount - 5))
      println(articleList(searchCount - 4))
      println(articleList(searchCount - 3))
      println(articleList(searchCount -2))
      println(articleList(searchCount - 1))
      println(articleList(searchCount))
      println("______________________________________________________________________________")

    }
    contentSearcher(searchString,totalContentList,searchCount + 8)
  }
}
