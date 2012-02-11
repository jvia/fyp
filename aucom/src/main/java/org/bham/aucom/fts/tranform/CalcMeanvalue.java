package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.util.SlidingWindow;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.String.format;

public class CalcMeanvalue extends AbstractAucomTranformNode<Score, Score> {
    private final ArrayList<Score> tmp;
    // TODO :: determine if slidingWindow can be removed from class
    private SlidingWindow slidingWindow;
    protected long tmpFirstTimestampOfTheWindow;
    protected long tmpLastTimestampOfTheWindow;
    private transient final Logger log = Logger.getLogger(getClass().getName());

    public CalcMeanvalue() {
        super("CalcMeanValue");
        tmp = new ArrayList<Score>();
    }

    public void reset() {
        log.info("Resetting CalcMean");
        tmp.clear();
        tmpFirstTimestampOfTheWindow = 0l;
        tmpLastTimestampOfTheWindow = getSlidingWindow().getIntervalSize();
    }

    protected void store(Score dataToStore) {
        tmp.add(dataToStore);
    }

    protected RangeScore calcMeanScore(ArrayList<Score> list) {
        if (list.size() == 0) {
            log.fine("Score list is empty. Returning null.");
            return null;
        }
        return new RangeScore(list);
    }

    protected boolean hasReceivedLastSequenceElement(Score element) {
        if (element.isMarkedAsLastElement())
            log.info(format("Received last item. Queue size is %d", tmp.size()));
        return element.isMarkedAsLastElement();
    }

    @Override
    protected Score iTransform(Score arg0) throws Exception {
        log.finest(format("Calling iTransform with sliding window %s", getSlidingWindow()));
        Score meanScore = null;
        store(arg0);

        if (shouldCalculateNewSlidingWindow(arg0)) {
            log.info("Calculating new window");
            ArrayList<Score> windowElements = getWindowElements(tmp, tmpLastTimestampOfTheWindow);
            ArrayList<Score> elementsToRemove = getElementsToRemove(tmp, tmpLastTimestampOfTheWindow);

            tmpLastTimestampOfTheWindow = getNextLastTimestampOfTheWindow();

            meanScore = calcMeanScore(windowElements);
            if (meanScore == null) {
                log.info("Not enough elements for mean score, returning null");
                return null;
            }
            if (hasReceivedLastSequenceElement(arg0)) {
                meanScore.getAttributes().put("lastElement", "yes");
                log.info("got last element, marking the output too");
            }

            tmp.removeAll(elementsToRemove);
            elementsToRemove.clear();
        } else {
            checkIfLastTimestampOfTheWindowIsValid();
        }
        return meanScore;
    }

    private boolean shouldCalculateNewSlidingWindow(Score arg0) {
        return hasReceivedLastSequenceElement(arg0) || hasEnoughElementsForNextSlidingWindow();
    }

    protected ArrayList<Score> getElementsToRemove(ArrayList<Score> tmp2, long tmpLastTimestampOfTheWindow2) {
        ArrayList<Score> out = new ArrayList<Score>();
        try {
            for (Score s : tmp2) {
                if (s.getTimestamp() >= tmpLastTimestampOfTheWindow2 - getSlidingWindow().getIntervalOverlapSize())
                    return out;
                out.add(s);
            }
        } catch (Exception e) {
            System.out.println("this is " + this);
        }
        return new ArrayList<Score>();
    }

    protected void checkIfLastTimestampOfTheWindowIsValid() {
        if (tmp.size() != 0 && tmp.get(0).getTimestamp() > tmpLastTimestampOfTheWindow) {
            log.info("lastTimeStamp is not valid");
            tmpLastTimestampOfTheWindow = tmp.get(0).getTimestamp();
        }
    }

    protected long getNextLastTimestampOfTheWindow() {
        long next = tmpLastTimestampOfTheWindow + getSlidingWindow().getIntervalSize() - getSlidingWindow().getIntervalOverlapSize();
        if (tmp.size() != 0 && tmp.get(0).getTimestamp() > next) {
            next = tmp.get(0).getTimestamp();
        }
        return next;
    }

    protected ArrayList<Score> getWindowElements(ArrayList<Score> tmp2, long tmpLastTimestampOfTheWindow2) {
        ArrayList<Score> out = new ArrayList<Score>();
        if (hasReceivedLastSequenceElement(tmp.get(tmp.size() - 1))) {
            out.addAll(tmp);
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
        return (tmp.size() > 0) ? tmp.get(tmp.size() - 1).getTimestamp() : tmpFirstTimestampOfTheWindow;
    }

    protected boolean hasEnoughElementsForNextSlidingWindow() {
        long oldestTimestamp = getOldestTimestamp();
        boolean isCalculateNextWindow = false;
        if (tmpLastTimestampOfTheWindow < oldestTimestamp) {
            isCalculateNextWindow = true;
        }
        log.info("old:" + oldestTimestamp + " max:" + tmpLastTimestampOfTheWindow + " enough:" + isCalculateNextWindow);
        return isCalculateNextWindow;
    }

    protected long getFirstTimestampOfTheWindow() {
        // check whether the next window will be empty, if so skip it
        if (tmpFirstTimestampOfTheWindow + getSlidingWindow().getIntervalSize() < tmp.get(0).getTimestamp()) {
            tmpFirstTimestampOfTheWindow = tmp.get(0).getTimestamp();
            log.info("first timestamp of the windows and the window are smaller than timestamp of the first element in tmp" + tmpFirstTimestampOfTheWindow + getSlidingWindow().getIntervalSize()
                     + tmp.get(0).getTimestamp());
        }
        return 0;
    }

    public void setSlidingWindow(SlidingWindow slidingWindow) {
        this.slidingWindow = slidingWindow;

        if (!(slidingWindow.getIntervalSize() > slidingWindow.getIntervalOverlapSize())) {
            log.severe(format("Interval size %d is smaller than overalp %d",
                              slidingWindow.getIntervalSize(),
                              slidingWindow.getIntervalOverlapSize()));
        }

        reset();
    }

    public SlidingWindow getSlidingWindow() {
        return slidingWindow;
    }
}
