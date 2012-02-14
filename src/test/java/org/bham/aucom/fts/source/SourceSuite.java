/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.source;

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
@Suite.SuiteClasses({org.bham.aucom.fts.source.SourceStatusEventTest.class,org.bham.aucom.fts.source.SourceEventListenerTest.class,org.bham.aucom.fts.source.SourceEventTest.class,org.bham.aucom.fts.source.TimeSeriesSourceTest.class,org.bham.aucom.fts.source.AucomSourceAdapterTest.class,org.bham.aucom.fts.source.ActionFailedExceptionTest.class,org.bham.aucom.fts.source.SourceStatusListenerTest.class,org.bham.aucom.fts.source.IllegalStateChangeTest.class,org.bham.aucom.fts.source.SourceStatusTest.class,org.bham.aucom.fts.source.DummyScoreSourceTest.class})
public class SourceSuite {

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