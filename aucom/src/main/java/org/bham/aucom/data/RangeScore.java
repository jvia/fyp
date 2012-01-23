package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a Score generated out of a sequence of consecutive scores. {@see
 * Score} Score value, abnormality and durations to predecessors are mean
 * values of the scores Timestamp and Predecessors are  elements of the first
 * score in the represented sequence
 */
public class RangeScore extends Score {

    private ArrayList<Score> scores;

    public RangeScore(ArrayList<Score> scores) {
        super(scores.get(0));// this is very dangerous, but I have no clue how to solve it
        double meanValue = 0.0d;
        if (scores.size() > 0) {
            setTimestamp(scores.get(0).getTimestamp());
            setContent(scores.get(0).getContent());
        }
        for (Score s : scores) {
            meanValue += s.getValue();
            setFirstElement(isMarkedAsFirstElement() || s.isMarkedAsFirstElement());
            setLastElement(isMarkedAsLastElement() || s.isMarkedAsLastElement());
        }
        meanValue /= scores.size();
        setValue(meanValue);
        setVariance(calculateVarianceValue(scores));
//		setAbnormal(SystemFaultStatus.UNKNOWN);
        this.setScores(scores);
    }

    public int size() {
        return this.getScores().size();
    }

    private double calculateVarianceValue(ArrayList<Score> sequence) {
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

    protected void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public ArrayList<Score> getScores() {
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
