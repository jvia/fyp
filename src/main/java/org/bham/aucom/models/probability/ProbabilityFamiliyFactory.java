package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;

public interface ProbabilityFamiliyFactory<P extends ProbabilityFactory, D extends Number> {
    public P getFactory(D... parameters);

    public ProbabilityInputPanel getInputPanel();
}
