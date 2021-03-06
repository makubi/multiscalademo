package de.kaufhof.ess.itgr.simplelogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleLoggerClassLoader extends ClassLoader {

    static {
        ClassLoader.registerAsParallelCapable();
    }

    private static final String apiPackageName = "de.kaufhof.ess.itgr.simplelogger";
    private static final String thirdPartyLibsFolderName = "de.kaufhof.ess.itgr.simplelogger.thirdpartylibs";

    private final ClassLoader resourceClassLoader;

    public SimpleLoggerClassLoader(ClassLoader parentClassLoader) {
        super(null);
        this.resourceClassLoader = parentClassLoader;
        assert parentClassLoader != null;
        assert getParent() == null; // Essential, don't use it by default in loadClass, only used for looking up resources
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        final String hiddenClassResourceName = getClassResourceName(name);

        try {
            System.out.println("[LoggingClassLoader] trying to load " + hiddenClassResourceName + " as resource");
            return getClass(name, hiddenClassResourceName);
        } catch(IOException e){
            throw new ClassNotFoundException("Loading class " + name + "(" + hiddenClassResourceName + ") was not successful", e);
        }
    }

    private String getClassResourceName(String className) {
        String prefixFolder = className.startsWith(apiPackageName)
                        ? ""
                        : thirdPartyLibsFolderName + "/";

        return prefixFolder + className.replace(".", "/") + ".class";
    }

    private Class<?> getClass(String className, String resource) throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try(final InputStream is = resourceClassLoader.getResourceAsStream(resource)) {
            if (is != null) {
                int nRead;
                byte[] data = new byte[1024];

                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                System.out.println("[LoggingClassLoader] Loaded bytes of " + className + " successfully - defining class.");
                return this.defineClass(className, buffer.toByteArray(), 0, buffer.size());
            } else {
                throw new ClassNotFoundException("Loading class " + className + "(" + resource + ") was unsuccessful");
            }
        }
    }

}


