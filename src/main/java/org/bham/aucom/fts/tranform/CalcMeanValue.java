package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.util.SlidingWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * This node calculates an averaged score by taking into account the score over
 * time.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class CalcMeanValue extends AbstractAucomTranformNode<Score, Score> {

    private static final long serialVersionUID = -5720907688401203759L;

    private SlidingWindow slidingWindow;
    private long windowStart;
    private long windowEnd;

    private transient final List<Score> history;
    private transient final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Construct the node.
     */
    public CalcMeanValue() {
        super("CalcMeanValue");
        history = new ArrayList<Score>();
    }

    /**
     * Transform a raw score value into a modified score value based on the
     * score history.
     *
     * @param score the raw score
     * @return the modified score
     * @throws Exception something went wrong
     */
    @Override
    protected Score iTransform(Score score) throws Exception {
        log.finest(format("Calling iTransform with sliding window %s", getSlidingWindow()));

        history.add(score);
        Score mean = null;

        if (shouldCalculateNewSlidingWindow(score)) {
            log.info("Calculating new window");

            List<Score> windowElements = getWindowElements(history, windowEnd);
            List<Score> elementsToRemove = getElementsToRemove(history, windowEnd);
            windowEnd = getNextWindowEnd();
            mean = calcMeanScore(windowElements);

            if (mean == null) {
                log.info("Not enough elements for mean score, returning null");
                return null;
            }

            if (lastElement(score)) {
                mean.getAttributes().put("lastElement", "yes");
                log.info("got last element, marking the output too");
            }

            history.removeAll(elementsToRemove);
        } else {
            checkIfLastTimestampOfTheWindowIsValid();
        }

        return mean;
    }

    /**
     * Resets all data in the node.
     */
    public void reset() {
        log.info("Resetting CalcMean");
        history.clear();
        windowStart = 0;
        windowEnd = getSlidingWindow().getIntervalSize();
    }

    /**
     * Adds a score value to the history.
     *
     * @param score the score to remember
     */
    protected void store(Score score) {
        history.add(score);
    }

    /**
     * Calculate the mean score.
     *
     * @param scores the list of score
     * @return a range score
     */
    protected RangeScore calcMeanScore(List<Score> scores) {
        if (scores.isEmpty()) {
            log.fine("Score scores is empty. Returning null.");
            return null;
        }
        return new RangeScore(scores);
    }

    /**
     * Determines if this score is the last score in the series.
     *
     * @param score the score
     * @return true if last element
     */
    protected boolean lastElement(Score score) {
        return score.isLastElement();
    }

    /**
     * Determines if a new sliding window should be calculated. This is returns
     * true when the score is the final score or if the sliding window has
     * reached its maximum size.
     *
     * @param score the score
     * @return true if new sliding window needs to be calculated
     */
    private boolean shouldCalculateNewSlidingWindow(Score score) {
        return lastElement(score) || hasEnoughElementsForNextSlidingWindow();
    }

    /**
     * Calculates which elements need to be removed from the sliding window.
     *
     * @param scores    the list of scores
     * @param windowEnd the window end
     * @return the list of elements to remove
     */
    protected List<Score> getElementsToRemove(List<Score> scores, long windowEnd) {
        List<Score> out = new ArrayList<Score>();
        for (Score s : scores) {
            if (s.getTimestamp() >= windowEnd - slidingWindow.getIntervalOverlapSize()) {
                break;
            }
            out.add(s);
        }
        return out;
    }

    /**
     * Check if windowEnd timestamp is valid and updates it if not.
     */
    protected void checkIfLastTimestampOfTheWindowIsValid() {
        if (!history.isEmpty() && history.get(0).getTimestamp() > windowEnd) {
            log.info("lastTimeStamp is not valid");
            windowEnd = history.get(0).getTimestamp();
        }
    }

    /**
     * Calculate the new end of the sliding window
     *
     * @return the new end
     */
    protected long getNextWindowEnd() {
        long next = windowEnd + (slidingWindow.getIntervalSize() - slidingWindow.getIntervalOverlapSize());

        if (!history.isEmpty() && history.get(0).getTimestamp() > next) {
            next = history.get(0).getTimestamp();
        }

        return next;
    }

    /**
     * Gets all of the elements which are within the sliding window.
     *
     * @param history   the score history
     * @param windowEnd the end of the sliding window
     * @return the scores within the sliding window
     */
    protected List<Score> getWindowElements(List<Score> history, long windowEnd) {
        List<Score> out = new ArrayList<Score>();

        // if we have seen the last element, return the whole history
        if (lastElement(history.get(history.size() - 1))) {
            return history;
        }

        // add all scores which fall within the sliding window
        for (Score s : history) {
            if (s.getTimestamp() < windowEnd) {
                out.add(s);
            }
        }

        return out;
    }

    /**
     * Get the oldest timestamp in the sliding window.
     *
     * @return the oldest timestamp
     */
    protected long getOldestTimestamp() {
        if (history.isEmpty()) {
            return windowStart;
        } else {
            return history.get(history.size() - 1).getTimestamp();
        }
    }

    /**
     * Determines if enough elements have come in to move the sliding window.
     *
     * @return true if the window can be slid
     */
    protected boolean hasEnoughElementsForNextSlidingWindow() {
        long oldest = getOldestTimestamp();
        log.fine(format("Oldest: %d, Window end: %d, Enough to slide? %b", oldest, windowEnd, windowEnd < oldest));
        return windowEnd < oldest;
    }

    /**
     * Gets the first time stamp of the sliding window.
     * <p/>
     * TODO :: Seems to be unused & illogical
     *
     * @return 0
     */
    protected long getFirstTimestampOfTheWindow() {
        // check whether the next window will be empty, if so skip it
        if (windowStart + getSlidingWindow().getIntervalSize() < history.get(0).getTimestamp()) {
            windowStart = history.get(0).getTimestamp();
            log.info("first timestamp of the windows and the window are smaller than timestamp of the first element in history" + windowStart + getSlidingWindow().getIntervalSize()
                     + history.get(0).getTimestamp());
        }
        return 0;
    }

    /**
     * Set the sliding window.
     *
     * @param slidingWindow the new sliding window
     */
    public void setSlidingWindow(SlidingWindow slidingWindow) {
        this.slidingWindow = slidingWindow;

        if (!(slidingWindow.getIntervalSize() > slidingWindow.getIntervalOverlapSize())) {
            log.severe(format("Interval size %d is smaller than overlap %d",
                              slidingWindow.getIntervalSize(),
                              slidingWindow.getIntervalOverlapSize()));
        }

        reset();
    }

    /**
     * Get the sliding window.
     *
     * @return the sliding window
     */
    public SlidingWindow getSlidingWindow() {
        return slidingWindow;
    }
}
