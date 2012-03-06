package org.bham.aucom.fts.tranform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * A class which takes source information to determine if two components can be
 * connected to one another.
 *
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class MatrixReducer {

    private Map<Integer, List<Integer>> connections;
    private static final String FILE_NAME = "/connections.txt";
    private final Logger log = Logger.getLogger(getClass().getName());

    public MatrixReducer() {
        connections = new HashMap<Integer, List<Integer>>();
        Set<Integer> all = new HashSet<Integer>();

        Scanner s = null;
        try {
            log.config(format("Loading connections file from %s", getClass().getResourceAsStream(FILE_NAME)));
            s = new Scanner(getClass().getResourceAsStream(FILE_NAME));

            while (s.hasNextLine()) {
                // Get the next line, filtering comments & empty lines
                String line = s.nextLine();
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }

                // Parse the structure
                String[] result = line.split(" -> ");
                if (result.length == 0) {
                    continue;
                }

                for (int i = 0; i < result.length - 1; i++) {
                    int from = Integer.valueOf(result[i].trim());
                    int to = Integer.valueOf(result[i + 1].trim());

                    // Add to data structure
                    if (!connections.containsKey(from))
                        connections.put(from, new ArrayList<Integer>());
                    connections.get(from).add(to);
                }
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public boolean areConnected(int predecessor, int current) {
        boolean connected = false;

        if (predecessor == current) {
            connected = true;
        } else if (!connections.containsKey(predecessor)) {
            connected = false;
        } else {
            for (Integer successor : connections.get(predecessor)) {
                if (current == successor) {
                    connected = true;
                    break;
                }
            }
        }

        log.finer(format("Connected([%d --> %d]) = %b", predecessor, current, connected));
        return connected;
    }
}
