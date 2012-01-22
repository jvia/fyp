package org.bham.aucom.diagnoser.t2gram;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bham.aucom.data.AttributableObject;



public abstract class  ProbabilityFactory implements AttributableObject, Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;


    public abstract ProbabilityDistribution create(double[] trainingValues);
	public abstract ProbabilityDistribution create();
	
	LinkedHashMap<String, Number> parameters = new LinkedHashMap<String, Number>();


	@Override
	public HashMap<String, String> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addAttribute(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAttribute(String propertyName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAttributeValue(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAttribute(String propertyName) {
		// TODO Auto-generated method stub
		return false;
	}

}
