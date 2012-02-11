package de.sbradl.liftedcontent.util

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import scala.xml.Text
import scala.xml.Unparsed

object Highlight {

  def apply(term: String, caseSensitive: Boolean = true) = new Highlight(term, caseSensitive)

}

class Highlight(term: String, caseSensitive: Boolean) {

  def in(nodes: NodeSeq): NodeSeq = in(nodes.text)
  def in(text: String): NodeSeq = caseSensitive match {
    case true => Unparsed(text.replace(term, "<mark>" + term + "</mark>"))
    case false => {
      val lowercaseTerm = term toLowerCase
      val lowercaseText = text toLowerCase
      
      val indexes = findIndexes(lowercaseTerm, lowercaseText)
      val substrings = findSubstrings(indexes, term.size, text)
      
      var result = text
      substrings foreach {
        s => result = result.replace(s, "<mark>" + s + "</mark>")
      }

      Unparsed(result)
    }
  }

  private def findIndexes(term: String, text: String) = {
    var indexes = List[Int]()

    var currentIndex = 0;

    var finished = false
    while (!finished) {
      currentIndex = text.indexOf(term, currentIndex)

      if (currentIndex == -1) {
        finished = true
      } else {
        indexes ::= currentIndex
        currentIndex += term.size
      }
    }

    indexes
  }
  
  private def findSubstrings(indexes: List[Int], size: Int, text: String) = {
    indexes map (i => text.substring(i, i + size))
  }

}