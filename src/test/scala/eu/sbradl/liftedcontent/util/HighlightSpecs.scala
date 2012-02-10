package eu.sbradl.liftedcontent.util

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import de.sbradl.liftedcontent.util.Highlight
import scala.xml.Unparsed

@RunWith(classOf[JUnitRunner])
class HighlightSpecs extends SpecificationWithJUnit {

  val abc = "abc"
  val abc2 = abc * 2
  val abc3 = abc * 3

  val abcLeadingWhitespace = " abc"
  val abcTrailingWhitespace = "abc "

  val abcXml = <b>a<i>b</i>c</b>
  val abc2Xml = <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>
  val abc3Xml = <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>

  val abcLeadingWhitespaceXml = <b> a<i>b</i>c</b>
  val abcTrailingWhitespaceXml = <b>a<i>b </i>c </b>

  "Highlight in plain text" should {

    "create <mark>a</mark>bc from 'abc'" in {
      Highlight("a") in abc must be equalTo Unparsed("<mark>a</mark>bc")
    }

    "create <mark>a</mark>bc<mark>a</mark>bc from 'abcabc'" in {
      Highlight("a") in abc2 must be equalTo Unparsed("<mark>a</mark>bc<mark>a</mark>bc")
    }

    "create <mark>a</mark>bc<mark>a</mark>bc<mark>a</mark>bc from 'abcabcabc'" in {
      Highlight("a") in abc3 must be equalTo Unparsed("<mark>a</mark>bc<mark>a</mark>bc<mark>a</mark>bc")
    }

  }

  "Highlight in xml" should {

    "create <mark>a</mark>bc from <b>a<i>b</i>c</b>" in {
      Highlight("a") in abcXml must be equalTo Unparsed("<mark>a</mark>bc")
    }

    "create <mark>a</mark>bc<mark>a</mark>bc from <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>" in {
      Highlight("a") in abc2Xml must be equalTo Unparsed("<mark>a</mark>bc<mark>a</mark>bc")
    }

    "create <mark>a</mark>bc<mark>a</mark>bc<mark>a</mark>bc from <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>" in {
      Highlight("a") in abc3Xml must be equalTo Unparsed("<mark>a</mark>bc<mark>a</mark>bc<mark>a</mark>bc")
    }

  }
}