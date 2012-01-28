/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

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
@Suite.SuiteClasses({org.bham.aucom.system.SystemConnectionNotFoundExceptionTest.class,org.bham.aucom.system.DummySystemConnectionTest.class,org.bham.aucom.system.SystemConnectionInfoTest.class,org.bham.aucom.system.FactoryManagerInitalizationExceptionTest.class,org.bham.aucom.system.SystemConnectionStatusListenerTest.class,org.bham.aucom.system.DefaultSystemConnectionPanelTest.class,org.bham.aucom.system.SystemConnectionFailedExceptionTest.class,org.bham.aucom.system.SystemConnectionFactoryManagerTest.class,org.bham.aucom.system.SystemConnectionStatusChangedEventTest.class,org.bham.aucom.system.SystemConnectionStatusTest.class,org.bham.aucom.system.SystemConnectionTest.class})
public class SystemSuite {

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