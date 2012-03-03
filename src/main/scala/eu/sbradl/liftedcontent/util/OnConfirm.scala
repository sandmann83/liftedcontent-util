package eu.sbradl.liftedcontent.util

import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds

object OnConfirm {

  def apply(message: String, func: () => JsCmd) = {
    SHtml.onEvent(s => JsCmds.Confirm(message, SHtml.ajaxInvoke(func)._2.cmd))
  }
  
}