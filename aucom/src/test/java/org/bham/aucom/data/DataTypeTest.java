package org.bham.aucom.data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class DataTypeTest {
    @Before
    public void setup(){
    }
    @Test
    public void testToString() throws NullPointerException
    {
        DataType dtp;
        /*
         * default constructor
         */
        dtp = new DataType();
        dtp.toString();
        /*
         * copy constructor
         */
        dtp = new DataType(DataType.createRandomDataType());
        dtp.toString();
        /*
         * seperate fields constructor
         */
        dtp = new DataType(new ArrayList<DomainFeature>(), 1, Observation.createRandomObservation());
        dtp.toString();

        /*
         * seperate fields constructor with null feature list
         */
        dtp = new DataType(null, 1, Observation.createRandomObservation());
        dtp.toString();
    }

    /**
     * Test of getEventType method, of class DataType.
     */
    @Test
    public void testGetEventType() {
        System.out.println("getEventType");
        DataType instance = new DataType();
        int expResult = 0;
        int result = instance.getEventType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventTypeIdAsString method, of class DataType.
     */
    @Test
    public void testGetEventTypeIdAsString() {
        System.out.println("getEventTypeIdAsString");
        DataType instance = new DataType();
        String expResult = "";
        String result = instance.getEventTypeIdAsString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEventTypeId method, of class DataType.
     */
    @Test
    public void testSetEventTypeId() {
        System.out.println("setEventTypeId");
        int eventTypeId = 0;
        DataType instance = new DataType();
        instance.setEventTypeId(eventTypeId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DataType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DataType instance = new DataType();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFeatures method, of class DataType.
     */
    @Test
    public void testSetFeatures() {
        System.out.println("setFeatures");
        List<DomainFeature> features = null;
        DataType instance = new DataType();
        instance.setFeatures(features);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeatures method, of class DataType.
     */
    @Test
    public void testGetFeatures() {
        System.out.println("getFeatures");
        DataType instance = new DataType();
        List expResult = null;
        List result = instance.getFeatures();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomDataType method, of class DataType.
     */
    @Test
    public void testCreateRandomDataType() {
        System.out.println("createRandomDataType");
        DataType expResult = null;
        DataType result = DataType.createRandomDataType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class DataType.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        DataType instance = new DataType();
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
