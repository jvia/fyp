package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.fail;

public class DetectorGraphTest implements GraphStatusListener {
    int zaehler = 0;
    DetectorGraph graph;
    TimeSeries<Score> scoreTimeSeries;
    ArrayList<GraphStateChangedEvent> status = new ArrayList<GraphStateChangedEvent>();
    Object waiterObject = new Object();

    @Before
    public void setUp() throws Exception {
//        this.graph = new DetectorGraph();
//        this.graph.addGraphListener(this);
//        this.zaehler = 0;
    }

    @After
    public void cleanUp() {
//        if (this.graph != null) {
//            this.graph.removeAllListeners();
//            this.status.clear();
//        }
    }

    /*
      * test state changes
      */
    @Test
    public void testSwitchFromINITToReadyStatus() {
        System.out.println("----------------------1 testSwitchFromINITToReadyStatus--------------------------");
//        Assert.assertEquals(this.graph.getStatus(), GraphStatus.NOTREADY);
//        try {
//            this.graph.start();
//            Assert.assertEquals(this.graph.getStatus(), GraphStatus.READY);
//        } catch (ActionFailedException exception) {
//            Assert.fail(exception.getLocalizedMessage());
//        }
        System.out.println("----------------------1 testSwitchFromINITToReadyStatus--------------------------");
    }

    @Test
    public void testSwitchFromReadyToRunningStatus() {
//        try {
//            System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
//            TimeSeries<Observation> s = creatTestScoreTimeSeriesWithOneElement();
//            this.graph.setInput(s);
//            this.graph.start();
//            Thread.sleep(1000);
//            Assert.assertEquals(this.graph.getStatus(), GraphStatus.RUNNING);
//            Assert.assertEquals(2, this.status.size());
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//            Assert.fail("should not happen");
//        } catch (Exception exception) {
//            TODO Auto-generated catch block
//            exception.printStackTrace();
//        }
//        System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
    }

    @Test
    public void testSwitchFromReadyToRunningToFinishedStatus() {
//        try {
//            System.out.println("----------------------3 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
//            TimeSeries<Observation> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(5);
//            this.graph.setInput(s);
//            this.graph.start();
//            Thread.sleep(2000);
//            Assert.assertEquals(GraphStatus.RUNNING, this.graph.getStatus());
//            Assert.assertEquals(2, this.status.size());
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//            Assert.fail("caught InterruptedException but thread should not be interrrupted");
//        } catch (Exception exception) {
//            Assert.fail("caught " + exception.getLocalizedMessage());
//            exception.printStackTrace();
//        }
//        System.out.println("----------------------3 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
    }

    @Test
    public void teststartGraphWithoutTimeseries() {
//        try {
//            System.out.println("----------------------4 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
//            this.graph.start();
//            Thread.sleep(2000);
//            Assert.assertEquals(GraphStatus.READY, this.graph.getStatus());
//            Assert.assertEquals(1, this.status.size());
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//            Assert.fail("caught InterruptedException but thread should not be interrrupted");
//        } catch (Exception exception) {
//            Assert.fail("caught " + exception.getLocalizedMessage());
//            exception.printStackTrace();
//        }
//        System.out.println("----------------------4 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
    }

    /*
      * helper functions
      */
//    private TimeSeries<Observation> creatTestScoreTimeSeriesWithOneElement() {
//        TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
//        testScoreTs.add(Observation.createRandomObservation());
//        testScoreTs.get(0).markAsFirstElement();
//        return testScoreTs;
//    }

//    private TimeSeries<Observation> creatTestScoreTimeSeriesWithTwoElements() {
//        TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
//        testScoreTs.add(Observation.createRandomObservation());
//        testScoreTs.add(Observation.createRandomObservation());
//        return testScoreTs;
//    }

//    private TimeSeries<Observation> creatTestScoreTimeSeriesWithMoreThanTwoElements(int numberElements) {
//        TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
//        for (int i = 0; i < numberElements; i++) {
//            testScoreTs.add(Observation.createRandomObservation());
//        }
//        testScoreTs.get(0).markAsFirstElement();
//        testScoreTs.get(testScoreTs.size() - 1).markAsLastElement();
//        return testScoreTs;
//    }

    @Override
    public void graphStatusChanged(GraphStateChangedEvent evt) {
//        this.zaehler++;
//        System.out.println("tester got : " + evt + " " + this.zaehler);
//        this.status.add(evt);
    }

    /**
     * Test of setModel method, of class DetectorGraph.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
//        T2GramModelI inModel = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.setModel(inModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class DetectorGraph.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
//        GraphStatus newStatus = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.setStatus(newStatus);
//        TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class DetectorGraph.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
//        AnomalyClassificator classificatorToSet = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.setClassificator(classificatorToSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initGraph method, of class DetectorGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
//        DetectorGraph instance = new DetectorGraph();
//        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class DetectorGraph.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
//        DetectorGraph instance = new DetectorGraph();
//        T2GramModelI expResult = null;
//        T2GramModelI result = instance.getModel();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class DetectorGraph.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
//        DetectorGraph instance = new DetectorGraph();
//        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DetectorGraph.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
//        DetectorGraph instance = new DetectorGraph();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class DetectorGraph.
     */
    @Test
    public void testSetInput() throws Exception {
        System.out.println("setInput");
//        TimeSeries<Observation> inTimeSeries = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.setInput(inTimeSeries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificator method, of class DetectorGraph.
     */
    @Test
    public void testGetClassificator() {
        System.out.println("getClassificator");
//        DetectorGraph instance = new DetectorGraph();
//        AnomalyClassificator expResult = null;
//        AnomalyClassificator result = instance.getClassificator();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSlidingWindow method, of class DetectorGraph.
     */
    @Test
    public void testSetSlidingWindow() {
        System.out.println("setSlidingWindow");
//        SlidingWindow slidingWindow = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.setSlidingWindow(slidingWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSlidingWindow method, of class DetectorGraph.
     */
    @Test
    public void testUpdateSlidingWindow() {
        System.out.println("updateSlidingWindow");
//        SlidingWindow slidingWindow = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.updateSlidingWindow(slidingWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSlidingWindow method, of class DetectorGraph.
     */
    @Test
    public void testGetSlidingWindow() {
        System.out.println("getSlidingWindow");
//        DetectorGraph instance = new DetectorGraph();
//        SlidingWindow expResult = null;
//        SlidingWindow result = instance.getSlidingWindow();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificationTimeSeries method, of class DetectorGraph.
     */
    @Test
    public void testGetClassificationTimeSeries() {
        System.out.println("getClassificationTimeSeries");
//        DetectorGraph instance = new DetectorGraph();
//        TimeSeries expResult = null;
//        TimeSeries result = instance.getClassificationTimeSeries();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class DetectorGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
//        DetectorGraph instance = new DetectorGraph();
//        instance.cleanUp();
//        TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class DetectorGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
//        DetectorGraph instance = new DetectorGraph();
//        boolean expResult = false;
//        boolean result = instance.preconditionsSatisfied();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class DetectorGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
//        DetectorGraph instance = new DetectorGraph();
//        String expResult = "";
//        String result = instance.getReason();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoreTimeSeries method, of class DetectorGraph.
     */
    @Test
    public void testGetScoreTimeSeries() {
        System.out.println("getScoreTimeSeries");
//        DetectorGraph instance = new DetectorGraph();
//        TimeSeries expResult = null;
//        TimeSeries result = instance.getScoreTimeSeries();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class DetectorGraph.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
//        TimeseriesStatusEvent status = null;
//        DetectorGraph instance = new DetectorGraph();
//        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInfoAsString method, of class DetectorGraph.
     */
    @Test
    public void testGetInfoAsString() {
        System.out.println("getInfoAsString");
//        DetectorGraph instance = new DetectorGraph();
//        String expResult = "";
//        String result = instance.getInfoAsString();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
