

package com.banno.finalProj
import spray.json._
import DefaultJsonProtocol._

object JsonFormatting {


  def dictJsonFormatting(newDict: DictionaryPath.DictWords): Unit = {
    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val format = jsonFormat1(DictionaryPath.DictWords)
    }
    import MyJsonProtocol._
    val toJson = DictionaryPath.DictWords(newDict.word).toJson
    val dict = toJson.convertTo[DictionaryPath.DictWords]
    println("  "+dict.word)


  }
  def createList(newArt: ArticleStore.Article): Unit = {
    ListStore.totalArticleList = ListStore.totalArticleList :+ newArt
    jsonFormatting(newArt)
  }

  def jsonFormatting(newArt: ArticleStore.Article): ArticleStore.Article = {

    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val format = jsonFormat6(ArticleStore.Article)

    }

    import MyJsonProtocol._
    val toJson = ArticleStore.Article(newArt.id,newArt.title,newArt.author,newArt.pub,newArt.up,newArt.ab).toJson
    val article = toJson.convertTo[ArticleStore.Article]
    println("Aritlce: [")
    println(article.id)
    println(article.title)
    println(article.author)
    println(article.pub)
    println(article.up)
    println(article.ab)
    println("]")
    newArt

  }



}
