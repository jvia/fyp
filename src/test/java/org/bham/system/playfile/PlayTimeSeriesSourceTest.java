package org.bham.system.playfile;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.junit.Before;
import org.junit.Test;

public class PlayTimeSeriesSourceTest {
    private static final int ITERATIONS = 50;
    PlayTimeSeriesSource<Observation> src;

    @Before
    public void setUp() throws Exception {
        src = new PlayTimeSeriesSource<Observation>();
    }

    @Test(expected = ActionFailedException.class)
    public void testIConnectExceptionCase() throws ActionFailedException {
        src.iConnect();

    }

    @Test(expected = ActionFailedException.class)
    public void testIConnectExceptionCase2() throws ActionFailedException {
        src.setInput(null);
        src.iConnect();
    }

    @Test(expected = ActionFailedException.class)
    public void testIConnectTimeSeriesLenghtIsNull() throws ActionFailedException {
        src.setInput(new ObservationTimeSeries());
        src.iConnect();
    }

    @Test
    public void testIConnect() throws ActionFailedException {
        try {
            ObservationTimeSeries ts = new ObservationTimeSeries();
            ts.add(Observation.createRandomObservation());
            ts.add(Observation.createRandomObservation());
            ts.add(Observation.createRandomObservation());
            ts.add(Observation.createRandomObservation());
            ts.get(0).setTimestamp(System.currentTimeMillis());
            Thread.sleep(10);
            ts.get(1).setTimestamp(System.currentTimeMillis());
            Thread.sleep(10);
            ts.get(2).setTimestamp(System.currentTimeMillis());
            Thread.sleep(10);
            ts.get(3).setTimestamp(System.currentTimeMillis());
            src.setInput(ts);
            src.iConnect();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testScheduler() throws ActionFailedException {
        try {
            ObservationTimeSeries ts = new ObservationTimeSeries();
            for (int i = 0; i < ITERATIONS; i++) {
                ts.add(Observation.createRandomObservation());
            }
            for (int i = 0; i < ITERATIONS; i++) {
                Thread.sleep(1);
                ts.get(i).setTimestamp(System.currentTimeMillis());
            }
            src.setInput(ts);
            src.iConnect();
            synchronized (this) {
                this.wait(40 * ITERATIONS);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }


    @Test
    public void testIDisconnect() {
        try {
            src.iDisconnect();
        } catch (ActionFailedException exception) {
            exception.printStackTrace();
        }
    }

}
