
package com.banno.finalProj


object DictionaryPath {

  def dictPrint(): List[Any] = {
    val s = System.currentTimeMillis.toDouble

    var wordList: List[Any] = Nil
    wordList = wordList :+ "Dictionary: ["+"\n"

    var slicedList: List[Any] = Nil

    var wordDict = ListStore.dictList.mkString.split(" ").toList
    wordDict = wordDict.map(_.toLowerCase())
    wordDict = wordDict.filterNot(_ == "reading&hellip;")
    dictHelper(0)
    def dictHelper(intCount: Int): List[Any] = {
      if (intCount > wordDict.length - 1) {
        return slicedList
      }
      val newChar = wordDict(intCount)
      val endChar = newChar.slice(newChar.length - 1,newChar.length)
      val endStr = newChar.slice(0,newChar.length - 1)
      val frontChar = newChar.slice(0,1)
      val frontStr = newChar.slice(1,newChar.length - 1)

      if (endChar == "," || endChar == "." || endChar == "th"){
        slicedList = slicedList :+ endStr
      }
      if (frontChar == ")" || frontChar == "(" || frontChar == "#")
        slicedList = slicedList :+ frontStr
      dictHelper(intCount + 1)
    }
    val fileDict = scala.io.Source.fromFile("src/main/resources/words2.txt").mkString
    val newArray = fileDict.split("\n").toList

    val newDict = slicedList.map{s =>
      if (newArray contains s){
      }
      else{
        if (wordList contains s+","+"\n"){

        }
        else {
          wordList = wordList :+s+","+"\n"
        }
      }

    }
    wordList = wordList :+ "]"
    println(wordList)
    println((System.currentTimeMillis - s) / 1000+" Seconds")

    return wordList
  }
}
