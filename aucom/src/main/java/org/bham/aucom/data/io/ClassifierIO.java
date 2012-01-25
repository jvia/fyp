package org.bham.aucom.data.io;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AbstractAnomalyClassifier;

class ClassifierIO extends BinaryIO<AbstractAnomalyClassifier> {
	public ClassifierIO() {
		super(AbstractAnomalyClassifier.class);
	}
}
