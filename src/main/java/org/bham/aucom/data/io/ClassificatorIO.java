package org.bham.aucom.data.io;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AbstractAnomalyClassifier;

public class ClassificatorIO extends BinaryIO<AbstractAnomalyClassifier> {
    public ClassificatorIO() {
        super(AbstractAnomalyClassifier.class);
    }

}
