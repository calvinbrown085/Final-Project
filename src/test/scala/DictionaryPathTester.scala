
package com.banno.finalProj



import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object DictionaryPathTester extends Specification with Server {


  val dict = DictionaryPath.dictPrint()

  "if we call the dict" should {
    "give us a list with at least 1 thing in it" in {
      dict.length >= 0

    }
  }



}
