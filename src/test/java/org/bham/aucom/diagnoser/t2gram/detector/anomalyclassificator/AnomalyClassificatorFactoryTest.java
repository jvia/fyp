/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.junit.*;

import javax.swing.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 *
 * @author jxv911
 */
public class AnomalyClassificatorFactoryTest {

    public AnomalyClassificatorFactoryTest() {
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
     * Test of add method, of class AnomalyClassificatorFactory.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String name = "";
        Class<? extends AnomalyClassificator> adClass = null;
        Class<? extends AnomalyConfigurator> cfgClass = null;
        Class<? extends AnomalyConfiguratorPanel> cfgPanelClass = null;
        AnomalyClassificatorFactory instance = new AnomalyClassificatorFactory();
        instance.add(name, adClass, cfgClass, cfgPanelClass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class AnomalyClassificatorFactory.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        String acName = "";
        AnomalyClassificatorFactory instance = new AnomalyClassificatorFactory();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.create(acName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigurator method, of class AnomalyClassificatorFactory.
     */
    @Test
    public void testGetConfigurator() throws Exception {
        System.out.println("getConfigurator");
        AnomalyClassificator ac = null;
        AnomalyClassificatorFactory instance = new AnomalyClassificatorFactory();
        AnomalyConfigurator expResult = null;
        AnomalyConfigurator result = instance.getConfigurator(ac);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfiguratorPanel method, of class AnomalyClassificatorFactory.
     */
    @Test
    public void testGetConfiguratorPanel() throws Exception {
        System.out.println("getConfiguratorPanel");
        AnomalyConfigurator aCfg = null;
        AnomalyClassificatorFactory instance = new AnomalyClassificatorFactory();
        JPanel expResult = null;
        JPanel result = instance.getConfiguratorPanel(aCfg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegisteredACNames method, of class AnomalyClassificatorFactory.
     */
    @Test
    public void testGetRegisteredACNames() {
        System.out.println("getRegisteredACNames");
        AnomalyClassificatorFactory instance = new AnomalyClassificatorFactory();
        String[] expResult = null;
        String[] result = instance.getRegisteredACNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}