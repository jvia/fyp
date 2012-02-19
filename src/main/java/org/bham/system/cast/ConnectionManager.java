package org.bham.system.cast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class ConnectionManager {

    private final static Logger log = Logger.getLogger(ConnectionManager.class.getName());
    private final BlockingQueue<String[]> queue;
    private String host;
    private int port;
    private Socket cast;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean done;
    private int count;

    public ConnectionManager(String host, int port, LinkedBlockingQueue<String[]> queue) {
        count = 0;
        this.queue = queue;
        this.host = host;
        this.port = port;

        openSocket();
        openStreams();

        // add shutdon hook
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                writeToCast(new String[]{"."});
            }
        }));

        processUntilDone();
    }

    /**
     * Creates a connection with the default settings
     *
     * @param queue data strucure to store data in
     */
    public ConnectionManager(LinkedBlockingQueue<String[]> queue) {
        this("localhost", 5555, queue);
    }

    public String[] read() {
        String[] elt = null;
        try {
            elt = queue.take();
        } catch (InterruptedException ex) {
            log.severe("queue.take() interrupted");
        }

        return elt;
    }

    public void shutdown() {
        writeToCast(new String[]{"."});
    }

    public boolean isDone() {
        return done;
    }

    private void processUntilDone() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                done = false;
                while (!done) {
                    String[] fromCast = readFromCast();
                    // CAST connection is dead but there may be more to process
                    if (fromCast == null) break;

                    fromCast = escaped(fromCast);

                    try {
                        queue.put(fromCast);
                    } catch (InterruptedException ex) {
                        log.severe("queue.put() was interrupted");
                    }

                    // initiate shutdown if cast says bye
                    if (fromCast[0].equals(".")) {
                        log.info("Received shutdown from CAST");
                        writeToCast(new String[]{"."});
                        done = true;
                    } else {
                        printCastMessage(fromCast);
                    }
                }

                try {
                    cast.close();
                } catch (IOException ex) {
                    log.severe("IO exception closing CAST connection");
                }
            }

            private String[] escaped(String[] fromCast) {
                if (fromCast == null)
                    return null;

                if (fromCast.length == 1)
                    return fromCast;
                fromCast[3] = fromCast[3].replaceAll(":", "_");
                return fromCast;
            }
        }).start();
    }

    private void writeToCast(String[] toCast) {
        try {
            output.writeObject(toCast);
            output.flush();
        } catch (IOException ex) {
            log.severe("Error writing message to CAST");
        }
    }

    private String[] readFromCast() {
        String[] fromCast = null;
        try {
            fromCast = (String[]) input.readObject();
        } catch (IOException ex) {
            log.severe("Error reading from CAST");
            return null;
        } catch (ClassNotFoundException ex) {
            log.severe("Could not cast object to String[]");
        }
        return fromCast;
    }

    private void printCastMessage(String[] cast) {
        log.fine(format("%-8s %-10s %-11s %-20s [%s]",
                        "<" + (++count) + ">",
                        "[" + cast[0] + "]",
                        "[" + cast[1] + "]",
                        "[" + cast[2] + "]",
                        cast[3]));
    }

    private void openSocket() {
        int attempt = 0;
        // open socket to talk to cast
        do {
            attempt++;
            try {
                log.info(format("Connecting on %s:%d", host, port));
                cast = new Socket(host, port);
            } catch (UnknownHostException ex) {
                log.severe("CAST host does not exist");
            } catch (IOException ex) {
                // be silent
            }
        } while (cast == null || !cast.isConnected());

        log.info(format("Connected after %d attempts", attempt));
    }

    private void openStreams() {
        // open output stream
        try {
            output = new ObjectOutputStream(cast.getOutputStream());
            output.flush();
        } catch (IOException ex) {
            log.severe("Could not open output stream to CAST");
        }

        // open input stream
        try {
            input = new ObjectInputStream(cast.getInputStream());
        } catch (IOException ex) {
            log.severe("Could not open input stream to CAST");
        }
    }
}
