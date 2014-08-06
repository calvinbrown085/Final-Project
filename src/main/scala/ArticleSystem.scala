


package com.banno.finalProj

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.PostgreSqlAdapter
import java.util.Calendar

object ArticleSystem extends Schema {

  val articles = table[ArticleStore.Article]("articlestorage")



}
