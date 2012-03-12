package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;

import javax.swing.event.EventListenerList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class realizes an anomaly classifier for score values. The
 * hypothesis is that a model should produce high score values for data
 * which belongs to this model. Furthermore consecutive score values in a
 * time-series should have low variance.
 * <p/>
 * This classifier computes the mean score and variance over a sequence
 * of consecutive elements of a time-series and uses this values to compute
 * a threshold. except a high score value an item which fits to the
 * corresponding model should have a low variance The idea is to incorporate
 * the variance of the score value.
 *
 * @see Score
 */
public class StatisticalAnomalyClassifier extends AbstractAnomalyClassifier {

    private static final long serialVersionUID = 1L;
    public static final String THRESHOLD_USED = "thresholdUsed";
    private final transient Logger log = Logger.getLogger(getClass().getName());

    private double classifierMean;
    private double classifierVariance;
    private final double a;
    private transient List<Score> scoreHistory = Collections.synchronizedList(new ArrayList<Score>());
    private int historySize = 50;


    public StatisticalAnomalyClassifier(final double mean,
                                        final double variance) {
        super("StatisticalAnomalyClassifier");
        setMean(mean);
        setVariance(variance);
        a = 0.95;
        log.setLevel(Level.ALL);
    }

    @Override
    public boolean satisfies(final Score scoreToCheck) {
        scoreHistory.add(scoreToCheck);

        if (scoreHistory.size() > historySize) {
            scoreHistory.remove(0);
        }

        double historyMean = calculateMeanOnHistoryElements(scoreHistory);
        double valueToCompare = getValueToCompare();
        scoreToCheck.addAttribute(THRESHOLD_USED, String.valueOf(valueToCompare));
        log.fine(String.format("Satisfies: %f <= %f: %b",
                               valueToCompare, historyMean,
                               valueToCompare <= historyMean));
        return valueToCompare <= historyMean;
    }

    public double getValueToCompare() {
        double scoreVariance = calculateVarianceValue(scoreHistory);
        double quotient = scoreVariance / classifierVariance;
        double value =(a * classifierMean) + ((1.0 - a) * classifierMean * quotient);
        log.fine(String.format("Compare to: (%f * %f) + ((1.0 - %f) * %f * %f) = %f",
                               a, classifierMean, a, classifierMean, quotient, value));
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s:%d:%.4f:%.4f",
                             getAttributeValue("name"),
                             historySize,
                             classifierMean,
                             classifierVariance);
    }

    private double calculateVarianceValue(final List<Score> sequence) {
        double var = 0.0d;
        double calculatedMean = calculateMeanOnHistoryElements(sequence);

        for (Score s : sequence) {
            var += Math.pow(calculatedMean - s.getValue(), 2);
        }

        if (sequence.size() == 1) {
            var /= sequence.size();
        } else {
            var /= (sequence.size() - 1);
        }
        return var;
    }

    public double calculateMeanOnHistoryElements(final List<Score> sequence) {
        double calculatedMean = 0.0d;

        for (Score s : sequence) {
            calculatedMean += s.getValue();
        }

        return calculatedMean / sequence.size();
    }

    protected void setMean(final double mean) {
        if (classifierMean != mean) {
            fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
        }

        classifierMean = mean;
    }

    public double getMean() {
        return classifierMean;
    }

    protected void setMeanAndVariance(final double mean, final double variance) {
        boolean valuesChanged = classifierVariance != variance || classifierMean != mean;

        classifierVariance = variance;
        classifierMean = mean;

        if (valuesChanged) {
            fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
        }
    }

    protected void setVariance(final double variance) {
        if (classifierVariance != variance) {
            fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
        }

        classifierVariance = variance;
    }

    public double getVariance() {
        return classifierVariance;
    }

    @Override
    public void setClassifier(final AnomalyClassifier classifier) throws ClassCastException {
        StatisticalAnomalyClassifier st = (StatisticalAnomalyClassifier) classifier;
        setMeanAndVariance(st.getMean(), st.getVariance());
    }

    @Override
    public void copy(final AnomalyClassifier classifier) {
        setClassifier(classifier);

    }

    @Override
    public AnomalyClassifier duplicate() {
        return new StatisticalAnomalyClassifier(classifierMean, classifierVariance);
    }

    public void reset() {
        scoreHistory.clear();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        try {
            out.defaultWriteObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            in.defaultReadObject();
            listenerList = new EventListenerList();
            scoreHistory = Collections.synchronizedList(new ArrayList<Score>());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}