package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class StatisticalAnomalyClassificatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAproximatelyDuplicateThreadSafety() {
		final StatisticalAnomalyClassificator templateClassificatior = new StatisticalAnomalyClassificator(0.1, 0.002);
		try {
			
		ScheduledExecutorService service = Executors.newScheduledThreadPool(100);
		for(int i= 0 ; i< 50; i++){
			System.out.println("duplicator " + i );
			service.scheduleAtFixedRate(new Runnable() {
				public void run() {
					while(true){
						System.out.println("duplicating");
						StatisticalAnomalyClassificator duplicatedClassificatior  = (StatisticalAnomalyClassificator) templateClassificatior.duplicate();
					}
				}
			}, (int)(10*Math.random()), (int)(20 + 20*Math.random()), TimeUnit.MILLISECONDS);
		}
		Thread.sleep(10000);
		service.shutdownNow();
		} catch (Exception exception) {
			Assert.fail("exception occurred " + exception.getLocalizedMessage());
		}
	}

}
