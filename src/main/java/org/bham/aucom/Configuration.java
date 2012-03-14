package org.bham.aucom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static final String resourcePath = "/data/aucom.cfg";
    private static final String fileString = "data/aucom.cfg";

    private final Logger log = Logger.getLogger(getClass().getName());
    private final HashMap<String, String> map;

    private static Configuration instance = null;

    /**
     * Creates the private instance of the Configuration object.
     */
    private Configuration() {
        map = new HashMap<String, String>();
    }

    /**
     * Returns the singleton of the Configuration object.
     *
     * @return return the singleton instance
     */
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }

        try {
            instance.load();
        } catch (Exception ignore) {
        }
        return instance;
    }

    /**
     * Returns the value associated with a configuration parameter.
     *
     * @param inKey configuration name
     * @return configuration value
     */
    public String getValue(final String inKey) {
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
            log.info("loading from extern file");
            loadFromExternFile();
        }
        if (internFileExists()) {
            log.info("loading from intern file");
            loadFromInternFile();
        }
        log.info("loading done");
    }

    /**
     * Determines if an intern file exists.
     *
     * @return true if the file exists
     */
    private boolean internFileExists() {
        InputStream s = getClass().getResourceAsStream(resourcePath);
        boolean exists = (s != null);

        if (exists) {
            try {
                s.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        return exists;
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
     * Loads the configuration parameters into the map.
     *
     * @param br buffer to read from
     */
    private void loadFromBuffer(final BufferedReader br) {
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
     * @param linesToLoad list of all lines
     * @return the list of valid lines
     */
    private ArrayList<String> getValidLines(
            final ArrayList<String> linesToLoad) {
        ArrayList<String> lines = new ArrayList<String>();
        for (String line : linesToLoad) {
            if (isValid(line)) {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Returns a list of all valid lines.
     *
     * @param br the source to read from
     * @return a list of valid lines
     */
    private ArrayList<String> getLinesToConsider(final BufferedReader br) {
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
        } catch (IOException ignored) {
        }
        return lines;
    }

    /**
     * Loads an external file.
     *
     * @throws java.io.IOException Error reading file
     */
    private void loadFromExternFile() throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(new File(fileString)));
        loadFromBuffer(br);
        br.close();
    }

    /**
     * Loads an internal file.
     */
    private void loadFromInternFile() {
        InputStream inStream = getClass().getResourceAsStream(resourcePath);
        loadFromBuffer(new BufferedReader(new InputStreamReader(inStream)));
    }

    /**
     * Determines if the line is a comment. Any line which begins with "#" is
     * deemed to be a comment.
     *
     * @param str the line in question
     * @return true if the line is a comment
     */
    private static boolean ignoreLine(final String str) {
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
    private static boolean isValid(final String lineStr) {
        String[] parts = lineStr.split(" ");
        return (parts.length == 2) && (!parts[0].isEmpty());
    }
}
