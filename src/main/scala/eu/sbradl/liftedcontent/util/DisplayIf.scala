package eu.sbradl.liftedcontent.util

import net.liftweb.util.PassThru
import net.liftweb.util.ClearNodes
import scala.xml.NodeSeq

object DisplayIf {

  def apply(conditionFullfilled: () => Boolean): NodeSeq => NodeSeq = apply(conditionFullfilled())
  def apply(conditionFullfilled: Boolean): NodeSeq => NodeSeq = (if (conditionFullfilled) PassThru else ClearNodes)

}