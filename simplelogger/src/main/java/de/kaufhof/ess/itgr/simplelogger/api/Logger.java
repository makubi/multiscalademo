package de.kaufhof.ess.itgr.simplelogger.api;

public interface Logger {
    void trace(String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
    void fatal(String message);
}
