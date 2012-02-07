package de.sbradl.liftedcontent.util

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import scala.xml.Text
import scala.xml.Unparsed

object Highlight {

  def apply(term: String) = new Highlight(term)
  
}

class Highlight(term: String) {
  
  def in(nodes: NodeSeq): NodeSeq = in(nodes.text)
  def in(text: String): NodeSeq = Unparsed(text.replace(term, "<mark>" + term + "</mark>"))
  
}