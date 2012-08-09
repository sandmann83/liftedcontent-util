package eu.sbradl.liftedcontent.util

object WordDensity {

  def forText(s: String) = {
    var modifiedText = s
    
    List("\\.", "\\,", "\\:", "\\!", "\\?") foreach {
      p => modifiedText = modifiedText.replaceAll(p, " ")
    }
    
    modifiedText.split(" ").filterNot(s => s.trim.isEmpty).groupBy(w => w) map {
      case (word, ls) => (word, ls.size)
    }
  }
  
}