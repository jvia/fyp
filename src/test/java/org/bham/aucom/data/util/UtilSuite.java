/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.util;

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
@Suite.SuiteClasses({org.bham.aucom.data.util.SlidingWindowTest.class,org.bham.aucom.data.util.DataModelTest.class,org.bham.aucom.data.util.DataModelStatusEventTest.class,org.bham.aucom.data.util.DataManagerTest.class,org.bham.aucom.data.util.SequenceListModelTest.class,org.bham.aucom.data.util.IdNotFoundExceptionTest.class,org.bham.aucom.data.util.XmlToJavaObjectConverterTest.class,org.bham.aucom.data.util.DataModelStatusListenerTest.class,org.bham.aucom.data.util.DataManagerPanelTest.class,org.bham.aucom.data.util.DataModelStatusTest.class})
public class UtilSuite {

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