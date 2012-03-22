package eu.sbradl.liftedcontent.util.snippet

import net.liftweb.http.S

class FacebookLike {

  def render = {
    val send = S.attr("send").openOr("true")
    val layout = S.attr("layout").openOr("standard")
    val showFaces = S.attr("show-faces").openOr("true")
    val verb = S.attr("verb").openOr("like")
    val color = S.attr("color").openOr("light")

    <tail><div id="fb-root"></div>
    <script>
      (function(d, s, id) {{
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/de_DE/all.js#xfbml=1";
	  fjs.parentNode.insertBefore(js, fjs);
	}}(document, 'script', 'facebook-jssdk'));
    </script></tail>
    <div class="fb-like" 
    	data-send={ send } data-layout={ layout } data-show-faces={ showFaces }
    	data-action={verb} data-colorscheme={color}></div>
  }
}