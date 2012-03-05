package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import nu.xom.Element;
import org.bham.aucom.data.*;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CalcDummyScore extends AbstractTransformNode<Score, Score> {
    String methodName = "zero";

    protected Score calc(Score inData) throws Exception {
        try {
            ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
            features.add(new DomainFeature("scope", "a"));
            features.add(new DomainFeature("type", "b"));
            features.add(new DomainFeature("source", "c"));
            DataType d = new DataType(features, 1, new Observation(new Element(""), 1));
            TemporalDurationFeature t2g = new TemporalDurationFeature(d, new LinkedHashMap<DataType, Long>());
            TemporalProbabilityFeature tpd = new TemporalProbabilityFeature(t2g, new HashMap<DataType, Double>());
            double value = 0.1;
            Score score = new SingleScore(tpd, value);

            for (String key : inData.getAttributes().keySet()) {
                score.getAttributes().put(key, inData.getAttributes().get(key));
            }
            return score;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * @param distribution
     * @return
     */
    private double calculateFrequencyWeight(ProbabilityDistribution distribution) {
        return 1;// distribution.getCount();
    }

    @Override
    protected Score transform(Score arg0) throws Exception {
        return calc(arg0);
    }
}