/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.visualizer;

import net.sf.xcf.fts.Triple;
import org.bham.aucom.data.SystemFaultStatus;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author jxv911
 */
public class CropTimestampFromtripleTest {

    public CropTimestampFromtripleTest() {
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
     * Test of transform method, of class CropTimestampFromtriple.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        Triple<Long, Double, SystemFaultStatus> input = null;
        CropTimestampFromtriple instance = new CropTimestampFromtriple();
        Triple expResult = null;
        Triple result = instance.transform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}