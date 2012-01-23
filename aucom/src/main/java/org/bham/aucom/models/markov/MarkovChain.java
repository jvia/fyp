package org.bham.aucom.models.markov;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tuple;


class MarkovChain {
	private final LinkedHashMap<Integer, Integer> initial;
	private final HashMatrix<Integer, Integer, Integer> transitions;
	int numElements = 0;
	private int lastId = -1;
	public MarkovChain() {
		this.initial= new LinkedHashMap<Integer, Integer>();
		this.transitions = new HashMatrix<Integer, Integer, Integer>();
	}
	private int getNumInitialElements(){
		int out = 0;
		for(Integer classId: initial.keySet()){
			out += initial.get(classId);
		}
		return out;
	}
	private int getNumTransitionElements(int classId){
		int out = 0;
		for(Tuple<Integer, Integer> key: transitions.keySet()){
			if(key.getFirst()==classId)
				out += transitions.get(key.getFirst(), key.getSecond());
		}
		return out;
	}
	public void put(int classId){
		if(!initial.containsKey(classId))
			initial.put(classId, 0);
		if(lastId==-1){
			lastId = classId;
			return;
		}
		initial.put(classId, initial.get(classId)+1);
		if(transitions.doesNotContainsFirstKey(lastId)){
			transitions.put(lastId, classId, 0);
		}
		if(!transitions.containsKey(lastId,classId))
			transitions.put(lastId, classId, 0);
		transitions.put(lastId, classId, transitions.get(lastId, classId)+1);
		lastId = classId;
	}
	double getInitialProbability(int classId){
		if(!initial.containsKey(classId))
			return LOWEST_PROBABILITY;
		return (double)initial.get(classId)/(double)getNumInitialElements();
	}
	double getTransitionProbability(int fromt, int to){
		if(!transitions.containsKey(fromt, to))
			return LOWEST_PROBABILITY;
		return (double)transitions.get(fromt, to)/(double)getNumTransitionElements(fromt);
	}
	public double getProbability(ArrayList<Integer> classIds){
		double probability = 1;
		int last = -1;
		for(Integer classId: classIds){
			if(last == -1){
					probability *= getInitialProbability(classId);
			}else{
					probability *= getTransitionProbability(lastId, classId);
			}
			last = classId;
		}
		return probability;
	}
}
