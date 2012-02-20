package org.bham.aucom.data.timeseries;

import org.bham.aucom.data.Classification;

import java.util.ArrayList;
import java.util.UUID;

public class ClassificationTimeSeries extends TimeSeries<Classification> {
    private static final long serialVersionUID = 0L;

    public ClassificationTimeSeries() {
        setType(TimeSeriesType.cl);
    }

    public ClassificationTimeSeries(UUID generatorId, UUID scoreTimeSeriesID, UUID id) {
        super(generatorId, scoreTimeSeriesID, id, new ArrayList<Classification>());
        setType(TimeSeriesType.cl);
    }

    public static ClassificationTimeSeries createRandomTimeSeries() {
        ClassificationTimeSeries cl = new ClassificationTimeSeries();
        cl.add(Classification.createRandomClassification());
        cl.add(Classification.createRandomClassification());
        cl.add(Classification.createRandomClassification());
        cl.add(Classification.createRandomClassification());
        cl.add(Classification.createRandomClassification());
        return cl;
    }

}