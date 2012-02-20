package org.bham.aucom.data.io;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AbstractAnomalyClassificator;

public class ClassificatorIO extends BinaryIO<AbstractAnomalyClassificator> {
    public ClassificatorIO() {
        super(AbstractAnomalyClassificator.class);
    }

}
