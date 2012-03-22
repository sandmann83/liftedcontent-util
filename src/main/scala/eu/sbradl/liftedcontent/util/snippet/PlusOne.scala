package eu.sbradl.liftedcontent.util.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.S

/**
 * This snippet renders a google +1 button. You can pass attributes to the snippet 
 * to control the look of the button.
 * 
 * Possible attributes are: size and annotation.
 */
class PlusOne {

  def render = {
    val size = S.attr("size").openOr("standard")
    val annotation = S.attr("annotation").openOr("info")
    
    <tail>
      <script type="text/javascript" src="https://apis.google.com/js/plusone.js">
        {{
    		lang: '{ S.locale.getLanguage }'
    	}}
      </script>
    </tail>
    <div class="g-plusone" data-size={size} data-annotation={annotation}></div>
  }

}