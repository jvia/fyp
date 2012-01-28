/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

import java.io.File;
import java.util.LinkedList;
import nu.xom.Element;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
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
public class ExperimentFactoryTest {

    public ExperimentFactoryTest() {
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
     * Test of createExperiment method, of class ExperimentFactory.
     */
    @Test
    public void testCreateExperiment() throws Exception {
        System.out.println("createExperiment");
        Element experimentDescription = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Experiment expResult = null;
        Experiment result = instance.createExperiment(experimentDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFiles method, of class ExperimentFactory.
     */
    @Test
    public void testLoadFiles() throws Exception {
        System.out.println("loadFiles");
        LinkedList<String> trainingFiles = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        LinkedList expResult = null;
        LinkedList result = instance.loadFiles(trainingFiles);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWorkingDirectoryIfPresentInExperimentDescription method, of class ExperimentFactory.
     */
    @Test
    public void testSetWorkingDirectoryIfPresentInExperimentDescription() {
        System.out.println("setWorkingDirectoryIfPresentInExperimentDescription");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        instance.setWorkingDirectoryIfPresentInExperimentDescription(xmlElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntervalSizeFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetIntervalSizeFromElement() {
        System.out.println("getIntervalSizeFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        int expResult = 0;
        int result = instance.getIntervalSizeFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntervalOverlapSize method, of class ExperimentFactory.
     */
    @Test
    public void testGetIntervalOverlapSize() {
        System.out.println("getIntervalOverlapSize");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        int expResult = 0;
        int result = instance.getIntervalOverlapSize(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValueFromExperimentElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetAttributeValueFromExperimentElement() throws Exception {
        System.out.println("getAttributeValueFromExperimentElement");
        Element xmlElement = null;
        String attributeKey = "";
        ExperimentFactory instance = new ExperimentFactoryImpl();
        String expResult = "";
        String result = instance.getAttributeValueFromExperimentElement(xmlElement, attributeKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExperimentNameFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetExperimentNameFromElement() throws Exception {
        System.out.println("getExperimentNameFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        String expResult = "";
        String result = instance.getExperimentNameFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExperimentType method, of class ExperimentFactory.
     */
    @Test
    public void testGetExperimentType() throws Exception {
        System.out.println("getExperimentType");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        String expResult = "";
        String result = instance.getExperimentType(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThresholdFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetThresholdFromElement() {
        System.out.println("getThresholdFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getThresholdFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidationFilesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetValidationFilesFromElement() {
        System.out.println("getValidationFilesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        LinkedList expResult = null;
        LinkedList result = instance.getValidationFilesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrainingFilesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetTrainingFilesFromElement() {
        System.out.println("getTrainingFilesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        LinkedList expResult = null;
        LinkedList result = instance.getTrainingFilesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestFilesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetTestFilesFromElement() {
        System.out.println("getTestFilesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        LinkedList expResult = null;
        LinkedList result = instance.getTestFilesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorkingDirectoryFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetWorkingDirectoryFromElement() {
        System.out.println("getWorkingDirectoryFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        String expResult = "";
        String result = instance.getWorkingDirectoryFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilityFactoryFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetProbabilityFactoryFromElement() {
        System.out.println("getProbabilityFactoryFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getProbabilityFactoryFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameterValuesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetParameterValuesFromElement() {
        System.out.println("getParameterValuesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Object[] expResult = null;
        Object[] result = instance.getParameterValuesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameterClassesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetParameterClassesFromElement() {
        System.out.println("getParameterClassesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Class[] expResult = null;
        Class[] result = instance.getParameterClassesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameterClassFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetParameterClassFromElement() {
        System.out.println("getParameterClassFromElement");
        Element result_2 = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Class expResult = null;
        Class result = instance.getParameterClassFromElement(result_2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameterValueFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetParameterValueFromElement() {
        System.out.println("getParameterValueFromElement");
        Element result_2 = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Object expResult = null;
        Object result = instance.getParameterValueFromElement(result_2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilityFactoryClassFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetProbabilityFactoryClassFromElement() {
        System.out.println("getProbabilityFactoryClassFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Class expResult = null;
        Class result = instance.getProbabilityFactoryClassFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThresholdClassFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetThresholdClassFromElement() {
        System.out.println("getThresholdClassFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        Class expResult = null;
        Class result = instance.getThresholdClassFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModelFileFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetModelFileFromElement() {
        System.out.println("getModelFileFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        File expResult = null;
        File result = instance.getModelFileFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateFile method, of class ExperimentFactory.
     */
    @Test
    public void testValidateFile() {
        System.out.println("validateFile");
        File trainingFile = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        boolean expResult = false;
        boolean result = instance.validateFile(trainingFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOptimizeFilesFromElement method, of class ExperimentFactory.
     */
    @Test
    public void testGetOptimizeFilesFromElement() {
        System.out.println("getOptimizeFilesFromElement");
        Element xmlElement = null;
        ExperimentFactory instance = new ExperimentFactoryImpl();
        LinkedList expResult = null;
        LinkedList result = instance.getOptimizeFilesFromElement(xmlElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ExperimentFactoryImpl extends ExperimentFactory {

        public Experiment createExperiment(Element experimentDescription) throws Exception {
            return null;
        }
    }

}