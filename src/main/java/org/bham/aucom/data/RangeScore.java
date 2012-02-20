package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Score generated out of a sequence of consecutive scores. @see
 * Score
 * Score value, abnormality and durations to precedessors are mean values of
 * the
 * scores
 * Timestamp and Precedessors are  elements of the first score in the
 * represented sequence
 */
public class RangeScore extends Score {
    private List<Score> scores;

    public RangeScore(List<Score> scores) {
        // TODO :: this is very dangerous, but I have no clue how to solve it
        super(scores.get(0));
        double meanValue = 0.0d;
        int eventCounter = 0;
        if (scores.size() > 0) {
            setTimestamp(scores.get(0).getTimestamp());
            setContent(scores.get(0).getContent());
        }
        for (Score s : scores) {
            meanValue += s.getValue();
            eventCounter++;
            setFirstElement(isMarkedAsFirstElement() || s.isMarkedAsFirstElement());
            setLastElement(isMarkedAsLastElement() || s.isMarkedAsLastElement());
        }
        meanValue /= scores.size();
        setValue(meanValue);
        setVariance(calculateVarianceValue(scores));
//      setAbnormal(SystemFaultStatus.UNKNOWN);
        this.setScores(scores);
    }

    public int size() {
        return this.getScores().size();
    }

    private double calculateVarianceValue(List<Score> sequence) {
        double var = 0.0d;
        for (Score s : sequence) {
            var += Math.pow(this.getValue() - s.getValue(), 2);
        }
        if (sequence.size() == 1)
            var /= sequence.size();
        else
            var /= (sequence.size() - 1);
        return var;
    }

    protected void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return this.scores;
    }

    @Override
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attr = new HashMap<String, String>();
        for (Score s : this.scores) {
            attr.putAll(s.getAttributes());
        }
        attr.putAll(super.getAttributes());
        return attr;
    }

    @Override
    public String getAttributeValue(String propertyName) {
        if (super.containsAttribute(propertyName)) {
            return super.getAttributeValue(propertyName);
        }
        String attributeValue = null;
        for (Score s : this.scores) {
            if (s.containsAttribute(propertyName)) {
                attributeValue = s.getAttributeValue(propertyName);
            }
        }
        return attributeValue;
    }

    @Override
    public boolean containsAttribute(String propertyName) {
        boolean contains = super.containsAttribute(propertyName);
        for (Score s : this.scores) {
            contains = contains || s.containsAttribute(propertyName);
        }
        return contains;
    }

    @Override
    public Object copy() {
        ArrayList<Score> scores_copy = new ArrayList<Score>();
        for (Score score : this.scores) {
            scores_copy.add((Score) score.copy());
        }
        RangeScore rs_copy = new RangeScore(scores_copy);
        rs_copy.setFirstElement(isMarkedAsFirstElement());
        rs_copy.setLastElement(isMarkedAsLastElement());
        return rs_copy;
    }
    // delegate to score elements of this range score object

}
