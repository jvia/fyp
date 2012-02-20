package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.util.Constants;

public class ScoreTimeSeriesToXmlConverter extends TimeSeriesToXmlDocumentConverter<Score> {

    @Override
    public void appendElementContentAsXml(Element containerElement, Score data) {
        new TemporalProbabilityFeatureTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, data);
        containerElement.appendChild(createScoreElement(data));
    }

    private Element createScoreElement(Score data) {
        if (data.getClass().equals(RangeScore.class)) {
            return createRangeScoreElement(data);
        }
        return createSingleScoreElement(data);
    }

    private Element createSingleScoreElement(Score data) {
        Element singleScoreElement = new Element("ts:score", Constants.URI);
        singleScoreElement.addAttribute(new Attribute("type", "singlescore"));
        singleScoreElement.addAttribute(new Attribute("value", String.valueOf(data.getValue())));
        return singleScoreElement;
    }

    private Element createRangeScoreElement(Score data) {
        Element rangeScoreElement = new Element("ts:score", Constants.URI);
        rangeScoreElement.addAttribute(new Attribute("type", "rangescore"));

        for (Score scoreInRangescore : ((RangeScore) data).getScores()) {
            Element e = new Element("ts:element", Constants.URI);
            this.appendElementContentAsXml(e, scoreInRangescore);
            rangeScoreElement.appendChild(e);
        }
        return rangeScoreElement;
    }

}
