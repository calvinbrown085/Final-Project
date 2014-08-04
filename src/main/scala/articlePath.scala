
package com.banno.finalProj

object ArticleSearch {

def articleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    val newArt = ListStore.getArticle()
    var thatList = otherList
    val frontSlice = newList(intCount).toString.slice(8,newList(intCount).length)
    if (intCount >= newList.length - 7) {
      return otherList
    }
    else if (frontSlice.toString.trim == searchString.toLowerCase.trim){
      thatList = otherList :+ newList(intCount + 5)


    }
    articleSearcher(searchString,thatList,intCount + 8)


  }
}
