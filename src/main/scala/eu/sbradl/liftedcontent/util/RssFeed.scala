package eu.sbradl.liftedcontent.util

object RssFeed {
  def apply[T](title: String, link: String, desc: String, language: String, author: String, items: Seq[T],
      titleOf: (T) => String, descriptionOf: (T) => String, linkTo: (T) => String,
      authorOf: (T) => String, pubDateOf: (T) => String) = {
	  <rss version="2.0">
	  	<channel>
	  		<title>{title}</title>
	  		<link>{link}</link>
	  		<description>{desc}</description>
	  		<language>{language}</language>
	  		<copyright>{author}</copyright>
	  		
	  		{
	  		  items map {
	  		    item => {
	  		      <item>
	  		    	<title>{titleOf(item)}</title>
	  		    	<description>{descriptionOf(item)}</description>
	  		    	<link>{linkTo(item)}</link>
	  		    	<author>{authorOf(item)}</author>
	  		    	<pubDate>{pubDateOf(item)}</pubDate>
	  		    </item>
	  		    }
	  		  }
	  		}
	  	</channel>
	  </rss>
  }
}