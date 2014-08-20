
package com.banno.finalProj

object TitleSearch {

   def titleSearcher(searchString: String,otherList: List[ArticleStore.Article],searchCount : Int): List[ArticleStore.Article] = {
    val articleList = ListStore.getList()
    var titleList = otherList
     articleList.map{article =>
       if (article.title contains " "+searchString.toLowerCase){
         titleList = otherList :+ article
         println(article)
       }
     }
     titleList

  }
}
