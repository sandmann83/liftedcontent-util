package eu.sbradl.liftedcontent.util.snippet

import net.liftweb.http.S
import net.liftweb.util.Helpers._

class LanguageAttribute {

  def render = {
    "data-lift-id=lang [lang]" #> S.attr("lang").openOr(S.locale.getLanguage)
  }
  
}