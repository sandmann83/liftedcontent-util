package de.sbradl.liftedcontent.util

import net.liftweb.mapper.BaseMetaMapper
import net.liftweb.sitemap.ConvertableToMenu

/**
 * Trait for pluggable modules which can be integrated into LiftedContent.
 */
trait Module {
  
  /**
   * Define the name of the module.
   */
  def name: String
  
  def id = name.toLowerCase

  /**
   * Override this method to tell the CMS which classes need to be schemefied
   */
  def mappers: List[BaseMetaMapper] = List()
  
  /**
   * The menus for this module.
   */
  def menus: List[ConvertableToMenu] = List()
  
  /**
   * Resource files used by this module.
   */
  def resourceNames: List[String] = List("i18n/" + name.toLowerCase)
  
  /**
   * The packages which are added for snippet resolution.
   */
  def packageToAdd = this.getClass().getPackage().getName()
  
  def init {
    
  }
  
  def needsSetup = setupMenu match {
    case Some(_) => true
    case None => false
  }
  
  def setupMenu: Option[ConvertableToMenu] = None
  
  def isInstalled = true
}