
package com.banno.finalProj

object TitleSearch {

   def titleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    var thatList = otherList
    if (intCount >= newList.length - 2) {

      return otherList
    }
    else if (newList(intCount).toString.toLowerCase contains " "+searchString.toLowerCase){
      thatList = otherList :+ newList(intCount).trim
      println("These are the articles I found with your search criteria"+" "+searchString+" "+"on the titles")
      println(newList(intCount - 1))
      println(newList(intCount))
      println(newList(intCount + 1))
      println(newList(intCount + 2))
      println(newList(intCount + 3))
      println(newList(intCount + 4))
      println("______________________________________________________________________")
    }
    titleSearcher(searchString,thatList,intCount + 8)


  }
}
