package org.bham.aucom.fts.tranform;

import junit.framework.Assert;
import org.bham.aucom.data.AbstractData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;

public class PauseNodeTest {
	PauseNode<Observation> pause;
	Thread testThread;
	Observation observation = null;

	@Before
	public void setUp() throws Exception {
		pause = new PauseNode<Observation>();
	}

	@After
	public void cleanUp() {
		if (testThread != null) {
			testThread.interrupt();
		}
		observation = null;
	}

	@Test
	public void testPause() {
		try {
			// call iTransform on an own thread
			testThread = new Thread((new Runnable() {
				public void run() {
					try {
						observation = pause.iTransform(Observation.createRandomObservation());
					} catch (Exception exception) {
						Assert.fail();
					}
				}
			}));
			testThread.start();
			Thread.sleep(200);
			Assert.assertNotNull(observation);
		} catch (Exception exception) {
			Assert.fail();
		}
	}

	@Test
	public void testResume() {
		try {
			// call itransform on an own thread
			pause.pause();
			testThread = new Thread((new Runnable() {
				public void run() {
					try {
						observation = pause.iTransform(Observation.createRandomObservation());
					} catch (Exception exception) {
						Assert.fail();
					}
				}
			}));
			testThread.start();
			Thread.sleep(300);
			Assert.assertNull(observation);
			testThread.interrupt();
			pause.resume();
			testThread = new Thread((new Runnable() {
				public void run() {
					try {
						observation = pause.iTransform(Observation.createRandomObservation());
					} catch (Exception exception) {
						Assert.fail();
					}
				}
			}));
			testThread.start();
			Thread.sleep(300);
			Assert.assertNotNull(observation);
		} catch (Exception exception) {
			Assert.fail();
		}
	}

    /**
     * Test of iTransform method, of class PauseNode.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        PauseNode instance = new PauseNode();
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPaused method, of class PauseNode.
     */
    @Test
    public void testIsPaused() {
        System.out.println("isPaused");
        PauseNode instance = new PauseNode();
        boolean expResult = false;
        boolean result = instance.isPaused();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
