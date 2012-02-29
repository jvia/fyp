package org.bham.aucom.data.io.xml.in.converter;

import nu.xom.Element;
import nu.xom.Nodes;
import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class XmlToScoreTimeSeriesConverter extends XmlToTimeSeriesConverter<Score> {

    @Override
    public Score createDataFromElement(Element e) {
        if (isSingle(e))
            return createSingleScoreFrom(e);
        return createRangeScoreFrom(e);
    }

    public Score createSingleScoreFrom(Element e) {
        double scoreValue = getScoreValue(e);
        TemporalProbabilityFeature tpf = new XmlToTemporalProbabilityFeatureTimeSeriesConverter().createDataFromElement(e);
        return new SingleScore(tpf, scoreValue);
    }

    public RangeScore createRangeScoreFrom(Element e) {
        Logger.getLogger(this.getClass().getCanonicalName()).info(e.toXML());
        Element[] elements = getXmlElementsOfScoresInThisRange(e);
        if (elements.length != 0) {
            ArrayList<Score> scores = new ArrayList<Score>();
            for (int i = 0; i < elements.length; i++) {
                scores.add(createDataFromElement(elements[i]));
            }
            return new RangeScore(scores);
        }
        Logger.getLogger(this.getClass().getCanonicalName()).info("warning no elements in this range score element");
        return null;
    }

    private double getScoreValue(Element e) {
        double score = -1.0;
        Element scoreElement = (Element) e.query("./ts:score[@type='singlescore']", getContext()).get(0);
        if (scoreElement.getAttribute("value") != null) {
            String scoreAsString = scoreElement.getAttributeValue("value");
            score = Double.valueOf(scoreAsString);
        }
        return score;
    }

    private Element[] getXmlElementsOfScoresInThisRange(Element e) {
        Nodes nodes = e.query("./ts:score/ts:element", getContext());
        Element[] elements = new Element[nodes.size()];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = (Element) nodes.get(i);
        }
        return elements;

    }

    public boolean isSingle(Element e) {
        return e.query("./ts:score[@type='singlescore']", getContext()).size() > 0;
    }

    @Override
    public TimeSeries<Score> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<Score> items) {
        ScoreTimeSeries sts = new ScoreTimeSeries();
        sts.setId(id);
        sts.setGeneratedFrom(generatedFromID);
        sts.setGenerator(generatorID);
        sts.addAll(items);
        return sts;
    }

}
