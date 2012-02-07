package de.sbradl.liftedcontent.util

import scala.xml.NodeSeq

object Count {

  def apply(term: String) = new Count(term)
  
}

class Count(term: String) {
  
  def in(nodes: NodeSeq): Int = in(nodes.text)
  def in(text: String): Int = (text + " ").split(term).size - 1
  
}