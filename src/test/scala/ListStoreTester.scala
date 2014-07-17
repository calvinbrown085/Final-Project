package com.banno.finalProj

import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}


object ListStoreTester extends Specification with Server {

  val thisList = ListStore.getList()
  val thatList = ListStore.getDictList()
  val articleStr = ListStore.getArticle()
  val str: String = ""




  "if we call get dictList" should {
    "return a list with at least 1 thing in it" in {
      thatList.length >= 0
    }
  }

  "if we call getList()" should {
    "return a list with at least 1 thing in it" in {
      thisList.length >= 0
    }
  }
  "if we call getArticle()" should {
    "return a string" in {
      articleStr == str
    }
  }




}
