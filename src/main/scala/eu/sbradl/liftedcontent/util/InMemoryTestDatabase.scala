package eu.sbradl.liftedcontent.util

import net.liftweb.common.Empty
import net.liftweb.common.Full
import net.liftweb.common.Logger
import net.liftweb.db.DB1.db1ToDb
import net.liftweb.db.DefaultConnectionIdentifier
import net.liftweb.db.StandardDBVendor
import net.liftweb.mapper.BaseMetaMapper
import net.liftweb.mapper.DB
import net.liftweb.mapper.Schemifier

/**
 * This is a helper object for initializing an in-memory db for usage in tests.
 */
object InMemoryTestDatabase {

  private val vendor = new StandardDBVendor("org.h2.Driver",
    "jdbc:h2:mem:lift;DB_CLOSE_DELAY=-1", Empty, Empty)

  Logger.setup = Full(net.liftweb.util.LoggingAutoConfigurer())
  Logger.setup.foreach { _.apply() }

  /**
   * Initializes the test database.
   * 
   * @param models A list of mappers which should be schemified.
   */
  def init(models: BaseMetaMapper*) {
    DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    Schemifier.destroyTables_!!(Schemifier.infoF _, models:_*)
    Schemifier.schemify(true, Schemifier.infoF _, models:_*)
  }

  def shutdown {
    // TODO: figure out if anything goes here  
  }

}