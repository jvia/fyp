package org.bham.aucom.main;

import java.util.EventObject;

import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;



public class GraphStateChangedEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private final GraphStatus previousState;
	private final GraphStatus newState;
	public GraphStateChangedEvent(Object source, GraphStatus prevGraphState, GraphStatus newState) { 
		super(source);
		this.previousState=prevGraphState;
		this.newState = newState;
		
	}
	public GraphStatus getPreviousState() {
		return previousState;
	}
	public GraphStatus getNewState() {
		return newState;
	} 
	@Override
	public String toString() {
		return "["+((AbstractAucomGraph)source).getGraphName()+","+this.previousState+" --> "+this.newState+"]";
	}
}
