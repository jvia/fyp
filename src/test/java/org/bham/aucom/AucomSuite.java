/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom;

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
@Suite.SuiteClasses({org.bham.aucom.diagnoser.DiagnoserSuite.class,org.bham.aucom.models.ModelsSuite.class,org.bham.aucom.main.MainSuite.class,org.bham.aucom.PresentableTest.class,org.bham.aucom.fts.FtsSuite.class,org.bham.aucom.data.DataSuite.class,org.bham.aucom.system.SystemSuite.class,org.bham.aucom.xcfrecorder.XcfrecorderSuite.class,org.bham.aucom.ActionNotPermittedExceptionTest.class,org.bham.aucom.gui.GuiSuite.class,org.bham.aucom.ConfigurationTest.class,org.bham.aucom.util.UtilSuite.class})
public class AucomSuite {

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