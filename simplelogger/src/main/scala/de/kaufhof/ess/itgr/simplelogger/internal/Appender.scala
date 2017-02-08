package de.kaufhof.ess.itgr.simplelogger.internal

trait Appender {
  def append(message: String)
}

class StdOutAppender extends Appender {
  override def append(message: String): Unit = {
    println(message)
  }
}

class StdErrAppender extends Appender {
  override def append(message: String): Unit = {
    System.err.println(message)
  }
}


