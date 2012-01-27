package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;

import javax.swing.event.EventListenerList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class realizes an anomaly classificator for @Score score values. The
 * hypothesis is that a model should produce high score values for data
 * which belongs to this model. Furthermore consecutive score values in a
 * time-series should have low variance.
 * <p/>
 * This classificator computes the mean score and variance over a sequence
 * of consecutive elements of a time-series and uses this values to compute
 * a threshold. except a high score value an item which fits to the
 * corresponding model should have a low variance The idea is to incorporate
 * the variance of the score value
 */
public class StatisticalAnomalyClassificator extends AbstractAnomalyClassificator {
    private static final long serialVersionUID = 1L;
    public static final String THRESHOLD_USED = "thresholdUsed";
    private double classificatorMean;
    private double classificatorVariance;
    transient List<Score> historyScoreValues = Collections.synchronizedList(new ArrayList<Score>());
    int historySize = 50;
    private double a;

    public StatisticalAnomalyClassificator(Double mean, Double variance) {
        super("statistical");
        this.setMean(mean.doubleValue());
        this.setVariance(variance.doubleValue());
        a = 0.95;
    }

    public StatisticalAnomalyClassificator() {
        super("statistical");
        this.setMean(Double.NaN);
        this.setVariance(Double.NaN);
        a = 0.95;
        historySize = Integer.MIN_VALUE;
    }

    public StatisticalAnomalyClassificator(Double mean, Double variance, int size) {
        this(mean, variance);
        this.historySize = size;
        a = 0.95;
    }

    @Override
    public boolean satisfies(Score scoreToCheck) {
        double historyMean = 0.0d;
        double scoreVariance = 0.0d;
        this.historyScoreValues.add(scoreToCheck);
        if (this.historyScoreValues.size() > this.historySize) {
            this.historyScoreValues.remove(0);
        }
        historyMean = calculateMeanOnHistoryElements(this.historyScoreValues);
        scoreVariance = calculateVarianceValue(this.historyScoreValues);
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
        String str = "";
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
        if (this.classificatorMean != mean) {
            fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
        }
        this.classificatorMean = mean;
    }

    public double getMean() {
        return this.classificatorMean;
    }

    protected void setMeanAndVariance(double mean, double variance) {
        {
            boolean valuesChanged = false;
            valuesChanged = valuesChanged || this.classificatorVariance != variance;
            valuesChanged = valuesChanged || this.classificatorMean != mean;
            this.classificatorVariance = variance;
            this.classificatorMean = mean;
            if (valuesChanged) {
                fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
            }
        }

    }

    protected void setVariance(double variance) {
        if (this.classificatorVariance != variance) {
            fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
        }
        this.classificatorVariance = variance;
    }

    public double getVariance() {
        return this.classificatorVariance;
    }

    @Override
    public void setClassificator(AnomalyClassificator classificator) throws ClassCastException {
        StatisticalAnomalyClassificator st = (StatisticalAnomalyClassificator) classificator;
        setMeanAndVariance(st.getMean(), st.getVariance());
    }

    @Override
    public void copy(AnomalyClassificator classificator) {
        setClassificator(classificator);

    }

    @Override
    public AnomalyClassificator duplicate() {
        return new StatisticalAnomalyClassificator(this.classificatorMean, this.classificatorVariance);
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