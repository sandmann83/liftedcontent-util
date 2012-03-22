package eu.sbradl.liftedcontent.util.snippet

import net.liftweb.http.S

class Tweet {

  def render = {
    val lang = S.locale.getLanguage
    val size = S.attr("size").openOr("small")
    
    <a href="https://twitter.com/share" class="twitter-share-button" data-size={size} data-lang={lang}>Tweet</a>
	<tail><script>
			!function(d, s, id) {{
			var js, fjs = d.getElementsByTagName(s)[0];
			if (!d.getElementById(id)) {{
				js = d.createElement(s);
				js.id = id;
				js.src = "//platform.twitter.com/widgets.js";
				fjs.parentNode.insertBefore(js, fjs);
			}}
		}}(document, "script", "twitter-wjs");
	</script></tail>
  }
}