package org.bham.aucom.fts.source;

import junit.framework.Assert;
import net.sf.xcf.fts.Event;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TimeSeriesSourceTest implements SourceStatusListener {

    TimeSeriesSource<Observation> source;
    private TimeSeries<Observation> sequence;
    ArrayList<SourceStatusEvent> status;

    @Before
    public void setUp() throws Exception {
        this.sequence = new ObservationTimeSeries();
        this.source = new TimeSeriesSource<Observation>(this.sequence, "testSource");
        this.status = new ArrayList<SourceStatusEvent>();
        this.source.addSourceStatusListener(this);
    }

    @Test
    public void testAddingElements() {
        this.sequence.add(Observation.createRandomObservation());
        Assert.assertEquals(this.sequence.size(), this.source.size());
        ArrayList<Observation> list = new ArrayList<Observation>();
        list.add(Observation.createRandomObservation());
        list.add(Observation.createRandomObservation());
        this.sequence.addAll(list);
        Assert.assertEquals(this.sequence.size(), this.source.size());
    }

    @Test
    public void testFireQueueChangeEvents() {
        Assert.assertEquals(0, this.status.size());
        Observation d = getRandomObservation();
        this.sequence.add(d);
        Assert.assertEquals(0, this.status.size());
        try {
            this.status.clear();
            Event<? extends Observation> event = this.source.next();
            Assert.assertEquals(1, this.status.size());
            this.status.get(0).equals(SourceStatus.FIRST_ELEMENT_SENT);
        } catch (Exception exception1) {
            exception1.printStackTrace();
        }
    }

    @Test
    public void testReceivedFirstElementEventSent() {
        Assert.assertEquals(0, this.status.size());
        Observation d = getRandomObservation();
        this.sequence.add(d);
        d = getRandomObservation();
        Assert.assertEquals(0, this.status.size());
        try {
            this.status.clear();
            Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
            Event<? extends Observation> event = this.source.next();
            Assert.assertEquals(1, this.status.size());
            Assert.assertEquals(this.source.getStatus(), SourceStatus.RUNNING);
            Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testReceivedFirstElementEventSent_ONLYONCE() {
        System.out.println("testReceivedFirstElementEventSent_ONLYONCE--------------------------------------------------------");
        Observation d = getRandomObservation();
        this.sequence.add(d);
        d = getRandomObservation();
        this.sequence.add(d);
        d = getRandomObservation();
        this.sequence.add(d);
        d = getRandomObservation();
        this.sequence.add(d);
        Assert.assertEquals(0, this.status.size());
        try {
            Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
            this.source.next();
            Assert.assertEquals(this.source.getStatus(), SourceStatus.RUNNING);
            Assert.assertEquals(1, this.status.size());
            Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
            this.source.next();
            this.source.next();
            Assert.assertEquals(1, this.status.size());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("testReceivedFirstElementEventSent_ONLYONCE--------------------------------------------------------");
    }

    @Test
    public void testReceivedLastElementEventSent() {
        Assert.assertEquals(0, this.status.size());
        Observation d = Observation.createRandomObservation();
        d.markAsLastElement();
        this.sequence.add(d);
        try {
            this.status.clear();
            Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
            Event<? extends Observation> event = this.source.next();
            Assert.assertEquals(2, this.status.size());
            Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
            Assert.assertEquals(SourceStatus.LAST_ELEMENT_SENT, this.status.get(1).getStatus());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void resumeAfterPausedTest() {
        Observation d = getRandomObservation();
        this.sequence.add(d);
        Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
        this.source.pause();
        Assert.assertEquals(this.source.getStatus(), SourceStatus.PAUSED);
        try {
            this.source.resume();
        } catch (IllegalStateChange exception) {
            Assert.fail("resume needs to throw an exception when called not in paused mode");
        }
        Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
    }

    @Test
    public void resumedBeforePausedTest() {
        Observation d = getRandomObservation();
        this.sequence.add(d);
        Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
        try {
            this.source.resume();
            Assert.fail("resume needs to throw an IllegalStateChange when called not in paused mode");
        } catch (IllegalStateChange exception) {
            // ignore
        }
        Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
    }

    @Test
    public void pauseBeforeFirstElementTest() {
        System.out.println("-------------------------------------------------------- pauseBeforeFirstElementTest");
        Observation d = getRandomObservation();
        Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
        // initial the source is ready
        this.source.pause(); // pause should work also in ready state
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("blocked");
                    source.next();
                    System.out.println("unblocked");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        t.start();
        try {
            Thread.sleep(100);
            Assert.assertEquals(SourceStatus.PAUSED, this.source.getStatus());
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("pauseBeforeFirstElementTest--------------------------------------------------------");
    }

    @Test
    public void pauseAfterFirstElementTest() {
        System.out.println("pauseAfterFirstElementTest--------------------------------------------------------");
        Observation d = getRandomObservation();
        this.sequence.add(d);
        try {
            source.next();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.source.pause();
        Assert.assertEquals(SourceStatus.PAUSED, this.source.getStatus());
        System.out.println("--------------------------------------------------------pauseAfterFirstElementTest");
    }


    /**
     * @return
     */
    private Observation getRandomObservation() {
        Observation d = Observation.createRandomObservation();
//		d.markAsFirstElement();
        return d;
    }

    @Override
    public void sourceStatusChanged(SourceStatusEvent status) {
        System.out.println("sequenceQueueSourceStatusChanged: " + status.getStatus());
        this.status.add(status);
    }

    /**
     * Test of iNextItem method, of class TimeSeriesSource.
     */
    @Test
    public void testINextItem() throws Exception {
        System.out.println("iNextItem");
        TimeSeriesSource instance = null;
        AbstractData expResult = null;
        AbstractData result = instance.iNextItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQueue method, of class TimeSeriesSource.
     */
    @Test
    public void testGetQueue() {
        System.out.println("getQueue");
        TimeSeriesSource instance = null;
        BlockingQueue expResult = null;
        BlockingQueue result = instance.getQueue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class TimeSeriesSource.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        TimeSeriesSource instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class TimeSeriesSource.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        TimeSeriesSource instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyDataFromTimeSeriesToSourceQueue method, of class
     * TimeSeriesSource.
     */
    @Test
    public void testCopyDataFromTimeSeriesToSourceQueue() {
        System.out.println("copyDataFromTimeSeriesToSourceQueue");
        int startIndex = 0;
        int endIndex = 0;
        TimeSeriesSource instance = null;
        instance.copyDataFromTimeSeriesToSourceQueue(startIndex, endIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class TimeSeriesSource.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        TimeSeriesSource instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class TimeSeriesSource.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        TimeSeries<Classification> newInput = null;
        TimeSeriesSource instance = null;
        instance.setInput(newInput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInput method, of class TimeSeriesSource.
     */
    @Test
    public void testGetInput() {
        System.out.println("getInput");
        TimeSeriesSource instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iDisconnect method, of class TimeSeriesSource.
     */
    @Test
    public void testIDisconnect() throws Exception {
        System.out.println("iDisconnect");
        TimeSeriesSource instance = null;
        instance.iDisconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iConnect method, of class TimeSeriesSource.
     */
    @Test
    public void testIConnect() throws Exception {
        System.out.println("iConnect");
        TimeSeriesSource instance = null;
        instance.iConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReady method, of class TimeSeriesSource.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        TimeSeriesSource instance = null;
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}