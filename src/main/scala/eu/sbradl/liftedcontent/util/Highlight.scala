package eu.sbradl.liftedcontent.util

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import scala.xml.Text
import scala.xml.Unparsed

object Highlight {

  /**
   * Creates a new highlighter.
   * 
   * @param term The text to highlight.
   * @param caseSensitive Flag indicating if highlighting should be done case sensitive.
   */
  def apply(term: String, caseSensitive: Boolean = true) = new Highlight(term, caseSensitive)

}

class Highlight(term: String, caseSensitive: Boolean) {

  /**
   * Highlights the term in the given node sequence using the HTML5 "mark"-Tag.
   * 
   * @param nodes The input in which the term should be highlighted.
   * @returns A node sequence with the occurrences of the term being highlighted.
   */
  def in(nodes: NodeSeq): NodeSeq = in(nodes.text)
  
  /**
   * Highlights the term in the given text using the HTML5 "mark"-Tag.
   * 
   * @param text The input in which the term should be highlighted.
   * @returns A node sequence with the occurrences of the term being highlighted.
   */
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