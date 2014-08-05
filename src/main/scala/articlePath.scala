
package com.banno.finalProj

object ArticleSearch {

def articleSearcher(searchString: String,otherList: List[String],intCount : Int): List[String] = {
    val newList = ListStore.getList()
    val newArt = ListStore.getArticle()
    var thatList = otherList
    val frontSlice = newList(intCount).toString.slice(8,newList(intCount).length)
    println(frontSlice)
    println(searchString)
    if (intCount >= newList.length - 7) {
      return otherList
    }
    else if (frontSlice.toString.trim == searchString.toLowerCase.trim){
      thatList = otherList :+ newList(intCount + 5)
      println("These are the articles I found with your search"+" "+searchString+" "+"criteria on the article id")
      println(newList(intCount))
      println(newList(intCount + 1))
      println(newList(intCount + 2))
      println(newList(intCount + 3))
      println(newList(intCount + 4))
      println(newList(intCount + 5))
      println("______________________________________________________________________")

    }
    articleSearcher(searchString,thatList,intCount + 8)


  }
}
