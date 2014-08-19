
package com.banno.finalProj

object ArticleSearch {

def articleSearcher(searchString: String,otherList: List[String],searchCount : Int): List[String] = {
    val articleList = ListStore.getList()
    val newArtcle = ListStore.getArticle()
    var userArticleList = otherList
    val frontSlice = articleList(searchCount).toString.slice(8,articleList(searchCount).length)
    if (searchCount >= articleList.length - 7) return userArticleList

    else if (frontSlice.toString.trim == searchString.toLowerCase.trim) {
      userArticleList = otherList :+ articleList(searchCount + 5)
      println("These are the articles I found with your search"+" "+searchString+" "+"criteria on the article id")
      println(articleList(searchCount))
      println(articleList(searchCount + 1))
      println(articleList(searchCount + 2))
      println(articleList(searchCount + 3))
      println(articleList(searchCount + 4))
      println(articleList(searchCount + 5))
      println("______________________________________________________________________")

    }
    articleSearcher(searchString,userArticleList,searchCount + 8)


}
}
