

package com.banno.finalProj


import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object TitlePathTester extends Specification with Server {
  val newList: List[String] = Nil
  val titleS = TitleSearch.titleSearcher("Hello",newList, 2)

  "if we give title path a search string" should {
    "give us an empty list since we havnet sent anything into it yet" in {
      titleS == Nil
    }
  }


}
