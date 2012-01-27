package org.bham.aucom.test;

import java.io.Serializable;
import java.util.UUID;

import junit.framework.Assert;


public class JavaTest implements Serializable{
	private static final long serialVersionUID = 0L;
	Double first = null;
	Long second = null;
	UUID id = null;
	public JavaTest(Double first, Long second, UUID id) {
		this.first = first;
		this.second= second;
		this.id = id;
	}
	public JavaTest() {
		
	}
	@Override
	public String toString() {
		return "first " + this.first + " second " + this.second + " id " + id;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double valOne = new Double(1.0d);
		Double valTwo = valOne;
		Assert.assertSame(valOne, valTwo);
		valTwo = new Double(1.0d);
		Assert.assertNotSame(valOne, valTwo);
	}

}
