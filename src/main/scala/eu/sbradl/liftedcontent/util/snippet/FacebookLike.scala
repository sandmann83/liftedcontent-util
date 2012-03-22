package eu.sbradl.liftedcontent.util.snippet

class FacebookLike {

  def render = {
    <div id="fb-root"></div>
    <script>
      (function(d, s, id) {{
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/de_DE/all.js#xfbml=1";
	  fjs.parentNode.insertBefore(js, fjs);
	}}(document, 'script', 'facebook-jssdk'));
    </script>
    <div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
  }
}