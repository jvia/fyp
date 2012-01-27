package org.bham.aucom.models.probability;

import org.junit.Before;
import org.junit.Test;

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

}
