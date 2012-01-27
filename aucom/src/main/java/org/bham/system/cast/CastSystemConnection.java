package org.bham.system.cast;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.system.SystemConnectionFailedException;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastSystemConnection extends SystemConnection {

    private CastNetworkGraph graph;

    public CastSystemConnection()
    {
        super("CastSystemConnection");
    }

    @Override
    public void iConnect() throws SystemConnectionFailedException
    {
        if (isConnected())
            return;
        graph = new CastNetworkGraph();
        try {
            graph.start();
        } catch (ActionFailedException ex) {
            log(Level.SEVERE, "Could not start CastNetworkGraph", ex);
        }
    }

    @Override
    public void iDisconnect()
    {
        if (!isConnected())
            return;
        graph.stop();
        graph = null;
    }

    @Override
    public TimeSeries<Observation> getObservationTimeSeries()
    {
        return graph.getObservationTimeSeries();
    }

    private void log(Level lvl, String msg, Exception ex)
    {
        Logger.getLogger(CastSystemConnection.class.getName()).log(lvl, msg, ex);
    }
}
