package org.bham.aucom.system;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;

public class DummySystemConnection extends SystemConnection {

    public DummySystemConnection() {
        super("DummySystemConnection");
    }

    @Override
    public void iConnect() throws SystemConnectionFailedException {
        // dummy function
    }

    @Override
    public void iDisconnect() {
        // dummy function
    }

    @Override
    public TimeSeries<Observation> getObservationTimeSeries() {
        // TODO Auto-generated method stub
        return null;
    }

}
