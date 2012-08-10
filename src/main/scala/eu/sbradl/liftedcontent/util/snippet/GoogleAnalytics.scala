package eu.sbradl.liftedcontent.util.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.S
import net.liftweb.common.Full
import net.liftweb.common.Empty
import net.liftweb.util.Props
import net.liftweb.common.Box
import scala.xml.Text
import scala.xml.NodeSeq

/**
 * Insert the google analytics tracking code into your page.
 * 
 * The snippet can be used with two attributes, "mode" and "id". The id specifies
 * your google tracking id. Alternatively you can set it in your properties with the
 * key "google.analytics.id". 
 * The mode attribute can be used to turn off tracking.
 * By default it is set to "Production", meaning that the tracking code is only
 * rendered when in production mode.
 */
class GoogleAnalytics {

  def render = {
    val minimumRunMode = Props.RunModes.withName(S.attr("mode").openOr("Production"))

    val id = trackingID
    val anonymizeIp = S.attr("anonymize").map(_.toBoolean).openOr(false)

    "*" #> {
      Props.mode.id >= minimumRunMode.id match {
        case true => id match {
          case Full(trackingID) => Full(scriptTag(trackingID, anonymizeIp))
          case _ => {
            if (Props.mode.id <= Props.RunModes.Test.id) error
            else Empty
          }
        }
        case false => Empty
      }

    }
  }

  private def trackingID: Box[String] = S.attr("id") match {
    case id @ Full(_) => id
    case _ => Props.get("google.analytics.id")
  }

  private def scriptTag(trackingID: String, anonymize: Boolean) = 
    <script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', '{ trackingID }' ]);
	_gaq.push([ '_trackPageview' ]);
  	{if(anonymize) "_gaq.push([ '_gat._anonymizeIp' ]);"}

	(function() {{
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	}})();</script>

  private def error = errorDiv(<div>
                                 No Tracking ID specified for the Google Analytics Snippet<br/>
                                 Either specifiy the id attribute on snippet invocation 
		  						 (e.g <b>GoogleAnalytics?id=my-tracking-id</b>) or set
                                 <b>google.analytics.id</b>
                                 in the properties
                               </div>)

}