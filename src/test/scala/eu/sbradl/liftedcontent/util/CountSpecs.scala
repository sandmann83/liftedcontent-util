package eu.sbradl.liftedcontent.util

import org.junit.runner.RunWith
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountSpecs extends SpecificationWithJUnit {
  
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
  
  "Count in plain text" should {
    
    "find one 'a' in 'abc'" in {
      Count("a") in abc must be equalTo 1
    }
    
    "find two 'a' in 'abcabc'" in {
      Count("a") in abc2 must be equalTo 2
    }
    
    "find three 'a' in 'abcabcabc'" in {
      Count("a") in abc3 must be equalTo 3
    }
    
    "find one 'a' in ' abc'" in {
      Count("a") in abcLeadingWhitespace must be equalTo 1
    }
    
    "find one 'a' in 'abc '" in {
      Count("a") in abcTrailingWhitespace must be equalTo 1
    }
    
  }
  
  "Count in xml" should {
    
    "find one a in <b>a<i>b</i>c</b>" in {
      Count("a") in abcXml must be equalTo 1
    }
    
    "find two a's in <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>" in {
      Count("a") in abc2Xml must be equalTo 2
    }
    
    "find three a's in <div><b>a<i>b</i>c</b><b>a<i>b</i>c</b><b>a<i>b</i>c</b></div>" in {
      Count("a") in abc3Xml must be equalTo 3
    }
    
    "find one a <b> a<i>b</i>c</b>" in {
      Count("a") in abcLeadingWhitespaceXml must be equalTo 1
    }
    
    "find one a's <b>a<i>b </i>c </b>" in {
      Count("a") in abcTrailingWhitespaceXml must be equalTo 1
    }
    
  }
  
  "Count caseinsensitive in plain text" should {
    
    "find one 'A' in 'abc'" in {
      Count("A", caseSensitive = false) in abc must be equalTo 1
    }
    
    "find two 'aB' in 'abcabc'" in {
      Count("aB", caseSensitive = false) in abc2 must be equalTo 2
    }
    
    "find three 'A' in 'abcabcabc'" in {
      Count("A", caseSensitive = false) in abc3 must be equalTo 3
    }
    
    "find one 'A' in ' abc'" in {
      Count("A", caseSensitive = false) in abcLeadingWhitespace must be equalTo 1
    }
    
    "find one 'A' in 'abc '" in {
      Count("A", caseSensitive = false) in abcTrailingWhitespace must be equalTo 1
    }
    
  }
}