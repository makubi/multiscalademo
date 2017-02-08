package de.kaufhof.ess.itgr.simplelogger.internal

import de.kaufhof.ess.itgr.simplelogger.api.Logger

case class LoggerImpl(name: String) extends Logger {

  override def trace(message: String): Unit = log("TRACE", message)

  override def debug(message: String): Unit = log("DEBUG", message)

  override def info(message: String): Unit = log("INFO", message)

  override def warn(message: String): Unit = log("WARN", message)

  override def error(message: String): Unit = log("ERROR", message)

  override def fatal(message: String): Unit = log("FATAL", message)


  private def log(level: String, message: String) = {
    Seq(new StdOutAppender, new StdErrAppender).foreach(a => a.append(s"[$level - ${a.getClass.getSimpleName}] $name: ${System.currentTimeMillis()}: $message"))
  }
}
