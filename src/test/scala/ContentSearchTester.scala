
package com.banno.finalProj

import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object ContentPathTester extends Specification with Server {
  val newList: List[String] = Nil
  val contentS = ContentSearch.contentSearcher("Hello",newList,6)

  "if we give content path a search string" should {
    "give us an empty list since we havent sent anything into it yet" in {
      contentS == Nil
    }
  }
}
