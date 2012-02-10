package de.sbradl.liftedcontent.util

import scala.xml.NodeSeq

object Count {

  def apply(term: String, caseInsensitive: Boolean = false) = new Count(term, caseInsensitive)
  
}

class Count(term: String, caseInsensitive: Boolean) {
  
  def in(nodes: NodeSeq): Int = in(nodes.text)
  def in(text: String): Int = caseInsensitive match {
    case true => {
      val lowerCaseText = text.toLowerCase
      val lowerCaseTerm = term.toLowerCase
      
      (lowerCaseText + " ").split(lowerCaseTerm).size - 1
    }
    case false => (text + " ").split(term).size - 1
  }
  
}