package de.kaufhof.ess.itgr

import de.kaufhof.ess.itgr.simplelogger.api.LoggerFactory
import de.kaufhof.ess.itgr.simplelogger.internal.LoggerImpl

object FancyScalaApp extends App  {

  LoggerFactory.getLogger(FancyScalaApp.getClass).info("Hello Marvin")

  // This doesn't work
  LoggerImpl(FancyScalaApp.getClass.getName).info("Hello Helga")

}
