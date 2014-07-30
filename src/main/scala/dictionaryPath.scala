
package com.banno.finalProj
//if the file is empty return none instead of blowing up the program
import spray.json._
import DefaultJsonProtocol._


object DictionaryPath {
  private var totalDictList: List[DictWords] = Nil
  case class DictWords(word: String)
  def dictPrint(): Option[List[Any]] = {
    if (ListStore.completeArticleList == Nil){
      return None
    }
    val s = System.currentTimeMillis.toDouble
    println("Dictionary: [")
    var wordList: List[Any] = Nil

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
          val newDict = DictWords(s.toString)
          createDictList(newDict)
          wordList = wordList :+s+","+"\n"
        }
      }

    }
    println((System.currentTimeMillis - s) / 1000+" Seconds")
    println("]")
    return(Some(wordList))
  }


  def createDictList(newDict: DictWords): List[DictWords] = {
    totalDictList =  totalDictList :+ newDict
    dictJsonFormatting(newDict)
    totalDictList

  }

  def dictJsonFormatting(newDict: DictWords): Unit = {
    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val format = jsonFormat1(DictWords)
    }
    import MyJsonProtocol._
    val toJson = DictWords(newDict.word).toJson
    val dict = toJson.convertTo[DictWords]
    println("  "+dict.word)


  }

}
