package com.banno.finalProj



object ContentSearch {


  def contentSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    var thatList = otherList
    if (intCount >= newList.length - 2) {
      return otherList
    }
    else if (newList(intCount).toString.toLowerCase contains " "+searchString.toLowerCase){
      thatList = otherList :+ newList(intCount).trim
      println("These are the articles I found with your search criteria"+" "+searchString+" "+"on the content")
      println(newList(intCount - 5))
      println(newList(intCount - 4))
      println(newList(intCount - 3))
      println(newList(intCount -2))
      println(newList(intCount - 1))
      println(newList(intCount))
      println("______________________________________________________________________________")

    }
    contentSearcher(searchString,thatList,intCount + 8)


  }
}
