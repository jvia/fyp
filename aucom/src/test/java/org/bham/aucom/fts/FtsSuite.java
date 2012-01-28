/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts;

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
@Suite.SuiteClasses({org.bham.aucom.fts.AbstractLinkableNodeTest.class,org.bham.aucom.fts.tranform.TranformSuite.class,org.bham.aucom.fts.select.SelectSuite.class,org.bham.aucom.fts.sink.SinkSuite.class,org.bham.aucom.fts.source.SourceSuite.class,org.bham.aucom.fts.graph.GraphSuite.class,org.bham.aucom.fts.filter.FilterSuite.class})
public class FtsSuite {

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