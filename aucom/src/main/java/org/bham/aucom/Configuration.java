package org.bham.aucom;

import org.bham.aucom.system.SystemConnectionFactoryManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 * This class loads properties about the system from file and maintains those
 * properties in Map that can be accessed as necessary by other components.
 * <p/>
 * This is a singleton object.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public final class Configuration {

    private static String resourcePath = "/data/aucom.cfg";
    private static String fileString = "data/aucom.cfg";
    private static Configuration instance = null;
    private HashMap<String, String> map;

    /*
     * Creates the private instance of the Configuration object.
     */
    private Configuration() {
        map = new HashMap<String, String>();
    }

    /**
     * Returns the singleton of the Configuration object.
     *
     * @return the configuration instance
     */
    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();

        try {
            instance.load();
        } catch (Exception ex) {
            System.err.println("Couldn't load configuration file");
        }
        return instance;
    }

    /**
     * Returns the value associated with a configuration parameter.
     *
     * @param inKey configuration name
     * @return configuration value
     */
    public String getValue(String inKey) {
        return map.get(inKey);
    }

    /**
     * Returns the number of configuration parameters in the object.
     *
     * @return number of configuration parameters.
     */
    public int size() {
        return map.size();
    }

    /**
     * Loads the configuration file.
     *
     * @throws Exception if something went wrong
     */
    private void load() throws Exception {
        if (externFileExists()) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("loading form extern file");
            loadFromExternalFile();
        }

        if (internFileExists()) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("loading form intern file");
            loadFromInternFile();
        }

        Logger.getLogger(this.getClass().getCanonicalName()).info("loading done");
    }

    /**
     * Determines if an intern file exists.
     *
     * @return true if the file exists
     */
    private boolean internFileExists() {
        return SystemConnectionFactoryManager.class.getResourceAsStream(resourcePath) != null;
    }

    /**
     * Determines if an extern file exists.
     *
     * @return true if file exists
     */
    private boolean externFileExists() {
        return new File(fileString).exists();
    }

    /**
     * Removes all configuration information from the Singleton class.
     */
    public void resetConfiguration() {
        map.clear();
        instance = null;
    }

    /**
     * Loads the configuration parameters into the map.
     *
     * @param br buffer to read from
     */
    private void loadFromBuffer(BufferedReader br) {
        ArrayList<String> linesToLoad = getLinesToConsider(br);
        linesToLoad = getValidLines(linesToLoad);

        for (String line : linesToLoad) {
            String[] parts = line.split(" ");
            map.put(parts[0], parts[1]);
        }
    }

    /**
     * Returns a list of all the valid lines.
     *
     * @param linesToLoad the set of all lines
     * @return the valid set of lines
     */
    private ArrayList<String> getValidLines(ArrayList<String> linesToLoad) {
        ArrayList<String> lines = new ArrayList<String>();
        for (String line : linesToLoad) {
            if (isValid(line)) {
                lines.add(line);
            }
        }
        return lines;
    }

    /*
     * Returns a list of all valid lines.
     * 
     * @param br the source to read from
     * @return a list of valid lines
     */
    private ArrayList<String> getLinesToConsider(BufferedReader br) {
        ArrayList<String> lines = new ArrayList<String>();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                str = str.trim();
                if (ignoreLine(str)) {
                    continue;
                }
                lines.add(str);
            }
        } catch (IOException exception) {
            System.err.println("exception caught: " + exception.getLocalizedMessage());
        }
        return lines;
    }

    /**
     * Loads an external file.
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void loadFromExternalFile() throws NumberFormatException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileString)));
        loadFromBuffer(br);
        br.close();
    }

    /**
     * Loads an internal file.
     *
     * @throws NumberFormatException  number format is exception
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void loadFromInternFile() throws NumberFormatException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        InputStream inStream = this.getClass().getResourceAsStream(resourcePath);
        loadFromBuffer(new BufferedReader(new InputStreamReader(inStream)));
    }

    /**
     * Determines if the line is a comment. Any line which begins with "#" is
     * deemed to be a comment.
     *
     * @param str the line in question
     * @return true if the line is a comment
     */
    private static boolean ignoreLine(String str) {
        return str.startsWith("#");
    }

    /**
     * Determines if the line in the configuration file is valid.
     * <p/>
     * A line is valid if it is in the form of "key value", i.e., a parameter
     * name followed by a space followed by a parameter value.
     *
     * @param lineStr a line in the configuration file
     * @return true if valid, false otherwise
     */
    private static boolean isValid(String lineStr) {
        String[] parts = lineStr.split(" ");
        return (parts.length == 2) && (!parts[0].isEmpty());
    }
}
