package org.bham.aucom.models.probability;

import org.junit.Before;
import org.junit.Test;
   import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
public class LogarithmicBinCalculatorTest {
	int num = 129;
	LogarithmicBinCalculator calculator;
	@Before
	public void setUp() throws Exception {
		this.calculator = new LogarithmicBinCalculator(1.5);
	}

	@Test
	public void testCalculateBin() {
		for(double d=0;d<=num;d=d+0.1){
			System.out.println( this.calculator.calculateBin(d)+ " " + "[" + this.calculator.getLowBoundary(d)+ ", " + d+ ", " + this.calculator.getHighBoundary(d)  + "]");
		}
	}

	@Test
	public void testGetHighBoundary() {
	}

	@Test
	public void testGetLowBoundary() {
	}

    /**
     * Test of log method, of class LogarithmicBinCalculator.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        double value = 0.0;
        LogarithmicBinCalculator instance = null;
        double expResult = 0.0;
        double result = instance.log(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
