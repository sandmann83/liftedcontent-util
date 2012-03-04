package eu.sbradl.liftedcontent.util

import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds

/**
 * This object is a simple helper for creating confirm boxes.
 */
object OnConfirm {

  /**
   * Creates a new ajax-function which can be bound to an html event attribute.
   * 
   * @param message The message to display in the confirm box.
   * @param func The function to execute when the user confirms.
   */
  def apply(message: String, func: () => JsCmd) = {
    SHtml.onEvent(s => JsCmds.Confirm(message, SHtml.ajaxInvoke(func)._2.cmd))
  }
  
}