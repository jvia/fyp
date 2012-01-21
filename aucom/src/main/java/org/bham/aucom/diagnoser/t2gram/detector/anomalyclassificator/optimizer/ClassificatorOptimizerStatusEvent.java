package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import java.util.EventObject;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;


public class ClassificatorOptimizerStatusEvent  extends EventObject  {

	private static final long serialVersionUID = 1L;
	private AnomalyClassificator tested;
	private AnomalyClassificator best;
	private double fpr;
	private double distanceToMean;
	private int number;
	private int total;
	private boolean finished;
	public ClassificatorOptimizerStatusEvent(Object source, AnomalyClassificator inTested, AnomalyClassificator inBest, double inFalsePositive, double inDistanceToMean, int inNumber, int inTotal) {
		super(source);
		this.tested = inTested;
		this.best = inBest;
		this.fpr = inFalsePositive;
		this.distanceToMean = inDistanceToMean;
		this.setNumber(inNumber);
		this.setTotal(inTotal);
	}
	public AnomalyClassificator getTestedClassificator(){
		return this.tested;
	}
	public AnomalyClassificator getBestClassificator(){
		return this.best;
	}
	public double getFalsePositiveRate(){
		return this.fpr;
	}
	public double getDistanceToMean(){
		return this.distanceToMean;
	}
	protected void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	protected void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void markAsFinished(){
		finished = true;
	}
	public boolean isFinished(){
		return finished;
	}
	@Override
	public String toString() {
		return tested + ": " + "fpr " + fpr + " dtm " + distanceToMean+ " " +  number + "/" + total;
	}
}

