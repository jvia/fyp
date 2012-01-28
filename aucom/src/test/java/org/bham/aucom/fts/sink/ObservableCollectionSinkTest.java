/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

import org.junit.*;

import java.util.Collection;
import java.util.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class ObservableCollectionSinkTest {

    public ObservableCollectionSinkTest() {
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
     * Test of pushItem method, of class ObservableCollectionSink.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
//        Object arg = null;
//        ObservableCollectionSink instance = null;
//        instance.pushItem(arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObservable method, of class ObservableCollectionSink.
     */
    @Test
    public void testGetObservable() {
        System.out.println("getObservable");
        ObservableCollectionSink instance = null;
        Observable expResult = null;
        Observable result = instance.getObservable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class ObservableCollectionSink.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
//        Collection<T> data = null;
//        ObservableCollectionSink instance = null;
//        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class ObservableCollectionSink.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        ObservableCollectionSink instance = null;
        Collection expResult = null;
        Collection result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}