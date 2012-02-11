package de.sbradl.liftedcontent.util

import scala.xml.NodeSeq

object Count {

  def apply(term: String, caseSensitive: Boolean = true) = new Count(term, caseSensitive)

}

class Count(term: String, caseSensitive: Boolean) {

  def in(nodes: NodeSeq): Int = in(nodes.text)
  def in(text: String): Int = caseSensitive match {
    case true => (text + " ").split(term).size - 1
    case false => {
      val lowerCaseText = text.toLowerCase
      val lowerCaseTerm = term.toLowerCase

      (lowerCaseText + " ").split(lowerCaseTerm).size - 1
    }
  }

}