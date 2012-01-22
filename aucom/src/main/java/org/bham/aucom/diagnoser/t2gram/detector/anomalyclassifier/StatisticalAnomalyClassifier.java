package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassifierStatusEvent;

import javax.swing.event.EventListenerList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StatisticalAnomalyClassifier extends AbstractAnomalyClassifier {
    public static final String THRESHOLD_USED = "thresholdUsed";
    /**
     * This class realizes an anomaly classifier for @Score score values. The
     * hypothesis is that a model should produce high score values for data
     * which belongs to this model. Furthermore consecutive score values in a
     * time-series should have low variance.
     * <p/>
     * This classifier computes the mean score and variance over a sequence
     * of consecutive elements of a time-series and uses this values to compute
     * a threshold. except a high score value an item which fits to the
     * corresponding model should have a low variance The idea is to incorporate
     * the variance of the score value
     */
    private static final long serialVersionUID = 1L;
    private double classifierMean;
    private double classifierVariance;
    transient List<Score> historyScoreValues = Collections.synchronizedList(new ArrayList<Score>());
    int historySize = 50;
    private double a;

    public StatisticalAnomalyClassifier(Double mean, Double variance) {
        super("statistical");
        this.setMean(mean);
        this.setVariance(variance);
        a = 0.95;
    }

    public StatisticalAnomalyClassifier() {
        super("statistical");
        this.setMean(Double.NaN);
        this.setVariance(Double.NaN);
        a = 0.95;
        historySize = Integer.MIN_VALUE;
    }

    public StatisticalAnomalyClassifier(Double mean, Double variance, int size) {
        this(mean, variance);
        this.historySize = size;
        a = 0.95;
    }

    @Override
    public boolean satisfies(Score scoreToCheck) {
        double historyMean;
        //double scoreVariance = 0.0d;
        this.historyScoreValues.add(scoreToCheck);
        if (this.historyScoreValues.size() > this.historySize) {
            this.historyScoreValues.remove(0);
        }
        historyMean = calculateMeanOnHistoryElements(this.historyScoreValues);
        //scoreVariance = calculateVarianceValue(this.historyScoreValues);
//		 System.out.println(scoreToCheck.getClass().getSimpleName() + "mean "
        // + mean + "\nvar " + scoreVariance + "\nq " + quotient + "\n #" +
        // ((RangeScore)scoreToCheck).size() + "\nval " + (a * this.mean +
        // (1.0-a) * this.mean * quotient) + "\n result"
        // + ((a * this.mean + (1.0-a) * this.mean * quotient) <= mean) +
        // "--\n");
        double valueTomCompare = getValueToCompare();
        scoreToCheck.addAttribute(THRESHOLD_USED, String.valueOf(valueTomCompare));
        return valueTomCompare <= historyMean;
        // return (this.mean +
    }

    public double getValueToCompare() {
        double scoreVariance = calculateVarianceValue(this.historyScoreValues);
        double quotient = scoreVariance / this.getVariance();
        return (a * this.getMean() + (1.0 - a) * this.getMean() * quotient);
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("#.####");
        String str;
        str = this.getAttributeValue("name");
        str += ": ";
        str += f.format(this.historySize);
        str += ":";
        str += f.format(this.getMean());
        str += ":";
        str += f.format(this.getVariance());
        return str;
    }

    private double calculateVarianceValue(List<Score> sequence) {
        double var = 0.0d;
        double calculatedMean = calculateMeanOnHistoryElements(sequence);
        for (int i = 0; i < sequence.size(); i++) {
            Score s = sequence.get(i);
            var += Math.pow(calculatedMean - s.getValue(), 2);
        }
        if (sequence.size() == 1)
            var /= sequence.size();
        else
            var /= (sequence.size() - 1);
        return var;
    }

    public double calculateMeanOnHistoryElements(List<Score> sequence) {
        double calculatedMean = 0.0d;
        for (int i = 0; i < sequence.size(); i++) {
            Score s = sequence.get(i);
            calculatedMean += s.getValue();
        }
        return calculatedMean / sequence.size();
    }

    protected void setMean(double mean) {
        if (this.classifierMean != mean) {
            fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
        }
        this.classifierMean = mean;
    }

    public double getMean() {
        return this.classifierMean;
    }

    protected void setMeanAndVariance(double mean, double variance) {
        {
            boolean valuesChanged;
            valuesChanged = this.classifierVariance != variance;
            valuesChanged = valuesChanged || this.classifierMean != mean;
            this.classifierVariance = variance;
            this.classifierMean = mean;
            if (valuesChanged) {
                fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
            }
        }

    }

    protected void setVariance(double variance) {
        if (this.classifierVariance != variance) {
            fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
        }
        this.classifierVariance = variance;
    }

    public double getVariance() {
        return this.classifierVariance;
    }

    @Override
    public void setClassificator(AnomalyClassifier classifier) throws ClassCastException {
        StatisticalAnomalyClassifier st = (StatisticalAnomalyClassifier) classifier;
        setMeanAndVariance(st.getMean(), st.getVariance());
    }

    @Override
    public void copy(AnomalyClassifier classifier) {
        setClassificator(classifier);

    }

    @Override
    public AnomalyClassifier duplicate() {
        return new StatisticalAnomalyClassifier(this.classifierMean, this.classifierVariance);
    }

    public void reset() {
        this.historyScoreValues.clear();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        try {
            out.defaultWriteObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            in.defaultReadObject();
            this.listenerList = new EventListenerList();
            this.historyScoreValues = Collections.synchronizedList(new ArrayList<Score>());
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("testgraph loading failed");
        }
    }
}