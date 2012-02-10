package de.sbradl.liftedcontent.util

import net.liftweb.mapper.Mapper
import net.liftweb.http.S

object DatabaseHelpers {
  
  def save[T <: Mapper[T]](mapper: Mapper[T]) {
    mapper.validate match {
      case List() => mapper.save
      case errors => errors foreach {
        error => S.error(error.msg)
      }
    }
  }
  
}