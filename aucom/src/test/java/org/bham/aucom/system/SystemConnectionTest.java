/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

import javax.swing.JPanel;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class SystemConnectionTest {

    public SystemConnectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isConnected method, of class SystemConnection.
     */
    @Test
    public void testIsConnected() {
        System.out.println("isConnected");
        SystemConnection instance = null;
        boolean expResult = false;
        boolean result = instance.isConnected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeState method, of class SystemConnection.
     */
    @Test
    public void testChangeState() {
        System.out.println("changeState");
        SystemConnectionStatus newState = null;
        SystemConnection instance = null;
        instance.changeState(newState);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iConnect method, of class SystemConnection.
     */
    @Test
    public void testIConnect() throws Exception {
        System.out.println("iConnect");
        SystemConnection instance = null;
        instance.iConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class SystemConnection.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        SystemConnection instance = null;
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iDisconnect method, of class SystemConnection.
     */
    @Test
    public void testIDisconnect() {
        System.out.println("iDisconnect");
        SystemConnection instance = null;
        instance.iDisconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class SystemConnection.
     */
    @Test
    public void testDisconnect() throws Exception {
        System.out.println("disconnect");
        SystemConnection instance = null;
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObservationTimeSeries method, of class SystemConnection.
     */
    @Test
    public void testGetObservationTimeSeries() {
        System.out.println("getObservationTimeSeries");
        SystemConnection instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getObservationTimeSeries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSystemConnectionStatusListener method, of class SystemConnection.
     */
    @Test
    public void testAddSystemConnectionStatusListener() {
        System.out.println("addSystemConnectionStatusListener");
        SystemConnectionStatusListener listener = null;
        SystemConnection instance = null;
        instance.addSystemConnectionStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSystemConnectionStatusListener method, of class SystemConnection.
     */
    @Test
    public void testRemoveSystemConnectionStatusListener() {
        System.out.println("removeSystemConnectionStatusListener");
        SystemConnectionStatusListener listener = null;
        SystemConnection instance = null;
        instance.removeSystemConnectionStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireSystemConnectionStatusChangedEvent method, of class SystemConnection.
     */
    @Test
    public void testFireSystemConnectionStatusChangedEvent() {
        System.out.println("fireSystemConnectionStatusChangedEvent");
        SystemConnectionStatusChangedEvent evt = null;
        SystemConnection instance = null;
        instance.fireSystemConnectionStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class SystemConnection.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        SystemConnection instance = null;
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectionStatus method, of class SystemConnection.
     */
    @Test
    public void testGetConnectionStatus() {
        System.out.println("getConnectionStatus");
        SystemConnection instance = null;
        SystemConnectionStatus expResult = null;
        SystemConnectionStatus result = instance.getConnectionStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class SystemConnection.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        SystemConnection expResult = null;
        SystemConnection result = SystemConnection.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SystemConnectionImpl extends SystemConnection {

        public SystemConnectionImpl() {
            super("");
        }

        public void iConnect() throws SystemConnectionFailedException {
        }

        public void iDisconnect() {
        }

        public TimeSeries<Observation> getObservationTimeSeries() {
            return null;
        }
    }

}