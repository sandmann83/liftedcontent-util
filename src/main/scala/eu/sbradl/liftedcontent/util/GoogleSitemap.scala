package eu.sbradl.liftedcontent.util

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.XmlResponse
import net.liftweb.http.S
import net.liftweb.common.Full
import scala.xml.NodeSeq
import net.liftweb.common.Box
import net.liftweb.common.Empty
import java.util.Date
import net.liftweb.util.TimeHelpers._
import java.text.SimpleDateFormat
import org.joda.time.DateTime

object ChangeFrequency extends Enumeration { 
    val Always = Value(1, "always")
    val Hourly = Value(2, "hourly")
    val Daily = Value(3, "daily")
    val Weekly = Value(4, "Weekly")
    val Monthly = Value(5, "monthly")
    val Yearly = Value(6, "yearly")
    val Never = Value(7, "never")
} 

object GoogleSitemap extends RestHelper {

  var urls: Box[() => Seq[(String, Date, Double)]] = Empty
  var changeFrequency: Box[() => ChangeFrequency.Value] = Empty
  var lastMod: Box[() => Date] = Empty
  
  var baseUrl = () => "http://%s%s/".format(S.hostName, S.contextPath)

  serve {
    case "sitemap" :: _ Get _ => {
      XmlResponse(
        <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
          <url>
            <loc>{baseUrl()}</loc>
            { changeFrequency.map(fn => <changefreq>{ fn().toString }</changefreq>).openOr(NodeSeq.Empty) }
            <priority>1.0</priority>
            { lastMod.map(fn => <lastmod>{ format(fn()) }</lastmod>).openOr(NodeSeq.Empty) }
          </url>
          {
            urls.map(fn => fn() map {
              case (url, lastModified, priority) => {
                <url>
                  <loc>{baseUrl() + url}</loc>
                  <lastmod>{format(lastModified)}</lastmod>
                  <priority>{priority.toString}</priority>
                </url>
              }
            }).openOr(NodeSeq.Empty)
          }
        </urlset>)
    }
  }

  private def format(date: Date) = {
    val dt: DateTime = new DateTime(date)
    
    dt.toString("yyyy-MM-dd'T'HH:mmZZ")
  }

}
