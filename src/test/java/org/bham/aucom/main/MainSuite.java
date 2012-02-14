/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jxv911
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.bham.aucom.main.ReportTest.class,org.bham.aucom.main.XcfFacesOutBoostDetectorTest.class,org.bham.aucom.main.AnomalyRecognitionTest.class,org.bham.aucom.main.PrintStatisticsTest.class,org.bham.aucom.main.CreateClassIdsFromDataTest.class,org.bham.aucom.main.SystemModelStatusEventTest.class,org.bham.aucom.main.SystemModelStatusTest.class,org.bham.aucom.main.GraphStateChangedEventTest.class,org.bham.aucom.main.BusyWorkerTest.class,org.bham.aucom.main.SystemModelStatusListenerTest.class,org.bham.aucom.main.ModelReportTest.class,org.bham.aucom.main.GraphStatusListenerTest.class,org.bham.aucom.main.TestDataTest.class,org.bham.aucom.main.prepare.PrepareSuite.class})
public class MainSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}