package de.kaufhof.ess.itgr.simplelogger.api;

import de.kaufhof.ess.itgr.simplelogger.SimpleLoggerClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerFactory {

    final private static SimpleLoggerClassLoader simpleLoggingClassLoader = new SimpleLoggerClassLoader(LoggerFactory.class.getClassLoader());

    public static Logger getLogger(Class<?> clazz){

        final String loggerName = clazz.getName();

        try {
            final Class<?> behindProxyClass = simpleLoggingClassLoader.loadClass("de.kaufhof.ess.itgr.simplelogger.internal.LoggerImpl");
            final Object loggerInstance = behindProxyClass.getConstructor(String.class).newInstance(loggerName);

            final InvocationHandler handler = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return loggerInstance.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).invoke(loggerInstance, args);
                }
            };

            return (Logger) Proxy.newProxyInstance(LoggerFactory.class.getClassLoader(), new Class[]{ Logger.class }, handler);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
