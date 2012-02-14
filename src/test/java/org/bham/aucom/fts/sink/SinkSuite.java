/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

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
@Suite.SuiteClasses({org.bham.aucom.fts.sink.AucomSinkStatusEventTest.class,org.bham.aucom.fts.sink.ToStringSinkTest.class,org.bham.aucom.fts.sink.AucomSinkAdapterTest.class,org.bham.aucom.fts.sink.SingleDataSinkTest.class,org.bham.aucom.fts.sink.TimeSeriesSinkTest.class,org.bham.aucom.fts.sink.DummySinkTest.class,org.bham.aucom.fts.sink.ObservableStreamSinkTest.class,org.bham.aucom.fts.sink.ObservableCollectionSinkTest.class,org.bham.aucom.fts.sink.NodeStatusTest.class,org.bham.aucom.fts.sink.OutputStreamSinkStripTest.class,org.bham.aucom.fts.sink.SaveT2GramDataTest.class,org.bham.aucom.fts.sink.OutputStreamSinkOverridingTest.class,org.bham.aucom.fts.sink.SinkStatusListenerTest.class})
public class SinkSuite {

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