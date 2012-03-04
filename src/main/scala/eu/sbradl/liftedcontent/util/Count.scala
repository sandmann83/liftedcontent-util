package eu.sbradl.liftedcontent.util

import scala.xml.NodeSeq

object Count {

  /**
   * Creates a new counter object.
   * 
   * @param term The text to count.
   * @param caseSensitive Flag indicating if counting should be doen case sensitive or not.
   */
  def apply(term: String, caseSensitive: Boolean = true) = new Count(term, caseSensitive)

}

class Count(term: String, caseSensitive: Boolean) {

  /**
   * Returns the number of matches.
   * 
   * @param nodes The nodes in whose text to count the term.
   */
  def in(nodes: NodeSeq): Int = in(nodes.text)
  
  /**
   * Returns the number of matches.
   * 
   * @param text The text in which to count the term.
   */
  def in(text: String): Int = caseSensitive match {
    case true => (text + " ").split(term).size - 1
    case false => {
      val lowerCaseText = text.toLowerCase
      val lowerCaseTerm = term.toLowerCase

      (lowerCaseText + " ").split(lowerCaseTerm).size - 1
    }
  }

}