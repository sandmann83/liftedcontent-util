package eu.sbradl.liftedcontent.util

import java.util.Locale
import net.liftweb.http.S
import scala.Array.canBuildFrom

object LocaleHelpers {

  /**
   * Returns a sorted list of the names of all available languages 
   * translated using the current locale.
   */
  def languageNames = (Locale.getAvailableLocales map {
    locale => locale.getDisplayLanguage(S.locale)
  } distinct) sorted

  /**
   * Returns a sequence of pairs with the english name and the translated name of 
   * all available locales.
   */
  def languages: Seq[(String, String)] = (Locale.getAvailableLocales map {
    locale => (locale.getLanguage, locale.getDisplayLanguage(S.locale))
  } distinct) sorted

  def languagesPromoter = (kv: (String, String)) => kv._2

  /**
   * Returns the name of the language identified by an iso-code translated into
   * the current locale.
   * 
   * @param isoCode The iso-code of the language to get.
   */
  def languageName(isoCode: String) = try {
    Locale.forLanguageTag(isoCode).getDisplayLanguage(S.locale)
  } catch {
    case e: NullPointerException => S ? "MULTILINGUAL"
  }

}