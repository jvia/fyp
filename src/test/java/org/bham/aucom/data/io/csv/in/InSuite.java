/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.csv.in;

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
@Suite.SuiteClasses({org.bham.aucom.data.io.csv.in.CSVTimeSeriesInputTest.class,org.bham.aucom.data.io.csv.in.converter.ConverterSuite.class})
public class InSuite {

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