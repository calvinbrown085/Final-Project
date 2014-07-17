
package com.banno.finalProj

object ArticleSearch {

def articleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    val newArt = ListStore.getArticle()
    var thatList = otherList
    val newSearch = searchString.map(_.toLower)
    val frontSlice = newList(intCount).toString.slice(8,newList(intCount).length)
    if (intCount >= newList.length - 7) {
      return otherList
    }
    else if (frontSlice.toString.trim == newSearch.toString.trim){
      thatList = otherList :+ newArt.trim

    }
    articleSearcher(searchString,thatList,intCount + 8)


  }
}
