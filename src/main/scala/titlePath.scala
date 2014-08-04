
package com.banno.finalProj

object TitleSearch {

   def titleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    var thatList = otherList
    if (intCount >= newList.length - 2) {
      println(otherList)
      return otherList
    }
    else if (newList(intCount).toString.toLowerCase contains " "+searchString.toLowerCase){
      thatList = otherList :+ newList(intCount).trim
      println("Id: "+newList(intCount - 1))
      println("Title: "+newList(intCount))
      println("Author: "+newList(intCount + 1))
      println("Published: "+newList(intCount + 2))
      println("Updated: "+newList(intCount + 3))
      println("Content: "+newList(intCount + 4))
      println("______________________________________________________________________")
    }
    titleSearcher(searchString,thatList,intCount + 8)


  }
}
