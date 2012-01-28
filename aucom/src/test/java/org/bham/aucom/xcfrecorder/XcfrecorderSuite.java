/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

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
@Suite.SuiteClasses({org.bham.aucom.xcfrecorder.RecorderPanelTest.class,org.bham.aucom.xcfrecorder.RecorderStateTest.class,org.bham.aucom.xcfrecorder.RecorderTest.class,org.bham.aucom.xcfrecorder.CounterTest.class,org.bham.aucom.xcfrecorder.SaveTimeSeriesGraphTest.class,org.bham.aucom.xcfrecorder.RecorderStatusListenerTest.class,org.bham.aucom.xcfrecorder.RecorderStatusChangedEventTest.class})
public class XcfrecorderSuite {

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