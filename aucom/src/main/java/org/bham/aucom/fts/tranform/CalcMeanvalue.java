package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.util.SlidingWindow;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CalcMeanvalue extends AbstractAucomTranformNode<Score, Score> {
	ArrayList<Score> tmp;
	private SlidingWindow slidingWindow;
	protected long tmpFirstTimestampOfTheWindow;
	protected long tmpLastTimestampOfTheWindow;

	public CalcMeanvalue() {
		super("CalcMeanValue");
		this.tmp = new ArrayList<Score>();
	}

	public void reset() {
		Logger.getLogger(this.getClass().getCanonicalName()).info("reseting CalcMean");
		this.tmp.clear();
		this.tmpFirstTimestampOfTheWindow = 0l;
		this.tmpLastTimestampOfTheWindow = this.getSlidingWindow().getIntervalSize();
	}

	protected void store(Score dataToStore) {
		this.tmp.add(dataToStore);
	}

//	public void storeAbstractData(Collection<Score> dataToStore) {
//		this.tmp.addAll(dataToStore);
//	}

	protected RangeScore calcMeanScore(ArrayList<Score> list) {
		if (list.size()==0) {
			return null;
		}
		return new RangeScore(list);
	}

	protected boolean hasReceivedLastSequenceElement(Score element) {
		if (element.isMarkedAsLastElement())
			Logger.getLogger(this.getClass().getCanonicalName()).info("received last item, queue size is " + this.tmp.size());
		Logger.getLogger(this.getClass().getCanonicalName()).info("last element: " + element.isMarkedAsLastElement());
		return element.isMarkedAsLastElement();
	}

	@Override
	protected Score iTransform(Score arg0) throws Exception {
		Logger.getLogger(this.getClass().getCanonicalName()).info("calcmenavalue iTransform " +getSlidingWindow());
		Score meanScore = null;
			store(arg0);
			if (shouldCalculateNewSlidingWindow(arg0)) {
				Logger.getLogger(this.getClass().getCanonicalName()).info("calculating new window");
				ArrayList<Score> windowElements = getWindowElements(this.tmp, this.tmpLastTimestampOfTheWindow);
				ArrayList<Score> elementsToRemove = getElementsToRemove(this.tmp, this.tmpLastTimestampOfTheWindow);
				
				this.tmpLastTimestampOfTheWindow = getNextLastTimestampOfTheWindow();

				meanScore = calcMeanScore(windowElements);
				if (meanScore == null) {
					Logger.getLogger(this.getClass().getCanonicalName()).info("not enough elements for meanScore, returning null");
					return null;
				}
				if (hasReceivedLastSequenceElement(arg0)) {
					meanScore.getAttributes().put("lastElement", "yes");
					Logger.getLogger(this.getClass().getCanonicalName()).info("got last element, marking the output too");
				}

				this.tmp.removeAll(elementsToRemove);
				elementsToRemove.clear();
			} else {
				checkIfLastTimestampOfTheWindowIsValid();
			}
		return meanScore;
	}

	/**
	 * @param arg0
	 * @return
	 */
	private boolean shouldCalculateNewSlidingWindow(Score arg0) {
		return hasReceivedLastSequenceElement(arg0) || hasEnoughElementsForNextSlidingWindow();
	}

	protected ArrayList<Score> getElementsToRemove(ArrayList<Score> tmp2, long tmpLastTimestampOfTheWindow2) {
		ArrayList<Score> out = new ArrayList<Score>();
		try {
			for (Score s : tmp2) {
				if (s.getTimestamp() >= tmpLastTimestampOfTheWindow2 - this.getSlidingWindow().getIntervalOverlapSize())
					return out;
				out.add(s);
			}
		} catch (Exception e) {
			System.out.println("this is " + this);
		}
		return new ArrayList<Score>();
	}

	protected void checkIfLastTimestampOfTheWindowIsValid() {
		if (this.tmp.size() != 0 && this.tmp.get(0).getTimestamp() > this.tmpLastTimestampOfTheWindow) {
			Logger.getLogger(this.getClass().getCanonicalName()).info("lastTimeStamp is not valid");
			this.tmpLastTimestampOfTheWindow = this.tmp.get(0).getTimestamp();
		}
	}

	protected long getNextLastTimestampOfTheWindow() {
		long next = this.tmpLastTimestampOfTheWindow + this.getSlidingWindow().getIntervalSize() - this.getSlidingWindow().getIntervalOverlapSize();
		if (this.tmp.size() != 0 && this.tmp.get(0).getTimestamp() > next) {
			next = this.tmp.get(0).getTimestamp();
		}
		return next;
	}

	protected ArrayList<Score> getWindowElements(ArrayList<Score> tmp2, long tmpLastTimestampOfTheWindow2) {
		ArrayList<Score> out = new ArrayList<Score>();
		if(hasReceivedLastSequenceElement(this.tmp.get(this.tmp.size()-1))){
			out.addAll(this.tmp);
			return out;
		}
		try {
			for (Score s : tmp2) {
				if (s.getTimestamp() >= tmpLastTimestampOfTheWindow2)
					return out;
				out.add(s);
			}
		} catch (Exception e) {
			System.out.println("ConcurrentModificationException catched in getWindowElements");
		}
		return out;
	}

	protected long getOldestTimestamp() {
		return (this.tmp.size() > 0) ? this.tmp.get(this.tmp.size() - 1).getTimestamp() : this.tmpFirstTimestampOfTheWindow;
	}

	protected boolean hasEnoughElementsForNextSlidingWindow() {
		long oldestTimestamp = getOldestTimestamp();
		boolean isCalculateNextWindow = false;
		if (this.tmpLastTimestampOfTheWindow < oldestTimestamp) {
			isCalculateNextWindow = true;
		}
		Logger.getLogger(this.getClass().getCanonicalName()).info("old:" + oldestTimestamp + " max:" +this.tmpLastTimestampOfTheWindow +" enough:"+isCalculateNextWindow);
		return isCalculateNextWindow;
	}

	protected long getFirstTimestampOfTheWindow() {
		// check whether the next window will be empty, if so skip it
		if (this.tmpFirstTimestampOfTheWindow + this.getSlidingWindow().getIntervalSize() < tmp.get(0).getTimestamp()) {
			this.tmpFirstTimestampOfTheWindow = tmp.get(0).getTimestamp();
			Logger.getLogger(this.getClass().getCanonicalName()).info("first timestamp of the windows and the window are smaller than timestamp of the first element in tmp" + this.tmpFirstTimestampOfTheWindow + this.getSlidingWindow().getIntervalSize()
					+ tmp.get(0).getTimestamp());
		}
		return 0;
	}

	public void setSlidingWindow(SlidingWindow slidingWindow) {
		this.slidingWindow = slidingWindow;
		//Assert.assertTrue("inSize" + this.getSlidingWindow().getIntervalSize() + " is smaller than overlap " + this.getSlidingWindow().getIntervalOverlapSize(), this.getSlidingWindow().getIntervalSize() > this.getSlidingWindow().getIntervalOverlapSize());
		reset();
	}

	public SlidingWindow getSlidingWindow() {
		return this.slidingWindow;
	}
}
