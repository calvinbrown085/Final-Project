package com.banno.finalProj



object ContentSearch {


  def contentSearcher(searchString: String,otherList: List[ArticleStore.Article]): List[ArticleStore.Article] = {
    val articleList = ListStore.getList()
    var totalContentList = articleList
    articleList.map{article =>
      if (article.ab.toLowerCase contains " "+searchString.toLowerCase){
        totalContentList = otherList :+ article
        println(article)


      }

    }
    totalContentList
  }
}
