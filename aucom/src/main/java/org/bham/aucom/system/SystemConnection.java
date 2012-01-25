package org.bham.aucom.system;

import java.awt.Dimension;
import javax.swing.JPanel;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.Configuration;
import org.bham.aucom.Presentable;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;


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
    private static final String resourcePath = "/data/aucom.cfg";
    private static final String fileString = "data/aucom.cfg";
    /**
     * A panel representing the SystemConnection.
     */
    protected JPanel panel;
    /**
     * The SystemConnection name.
     */
    protected final String name;
    /**
     * A variable use for event handling.
     */
    private final javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    /**
     * Creates the SystemConnection with a name for identification.
     * 
     * @param inName connection name
     */
    protected SystemConnection(String inName)
    {
        name = inName;
        currentState = SystemConnectionStatus.DISCONNECTED;
        previousState = SystemConnectionStatus.DISCONNECTED;
    }

    /**
     * Determines if the SystemConnection object is connected to the external system.
     * 
     * @return true i.f.f. connected
     */
    public boolean isConnected()
    {
        return currentState.equals(SystemConnectionStatus.CONNECTED);
    }

    /*
     * Changes the state of the SystemConnection. This is the mechanism for 
     * connecting or disconnecting to an outside system.
     * 
     * @param newState
     */
    void changeState(SystemConnectionStatus newState)
    {
        previousState = currentState;
        currentState = newState;
        fireSystemConnectionStatusChangedEvent(new SystemConnectionStatusChangedEvent(this, previousState, currentState));
    }

    /**
     * Provides a means for system specific connection to an outside robotics
     * framework.
     * 
     * @throws SystemConnectionFailedException unable to create a connection
     */
    protected abstract void iConnect() throws SystemConnectionFailedException;

    /**
     * Connects to an outside system.
     * 
     * @throws SystemConnectionFailedException could not connect
     * @throws ActionNotPermittedException already connected to the system
     */
    public void connect() throws SystemConnectionFailedException, ActionNotPermittedException
    {
        if (currentState.equals(SystemConnectionStatus.DISCONNECTED)) {
            iConnect();
            changeState(SystemConnectionStatus.CONNECTED);
        } else {
            throw new ActionNotPermittedException("Connection already established");
        }
    }

    /**
     * Provides a means for system specific disconnection to an outside robotics
     * framework.
     */
    protected abstract void iDisconnect();

    /**
     * Disconnects from the outside system.
     * 
     * @throws ActionNotPermittedException no connection to disconnect from
     */
    public void disconnect() throws ActionNotPermittedException
    {
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
    public void addSystemConnectionStatusListener(SystemConnectionStatusListener listener)
    {
        this.listenerList.add(SystemConnectionStatusListener.class, listener);
    }

    /**
     * Removes a listener for the system connection status.
     * 
     * @param listener listener to remove
     */
    public void removeSystemConnectionStatusListener(SystemConnectionStatusListener listener)
    {
        this.listenerList.remove(SystemConnectionStatusListener.class, listener);
    }

    /**
     * Notifies each listener of the change event.
     * 
     * @param evt the system connection status change even
     */
    void fireSystemConnectionStatusChangedEvent(SystemConnectionStatusChangedEvent evt)
    {
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
    public JPanel getPanel()
    {
        if (panel == null) {
            panel = new DefaultSystemConnectionPanel(this);
            panel.setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, Constants.DEFAULT_PRESENTABLE_HEIGHT));
            panel.setName(name);
        }
        return panel;
    }

    /**
     * Gets the status of the connection to the outside system.
     * 
     * @return connection status
     */
    public SystemConnectionStatus getConnectionStatus()
    {
        return currentState;
    }

    /*
     * Returns an instance of the SystemConnection object.
     * 
     * @return 
     * @throws FactoryManagerInitalizationException
     */
    public static SystemConnection getInstance() throws FactoryManagerInitalizationException
    {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    /*
     * Loads configuration information about which system to connect to.
     * 
     * @return a connection for a specific system
     * @throws FactoryManagerInitalizationException 
     */
    private static SystemConnection load() throws FactoryManagerInitalizationException
    {
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
}
