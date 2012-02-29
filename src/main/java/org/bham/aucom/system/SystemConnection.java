package org.bham.aucom.system;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.Configuration;
import org.bham.aucom.Presentable;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Abstract class which defines the common behavior needed to create classes
 * which can connect to specific types of system, e.g., XCF, CAST, ROS.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
abstract public class SystemConnection implements Presentable {

    private static SystemConnection instance = null;
    private SystemConnectionStatus currentState;
    private SystemConnectionStatus previousState;
    private static String resourcePath = "/data/org.bham.aucom.cfg";
    private static String fileString = "data/org.bham.aucom.cfg";
    /**
     * A panel representing the SystemConnection.
     */
    protected JPanel panel;
    /**
     * The SystemConnection name.
     */
    protected String name;
    /**
     * A variable use for event handling.
     */
    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    /**
     * Creates the SystemConnection with a name for identification.
     *
     * @param inName connection name
     */
    public SystemConnection(String inName) {
        name = inName;
        currentState = SystemConnectionStatus.DISCONNECTED;
        previousState = SystemConnectionStatus.DISCONNECTED;
    }

    /**
     * Determines if the SystemConnection object is connected to the external
     * system.
     *
     * @return true i.f.f. connected
     */
    public boolean isConnected() {
        return currentState.equals(SystemConnectionStatus.CONNECTED);
    }

    /**
     * Changes the state of the SystemConnection. This is the mechanism for
     * connecting or disconnecting to an outside system.
     *
     * @param newState
     */
    public void changeState(SystemConnectionStatus newState) {
        previousState = currentState;
        currentState = newState;
        fireSystemConnectionStatusChangedEvent(new SystemConnectionStatusChangedEvent(this, previousState, currentState));
    }

    /**
     * Provides a means for system specific connection to an outside robotics
     * framework.
     *
     * @throws SystemConnectionFailedException
     *          unable to create a connection
     */
    abstract public void iConnect() throws SystemConnectionFailedException;

    /**
     * Connects to an outside system.
     *
     * @throws SystemConnectionFailedException
     *                                     could not connect
     * @throws ActionNotPermittedException already connected to the system
     */
    public void connect() throws SystemConnectionFailedException, ActionNotPermittedException {
        if (currentState.equals(SystemConnectionStatus.DISCONNECTED)) {
            iConnect();
            changeState(SystemConnectionStatus.CONNECTED);
        } else {
            throw new ActionNotPermittedException("Connection already established");
        }
    }

    /**
     * Provides a means for system specific disconnection to an outside
     * robotics
     * framework.
     */
    abstract public void iDisconnect();

    /**
     * Disconnects from the outside system.
     *
     * @throws ActionNotPermittedException no connection to disconnect from
     */
    public void disconnect() throws ActionNotPermittedException {
        if (currentState.equals(SystemConnectionStatus.CONNECTED)) {
            iDisconnect();
            changeState(SystemConnectionStatus.DISCONNECTED);
        } else {
            throw new ActionNotPermittedException("system connection not established, cannot disconnect");
        }
    }

    /**
     * Get the collection of Observation events from the system.
     *
     * @return collection of observations
     */
    public abstract TimeSeries<Observation> getObservationTimeSeries();

    /**
     * Adds a listener for the status of the system connection.
     *
     * @param listener the listener to add
     */
    public void addSystemConnectionStatusListener(SystemConnectionStatusListener listener) {
        this.listenerList.add(SystemConnectionStatusListener.class, listener);
    }

    /**
     * Removes a listener for the system connection status.
     *
     * @param listener listener to remove
     */
    public void removeSystemConnectionStatusListener(SystemConnectionStatusListener listener) {
        this.listenerList.remove(SystemConnectionStatusListener.class, listener);
    }

    /**
     * Notifies each listener of the change event.
     *
     * @param evt the system connection status change even
     */
    void fireSystemConnectionStatusChangedEvent(SystemConnectionStatusChangedEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SystemConnectionStatusListener.class) {
                ((SystemConnectionStatusListener) listeners[i + 1]).handleSystemConnectionEvent(evt);
            }
        }
    }

    /**
     * Constructs and returns a JPanel representing the system connection.
     *
     * @return a JPanel for the system connection
     */
    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new DefaultSystemConnectionPanel(this);
            panel.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, Constants.DEFAULTPRESENTABELHEIGHT));
            panel.setName(name);
        }
        return panel;
    }

    /**
     * Gets the status of the connection to the outside system.
     *
     * @return connection status
     */
    public SystemConnectionStatus getConnectionStatus() {
        return currentState;
    }

    /**
     * Returns an instance of the SystemConnection object.
     *
     * @return
     * @throws FactoryManagerInitalizationException
     *
     */
    public static SystemConnection getInstance() throws FactoryManagerInitalizationException {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    /**
     * Loads configuration information about which system to connect to.
     *
     * @return a connection for a specific system
     * @throws FactoryManagerInitalizationException
     *
     */
    private static SystemConnection load() throws FactoryManagerInitalizationException {
        try {
            Configuration cfg = Configuration.getInstance();
            if (cfg != null) {
                if (cfg.getValue("SystemConnection") != null) {
                    System.out.println(cfg.getValue("SystemConnection"));
                    return (SystemConnection) Class.forName(Configuration.getInstance().getValue("SystemConnection")).newInstance();
                }
                System.err.println("value for SystemConnection missing");
                return null;
            }
            System.err.println("no configuration found");
        } catch (Exception e) {
            e.printStackTrace();
            throw new FactoryManagerInitalizationException("couldn't find source.cfg neither in file " + fileString + " nor with path " + resourcePath + " in a resource");
        }
        return null;
    }

    /**
     * Loads a system connection from a BufferedReader.
     *
     * @param br the reader to read from
     * @return a new system connection
     * @throws ClassNotFoundException could not find the class specific in the
     *                                configuration
     * @throws InstantiationException could not create an instance of this
     *                                class
     * @throws IllegalAccessException no access to the class definition
     * @throws IOException            IO error
     */
    @SuppressWarnings("unchecked")
    private static SystemConnection loadSystemConnection(BufferedReader br) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        SystemConnection loadedConnection = null;
        String lineStr = getSystemConnectionLine(br);
        if (isValid(lineStr)) {
            String classString = getClassFromString(lineStr);
            loadedConnection = (SystemConnection) Class.forName(classString).newInstance();
        }
        return loadedConnection;
    }

    /**
     * Parse the class name from a line of text.
     *
     * @param lineStr line of text
     * @return class name
     */
    private static String getClassFromString(String lineStr) {
        String[] parts = lineStr.split(" ");
        return parts[1];
    }

    /**
     * Determines if the line is a valid line in the configuration file.
     *
     * @param lineStr line of text
     * @return true if valid
     */
    private static boolean isValid(String lineStr) {
        String[] parts = lineStr.split(" ");
        boolean isValid = parts.length == 2;
        isValid = isValid & parts[1] != "";
        return isValid;
    }

    /**
     * Find and return the line about which system to connect to.
     *
     * @param br the text stream
     * @return the line with the connection information
     * @throws IOException IO error
     */
    private static String getSystemConnectionLine(BufferedReader br) throws IOException {
        String str = null;
        while ((str = br.readLine()) != null) {
            str = str.trim();
            if (ignoreLine(str)) {
                continue;
            }
            if (str.startsWith("SystemConnection")) {
                break;
            }
        }
        return str;
    }

    /**
     * Determines if the line is commented out.
     *
     * @param str line of text
     * @return true if it begins with a '#'
     */
    private static boolean ignoreLine(String str) {
        return str.startsWith("#");
    }
}
