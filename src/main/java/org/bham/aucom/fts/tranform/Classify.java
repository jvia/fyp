package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;

import java.util.logging.Logger;

public class Classify extends AbstractAucomTranformNode<Score, Classification> {
    private AnomalyClassifier classifier = null;

    public Classify(AnomalyClassifier inThreshold) {
        super("ClassificateScore");
        setClassifier(inThreshold);
    }

    public Classify() {
        super("ClassificateScore");
    }

    private Classification decide(Score in) {

        Classification cl = null;

        if (getClassifier().satisfies(in))
            cl = new Classification(in, SystemFaultStatus.NORMAL);
        else
            cl = new Classification(in, SystemFaultStatus.ABNORMAL);

        Logger.getLogger(this.getClass().getCanonicalName()).info(in.toString() + " classificated as " + cl.getStatus());
        return cl;
    }

    @Override
    protected Classification iTransform(Score arg0) throws Exception {
        try {
            Logger.getLogger(this.getClass().getCanonicalName()).info("classificating score " + arg0);
            return decide(arg0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void setClassifier(AnomalyClassifier threshold) {
        this.classifier = threshold;
    }

    public AnomalyClassifier getClassifier() {
        return this.classifier;
    }

    public void reset() {
        if (classifier != null) {
            classifier.reset();
        }
    }
}
