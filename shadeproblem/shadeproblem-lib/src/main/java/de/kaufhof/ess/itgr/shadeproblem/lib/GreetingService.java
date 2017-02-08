package de.kaufhof.ess.itgr.shadeproblem.lib;

import java.lang.reflect.Method;

public class GreetingService {

    public static void greet(String name) {
        try {
            final String consolePrinterClassName = new StringBuilder("d").append("e").append(".")
                    .append("kaufhof.ess.itgr.shadeproblem.lib.ConsolePrinter").toString();
            final Class<?> consolePrinterClass = Class.forName(consolePrinterClassName);
            final Method printMethod = consolePrinterClass.getMethod("print", String.class);

            printMethod.invoke(null, "Hello " + name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
