package org.bham.aucom.data;

import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;
import org.bham.aucom.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/*
 * This class provides descriptive statistics for a time-series. Statistics can
 * be computed for three different parts of the time-series: a) the whole
 * time-series, b) the part before the first fault was induced (called head) c)
 * the part after the first fault was induced (called tail) The information
 * about a fault need to be provided as an attribute {@linkplain Constants.} in
 * the time-series object given to this class.
 * 
 * @author rgolombe <rgolombe@cor-lab.uni-bielefeld.de>
 * 
 */
public class ClassificationTimeSeriesDescriptiveStatistics {
	private TimeSeries<Classification> classificationTimeSeries;

	/*
	 * Creates a ready to go ClassficationTimeSeriesStatistics object
	 * 
	 * @input scoretimeSeries the time-series for which statistics will be
	 *        generated. null is not permitted
	 */
	public ClassificationTimeSeriesDescriptiveStatistics(TimeSeries<Classification> scoreTimeSeries) {
		setTimeSeries(scoreTimeSeries);
		if (classificationTimeSeries.size() == 0) {
			Logger.getLogger(this.getClass().getCanonicalName()).severe(this.getClass() + " warning timeseries has length 0");
		}
		if(hasNotInducedFaultTimestamp()){
			Logger.getLogger(this.getClass().getCanonicalName()).severe(this.getClass() + " timeseries has no fault induces timestamp attribute");
		}
	}

	/*
	 * Default constructor
	 */
	public ClassificationTimeSeriesDescriptiveStatistics() {
		// empty
	}

	/*
	 * Returns the duration of the time-series i.e., the difference between the
	 * first time-stamp and last time-stamp of this time-series
	 * 
	 * @return
	 */
	public long getDuration() {
		long lastTimestamp = this.classificationTimeSeries.get(this.classificationTimeSeries.size() - 1).getTimestamp();
		long firstTimestamp = this.classificationTimeSeries.get(0).getTimestamp();
		return lastTimestamp- firstTimestamp;
	}


	/*
	 * Returns the duration of the head of the time-series i.e., the difference
	 * between the first time-stamp of the time-series and last time-stamp
	 * before the fault induced time-stamp given by {@link #getFaultTimestamp()}
	 * If {@link #hasInducedFaultTimestamp()} returns false this function returns
	 * the same value as {@link #getDuration()}.
	 * 
	 * @return
	 */
	public long getHeadDuration() {
		long lastHeadTimestamp = getLastTimestampBeforeFault();
		long firstHeadTimestamp = this.classificationTimeSeries.get(0).getTimestamp();
		return lastHeadTimestamp - firstHeadTimestamp;
	}

	/*
	 * Returns the time-stamp of the last element in the time-series before the
	 * fault induced time-stamp. If {@link #hasInducedFaultTimestamp()} returns
	 * false this function returns the time-stamp of the last element in this
	 * time-series.
	 * 
	 * @return time-stamp of the last element of the time-series before fault in milliseconds
	 */
	private long getLastTimestampBeforeFault() {
		List<Classification> list = getHead();
		return list.get(list.size() - 1).getTimestamp();
	}

	/*
	 * Returns the duration of the tail of the time-series i.e., the difference
	 * between the first time-stamp of the tail as returned by {@link #getTail()}
	 * and last time-stamp of the time-series. If {@link #hasInducedFaultTimestamp()} 
	 * returns false this function returns 0.
	 * 
	 * @return tail duration in milliseconds
	 */
	public long getTailDuration() {
		long lastTailTimestamp = this.classificationTimeSeries.get(this.classificationTimeSeries.size() - 1).getTimestamp();
		long firstTailTimestamp = getFirstTimestampAfterFault();
		if(firstTailTimestamp == Long.MAX_VALUE){
			return  0;
		}
		return lastTailTimestamp - firstTailTimestamp ;
	}

	/*
	 * Returns the time-stamp of the first element in the time-series after the
	 * fault induced time-stamp as returned by {@link getFaultTimestamp()}. In
	 * case {@link #hasInducedFaultTimestamp()} returns false this function returns long.MAX_VALUE.
	 * 
	 * @return
	 */
	private long getFirstTimestampAfterFault() {
		List<Classification> tail = getTail();
		if (tail.size() == 0) {
			
			return Long.MAX_VALUE;
		}
		return tail.get(0).getTimestamp();
	}

	/*
	 * Returns the total number of elements of the whole time-series which were
	 * marked as faulty. Ignores a potential induced-fault time-stamp.
	 * 
	 * @return total number of faults in the time-series
	 */
    int getAnomalyValueCount() {
		TimeSeries<Classification> list = getTimeSeries();
		int numberFaults = 0;
		synchronized (list) {
			for (int i = 0; i < list.size(); i++) {
				Classification s = list.get(i);
				numberFaults += s.getStatusAsDouble();

			}
		}
		return numberFaults;
	}

	/*
	 * Returns the total number of elements of the head part of the current
	 * time-series which were marked as faulty. If
	 * {@link timestamp_faultInduced} is Long.MAX_VALUE then this function is
	 * identical to {@link getAnomalyValueCount()}
	 * 
	 * @return total number of faults in the time-series.
	 */
	public int getHeadAnomalyValueCount() {
		int numberFaults = 0;
		for (int i = 0; i < getHead().size(); i++) {
			Classification s = getHead().get(i);
			numberFaults += s.getStatusAsDouble();

		}
		return numberFaults;
	}

	/*
	 * Returns the total number of elements of the tail part of the current
	 * time-series which were marked as faulty.
	 * 
	 * @return total number of faults in the time-series. 0 if tail is of length
	 *         zero
	 */
	public int getTailAnomalyValueCount() {
		int numberFaults = 0;
		for (int i = 0; i < getTail().size(); i++) {
			Classification s = getTail().get(i);
			numberFaults += s.getStatusAsDouble();

		}
		return numberFaults;
	}

	/*
	 * Returns the percentage of elements of the whole time-series which were
	 * marked as faulty. Ignores a potential induced-fault time-stamp.
	 * 
	 * @return percentage of faults in the time-series
	 */
	public double getAnomalyValuePercent() {
		if (getTimeSeries().size() == 0) {
			return 0.0d;
		}
		return (double) getAnomalyValueCount() / (double) getTimeSeries().size();
	}

	/*
	 * Returns the percentage of elements of the head part of the time-series
	 * which were marked as faulty. Ignores a potential induced-fault
	 * time-stamp.
	 * 
	 * @return percentage of faults in the time-series
	 */
	public double getHeadAnomalyValuePercent() {
		if (getHead().size() == 0) {
			return 0.0d;
		}
		return (double) getHeadAnomalyValueCount() / (double) getHead().size();
	}

	/*
	 * Returns the percentage of elements of the head part of the time-series
	 * which were marked as faulty. Ignores a potential induced-fault
	 * time-stamp.
	 * 
	 * @return percentage of faults in the time-series
	 */
	public double getTailAnomalyValuePercent() {
		if (getTail().size() == 0) {
			return 0.0d;
		}
		return (double) getTailAnomalyValueCount() / (double) getTail().size();
	}

	/**
	 * Returns a boolean value which indicate whether the current time-series
	 * carries along a time-stamp of an induced fault.
	 * 
	 * @return true if the time-series has a time-stamp for an induced fault
	 */
    boolean hasNotInducedFaultTimestamp() {
		return Long.MAX_VALUE == getFautlTimestamp();
	}

	/**
	 * Computes the mean score for the current time-series
	 * 
	 * @return mean score for the time-series
	 */
	public double getTotalMeanScoreValue() {
		double mean = 0.0d;
		if (!getTimeSeries().isEmpty()) {
			for (int i = 0; i < this.getTimeSeries().size(); i++) {
				Score s = this.getTimeSeries().get(i);
				mean += s.getValue();
			}
			mean /= this.getTimeSeries().size();
		}
		return mean;
	}

	/*
	 * Computes the mean score for the head of the current time-series
	 * 
	 * @return mean score for the head part of the current time-series
	 */
	public double getHeadMeanScoreValue() {
		double mean = 0.0d;
		if (!getHead().isEmpty()) {
			for (int i = 0; i < getHead().size(); i++) {
				Score s = getHead().get(i);
				mean += s.getValue();
			}
			mean /= getHead().size();
		}
		return mean;
	}

	/*
	 * Computes the mean score for the tail of the current time-series
	 * 
	 * @return mean score for the tail part of the current time-series
	 */
	public double getTailMeanScoreValue() {
		double mean = 0.0d;
		if (!getTail().isEmpty()) {
			for (int i = 0; i < getTail().size(); i++) {
				Score s = getTail().get(i);
				mean += s.getValue();
			}
			mean /= getTail().size();
		}
		return mean;
	}

	/*
	 * Computes the score variance for the current time-series.
	 * 
	 * @return score variance for the time-series
	 */
	public double getTotalScoreVarianceValue() {
		double variance = 0.0d;
		if (!getTimeSeries().isEmpty()) {
			double mean = getTotalMeanScoreValue();
			for (int i = 0; i < this.getTimeSeries().size(); i++) {
				Score s = this.getTimeSeries().get(i);
				variance += Math.pow(mean - s.getValue(), 2);
			}
			variance /= this.getTimeSeries().size()-1;
		}
		return variance;
	}

	/*
	 * Computes the score variance for the head of the current time-series.
	 * 
	 * @return score variance for head of the current time-series
	 */
	public double getHeadScoreVarianceValue() {
		double variance = 0.0d;
		if (!getHead().isEmpty()) {
			double mean = getHeadMeanScoreValue();
			for (int i = 0; i < getHead().size(); i++) {
				Score s = getHead().get(i);
				variance += Math.pow(mean - s.getValue(), 2);
			}
			variance /= getHead().size()-1;
		}
		return variance;
	}

	/*
	 * Computes the score variance for the tail of the current time-series.
	 * 
	 * @return score variance for tail of the current time-series
	 */
	public double getTailScoreVarianceValue() {
		double variance = 0.0d;
		if (!getTail().isEmpty()) {
			double mean = getTailMeanScoreValue();
			for (int i = 0; i < getTail().size(); i++) {
				Score s = getTail().get(i);
				variance += Math.pow(mean - s.getValue(), 2);
			}
			variance /= getTail().size()-1;
		}
		return variance;
	}
	/*
	 * Computes the positive quadratic distance for the time-series. This means for each element of the classification time-series the difference between the score of the 
	 * element and the threshold used to classify this element into faulty or normal is calculated. 
	 * In case  score<threshold (i.e., element classified as faulty) the difference would be negative and is therefore discarded.  
	 * @return the positive quadratic distance between the scores of this time-series and the corresponding classifications thresholds
	 */
	public double getPositiveQuadraticDistance() {
		double distance = 0.0d;
		synchronized (getTimeSeries()) {
			for (int i = 0; i < this.getTimeSeries().size(); i++) {
				Score s = this.getTimeSeries().get(i);
				double meanValue = Double.valueOf(s.getAttributeValue(StatisticalAnomalyClassifier.THRESHOLD_USED));
				double tmpDiff = Math.max(s.getValue() - meanValue, 0);
				distance += Math.pow(tmpDiff, 2);
			}
		}
		return distance;
	}

	/*
	 * returns the current time-series. may be null
	 * 
	 * @return current time-series
	 */
    TimeSeries<Classification> getTimeSeries() {
		return classificationTimeSeries;
	}

	/*
	 * Sets the time-series which will be examined.
	 * 
	 * @param classificationTimeSeries
	 */
	public void setTimeSeries(TimeSeries<Classification> classificationTimeSeries) {
		this.classificationTimeSeries = classificationTimeSeries;
	}

	/*
	 * Checks whether the current time-series is not null and has an attribute
	 * called {@linkplain Constants.FAULT_INDUCED} and if so returns its value.
	 * Otherwise returns {@link Long.MAX_VALUE}.
	 */
	public long getFautlTimestamp() {
		if (getTimeSeries() != null) {
			if (getTimeSeries().containsAttribute(Constants.FAULT_INDUCED)) {
				return Long.parseLong(getTimeSeries().getAttributeValue(Constants.FAULT_INDUCED));
			}
		}
		return Long.MAX_VALUE;
	}

	/*
	 * Returns the head of the current time-series based on the fault time-stamp
	 * attribute of the time-series. If time-series is null or empty an empty
	 * list is returned. Head consists of all elements with a time-stamp smaller
	 * than the value of the fault time-stamp attribute. If no such attribute is
	 * present a list is returned which contains all elements of the time-series
	 * at current point in time.
	 * 
	 * @return The head of the current time-series or all elements of the
	 *         time-series if no fault time-stamp is present
	 */
	List<Classification> getHead() {
		if (getTimeSeries() == null | getTimeSeries().isEmpty()) {
			return new ArrayList<Classification>();
		}
		List<Classification> head = new ArrayList<Classification>();
		if (hasNotInducedFaultTimestamp()) {
			head.addAll(getTimeSeries().getall());
			return head;
		}
		long faultTimeStamp = getFautlTimestamp();
		for (Classification cl : getTimeSeries().getall()) {
			if (cl.getTimestamp() < faultTimeStamp) {
				head.add(cl);
			} else {
				return head;
			}
		}
		return head;
	}

	/*
	 * Returns the tail of the current time-series based on the fault time-stamp
	 * attribute of the time-series. If time-series is null or empty an empty
	 * list is returned. Tail consists of all elements with a time-stamp equal
	 * or bigger than the value of the fault time-stamp attribute. If no such
	 * attribute is present an empty list is returned.
	 * 
	 * @return The tail of the current time-series or an empty list
	 */

	List<Classification> getTail() {
		if (getTimeSeries() == null | getTimeSeries().isEmpty()) {
			return new ArrayList<Classification>();
		}
		List<Classification> tail = new ArrayList<Classification>();
		if (hasNotInducedFaultTimestamp()) {
			return tail;
		}
		long faultTimeStamp = getFautlTimestamp();
		for (Classification cl : getTimeSeries().getall()) {
			if (cl.getTimestamp() >= faultTimeStamp) {
				tail.add(cl);
			}
		}
		return tail;
	}
}
