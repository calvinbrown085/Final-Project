
package com.banno.finalProj

object ArticleSearch {

def articleSearcher(searchString: String,otherList: List[ArticleStore.Article]): List[ArticleStore.Article] = {
    val articleList = ListStore.getList()
    var userArticleList = otherList

    articleList.map {article =>
      val newString = "id: "+searchString
      if (article.id.toLowerCase.trim == newString.toLowerCase.trim) {
        userArticleList = otherList :+ article
        println(article)
      }
    }
  userArticleList
}
}
