/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

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
@Suite.SuiteClasses({org.bham.aucom.data.io.EncoderIOTest.class,org.bham.aucom.data.io.TimeSeriesIOTest.class,org.bham.aucom.data.io.csv.CsvSuite.class,org.bham.aucom.data.io.ClassificatorIOTest.class,org.bham.aucom.data.io.TimeSeriesInputTest.class,org.bham.aucom.data.io.IllegalOutputTypeTest.class,org.bham.aucom.data.io.SlindingWindowIOTest.class,org.bham.aucom.data.io.IOInterfaceTest.class,org.bham.aucom.data.io.TimeSeriesOutputTest.class,org.bham.aucom.data.io.xml.XmlSuite.class,org.bham.aucom.data.io.BinaryIOTest.class,org.bham.aucom.data.io.AucomIOTest.class,org.bham.aucom.data.io.ModelIOTest.class,org.bham.aucom.data.io.OutputFactoryTest.class})
public class IoSuite {

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