/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author jxv911
 */
public class ProbabilityFamiliyFactoryTest {

    public ProbabilityFamiliyFactoryTest() {
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
     * Test of getFactory method, of class ProbabilityFamiliyFactory.
     */
    @Test
    public void testGetFactory() {
        System.out.println("getFactory");
        Double[] parameters = null;
        ProbabilityFamiliyFactory instance = new ProbabilityFamiliyFactoryImpl();
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getFactory(parameters);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputPanel method, of class ProbabilityFamiliyFactory.
     */
    @Test
    public void testGetInputPanel() {
        System.out.println("getInputPanel");
        ProbabilityFamiliyFactory instance = new ProbabilityFamiliyFactoryImpl();
        ProbabilityInputPanel expResult = null;
        ProbabilityInputPanel result = instance.getInputPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProbabilityFamiliyFactoryImpl implements ProbabilityFamiliyFactory<KDEProbabilityFactory, Double> {

        public KDEProbabilityFactory getFactory(Double[] parameters) {
            return null;
        }

        public ProbabilityInputPanel getInputPanel() {
            return null;
        }
    }

}