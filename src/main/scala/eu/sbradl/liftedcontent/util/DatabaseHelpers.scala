package eu.sbradl.liftedcontent.util

import net.liftweb.mapper.Mapper
import net.liftweb.http.S

object DatabaseHelpers {
  
  /**
   * Validates and saves the given mapper instance. If validation fails the errors 
   * a messaged using [[S.error]].
   * 
   * @param mapper The mapper instance to use.
   */
  def save[T <: Mapper[T]](mapper: Mapper[T]) {
    mapper.validate match {
      case List() => mapper.save
      case errors => errors foreach {
        error => S.error(error.msg)
      }
    }
  }
  
}