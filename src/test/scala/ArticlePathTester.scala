

package com.banno.finalProj
import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object ArticlePathTester extends Specification with Server {
  val newList: List[String] = Nil
  val articleS = ArticleSearch.articleSearcher("Hello",newList,1)
  "if we give article path a search string" should {
    "give us an empty list since we havent sent anything into it yet" in {
      articleS == Nil
    }
  }
}
