package org.bham.system.cast;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.system.SystemConnectionFailedException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastSystemConnection extends SystemConnection {

    private CastNetworkGraph graph;
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Create the Cast connection graph.
     */
    public CastSystemConnection() {
        super("CastSystemConnection");
    }

    @Override
    public void iConnect() throws SystemConnectionFailedException {
        if (isConnected())
            return;
        graph = new CastNetworkGraph();
        try {
            graph.start();
        } catch (ActionFailedException ex) {
            log.log(Level.SEVERE, "Could not start CastNetworkGraph", ex);
        }
    }

    @Override
    public void iDisconnect() {
        log.info("Stopping graph.");
        if (!isConnected())
            return;
        graph.stop();
        graph = null;
    }

    @Override
    public TimeSeries<Observation> getObservationTimeSeries() {
        return graph.getObservationTimeSeries();
    }
}
