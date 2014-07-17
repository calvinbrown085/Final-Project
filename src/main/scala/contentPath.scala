package com.banno.finalProj



object ContentSearch {


  def contentSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    var thatList = otherList
    val newSearch = searchString.map(_.toLower)
    if (intCount >= newList.length - 2) {
      return otherList
    }
    else if (newList(intCount).toString contains newSearch.toString){
      thatList = otherList :+ newList(intCount).trim

    }
    contentSearcher(searchString,thatList,intCount + 8)


  }
}
