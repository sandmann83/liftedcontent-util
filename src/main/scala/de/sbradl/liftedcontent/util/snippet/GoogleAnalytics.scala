package de.sbradl.liftedcontent.util.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.S
import net.liftweb.common.Full
import net.liftweb.common.Empty
import net.liftweb.util.Props

class GoogleAnalytics {

  def render = {
    val minimumRunMode = Props.RunModes.withName(S.attr("mode").openOr("Production"))

    "*" #> {
      Props.mode.id >= minimumRunMode.id match {
        case true => S.attr("account") match {
          case Full(account) => Full(scriptTag(account))
          case _ => Empty
        }
        case false => Empty
      }

    }
  }

  def scriptTag(account: String) = <script type="text/javascript">
                                     var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', '{account}' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {{
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	}})();
                                   </script>
}