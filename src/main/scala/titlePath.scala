
package com.banno.finalProj

object TitleSearch {

   def titleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()

    var thatList = otherList
    val newSearch = searchString.map(_.toLower)
    if (intCount >= newList.length - 2) {
      println(otherList)
      return otherList
    }
    else if (newList(intCount).toString.toLowerCase contains newSearch.toString){
      thatList = otherList :+ newList(intCount).trim

    }
    titleSearcher(searchString,thatList,intCount + 8)


  }
}
