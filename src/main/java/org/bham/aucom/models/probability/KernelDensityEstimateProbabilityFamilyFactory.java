package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.gui.KernelDensityInputPanel;

public class KernelDensityEstimateProbabilityFamilyFactory implements ProbabilityFamiliyFactory<KDEProbabilityFactory, Double> {

    @Override
    public KDEProbabilityFactory getFactory(Double... parameters) {
        //Assert.assertEquals(1, parameters.length);
        return new KDEProbabilityFactory(parameters[0]);
    }

    @Override
    public ProbabilityInputPanel getInputPanel() {
        return new KernelDensityInputPanel();
    }

}
