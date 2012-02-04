package org.bham.aucom.fts.tranform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class MatrixReducer {

    private Map<Integer, ArrayList<Integer>> connections;
    private static final String FILE_NAME = "/connections.txt";
    private final Logger log = Logger.getLogger(getClass().getName());

    public MatrixReducer() {
        connections = new HashMap<Integer, ArrayList<Integer>>();

        Scanner s = null;
        try {
            log.config("Loading connectons file from " + getClass().getResource(FILE_NAME).getPath());
            s = new Scanner(new BufferedReader(new FileReader(getClass().getResource(FILE_NAME).getFile())));

            while (s.hasNextLine()) {
                // Get the next line, filtering comments & empty lines
                String line = s.nextLine();
                if (line.startsWith("#") || line.isEmpty()) continue;

                // Parse the structure
                String[] result = line.split(" -> ");
                if (result.length == 0) continue;

                for (int i = 0; i < result.length - 1; i++) {
                    int from = Integer.valueOf(result[i].trim());
                    int to = Integer.valueOf(result[i + 1].trim());

                    // Add to data structure
                    if (!connections.containsKey(from))
                        connections.put(from, new ArrayList<Integer>());
                    connections.get(from).add(to);
                }
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Problem reading in connection file.", e);
        } finally {
            if (s != null) s.close();
        }
    }

    public boolean areConnected(int predecessor, int current) {
        for (Integer successor : connections.get(predecessor))
            if (current == successor) return true;
        return false;
    }
}
