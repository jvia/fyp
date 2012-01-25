package org.bham.aucom.system;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Logger;

public class SystemConnectionFactoryManager {
    private static final HashMap<String, Class<? extends SystemConnection>> map = new HashMap<String, Class<? extends SystemConnection>>();
    private static SystemConnectionFactoryManager instance = null;
    private static final String resourcePath = "/data/source.cfg";
    private static final String fileString = "data/source.cfg";

    protected SystemConnectionFactoryManager() throws FactoryManagerInitalizationException {
        initialize();
    }

    public static SystemConnectionFactoryManager getInstance() throws FactoryManagerInitalizationException {
        if (instance == null) {
            instance = new SystemConnectionFactoryManager();
        }
        return instance;
    }

    private static void initialize() throws FactoryManagerInitalizationException {
        try {
            if (externFileExists()) {
                loadExternClassesFile();
            } else {
                loadInternClassesFile();
            }
        } catch (Exception e) {
            try {
            } catch (Exception ee) {
                ee.printStackTrace();
                throw new FactoryManagerInitalizationException("couldn't find source.cfg neither in file " + fileString + " nor with path " + resourcePath + " in a resource");
            }
        }

    }

    private static boolean externFileExists() {
        return new File(fileString).exists();
    }

    private static void loadExternClassesFile() throws NumberFormatException, IOException, ClassNotFoundException {
        loadFactories(new File(fileString));
    }

    private static void loadFactories(File file) throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        loadFactories(br);
    }

    private static void loadFactories(InputStream inStream) throws IOException, ClassNotFoundException {
        if (inStream != null) {
            loadFactories(new BufferedReader(new InputStreamReader(inStream)));
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadFactories(BufferedReader br) throws IOException {
        String lineStr;
        String factoryName;
        String factoryClass;
        while ((lineStr = br.readLine()) != null) {
            String[] parts = lineStr.split(" ");
            if (parts.length != 2) {
                Logger.getLogger(SystemConnectionFactoryManager.class.getCanonicalName()).warning("wrong number of argmuents in line");
                System.err.println("wrong number of argmuents in line");
                continue;
            }
            factoryName = parts[0];
            factoryClass = parts[1];
            try {
                map.put(factoryName, (Class<SystemConnection>) Class.forName(factoryClass));
            } catch (ClassNotFoundException exception) {
                Logger.getLogger(SystemConnectionFactoryManager.class.getCanonicalName()).warning("class " + exception.getLocalizedMessage() + " not found");
                System.err.println("class " + exception.getLocalizedMessage() + " not found");
            }
        }
    }

    private static void loadInternClassesFile() throws NumberFormatException, IOException, ClassNotFoundException {
        InputStream inStream = SystemConnectionFactoryManager.class.getResourceAsStream(resourcePath);
        loadFactories(inStream);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public SystemConnection getConnection(String factoryName) throws SystemConnectionNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException,
                                                                     IllegalAccessException, InvocationTargetException {
        if (!map.containsKey(factoryName)) {
            throw new SystemConnectionNotFoundException(factoryName);
        }
        Class<? extends SystemConnection> cl = map.get(factoryName);
        Class<?>[] parameterClasses = {};
        Constructor<?> ownConstructor = cl.getConstructor(parameterClasses);
        Object[] paramObjects = {};
        return (SystemConnection) ownConstructor.newInstance(paramObjects);
    }

    public Class<?> getFactoryClass(String factoryName) throws SystemConnectionNotFoundException {
        if (!map.containsKey(factoryName)) {
            throw new SystemConnectionNotFoundException(factoryName);
        }
        return map.get(factoryName);
    }
}