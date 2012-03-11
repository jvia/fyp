package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;

import java.util.logging.Logger;

import static java.lang.String.format;

public class Classify extends AbstractAucomTranformNode<Score, Classification> {
    private AnomalyClassifier classifier;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    public Classify(AnomalyClassifier inThreshold) {
        super("ClassifyScore");
        setClassifier(inThreshold);
    }

    public Classify() {
        super("ClassifyScore");
    }

    @Override
    protected Classification iTransform(Score arg0) throws Exception {
        try {
            return decide(arg0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Classification decide(Score in) {
        Classification cl;
        if (getClassifier().satisfies(in)) {
            cl = new Classification(in, SystemFaultStatus.NORMAL);
        } else {
            cl = new Classification(in, SystemFaultStatus.ABNORMAL);
        }

        log.fine(format("Classify(%s) => %s", in, cl.getStatus()));
        return cl;
    }

    public void setClassifier(AnomalyClassifier threshold) {
        classifier = threshold;
    }

    public AnomalyClassifier getClassifier() {
        return classifier;
    }

    public void reset() {
        if (classifier != null) {
            classifier.reset();
        }
    }
}
