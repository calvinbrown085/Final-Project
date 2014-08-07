


package com.banno.finalProj
import org.squeryl.Schema


object ArticleSchema extends Schema {

  val articles = table[ArticleStore.Article]("articlestorage")



}
