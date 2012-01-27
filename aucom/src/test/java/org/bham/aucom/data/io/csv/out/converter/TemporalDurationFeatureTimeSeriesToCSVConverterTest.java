package org.bham.aucom.data.io.csv.out.converter;

import junit.framework.Assert;
import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TemporalDurationFeatureTimeSeriesToCSVConverterTest {

    @Test
    public void testConvertTimeSeriesElementTemporalDurationFeature() {
        TemporalDurationFeatureTimeSeriesToCSVConverter converter = new TemporalDurationFeatureTimeSeriesToCSVConverter();
        TemporalDurationFeature featureOrderA = getMockTemporalDuartionFeatureOrderTypeA();
        TemporalDurationFeature featureOrderB = getMockTemporalDuartionFeatureOrderTypeB();
        String featureOrderAStringRepresentation = converter.convertTimeSeriesElement(featureOrderA);
        String featureOrderBStringRepresentation = converter.convertTimeSeriesElement(featureOrderB);
        Assert.assertEquals(featureOrderAStringRepresentation, featureOrderBStringRepresentation);
    }

    @Test
    public void testConvertFeatureWithBigTimestamps() {
        TemporalDurationFeatureTimeSeriesToCSVConverter converter = new TemporalDurationFeatureTimeSeriesToCSVConverter();
        TemporalDurationFeature featureWithShortTimestamps = getMockTemporalDuartionFeatureWithBigTimestamps();
        TemporalDurationFeature featureWithLongTimestamsp = getMockTemporalDuartionFeatureOrderTypeB();
        String featureWithShortTimestamps_str = converter.convertTimeSeriesElement(featureWithShortTimestamps);
        String featureWithLongTimestamsp_str = converter.convertTimeSeriesElement(featureWithLongTimestamsp);
        System.out.println(featureWithShortTimestamps_str);
        System.out.println(featureWithLongTimestamsp_str);
    }

    private TemporalDurationFeature getMockTemporalDuartionFeatureWithBigTimestamps() {
        long ts = System.currentTimeMillis();
        Observation obs11 = new Observation(new Element("content"), ts + 112);
        int eventtype1 = 1;
        List<DomainFeature> features1 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "a", features1);
        Observation obs21 = new Observation(new Element("content"), ts + 100);
        int eventtype2 = 2;
        List<DomainFeature> features2 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "b", features1);
        Observation obs22 = new Observation(new Element("content"), ts + 123);

        DataType dtp21 = new DataType(features2, eventtype2, obs21);
        DataType dtp12 = new DataType(features1, eventtype1, obs11);
        DataType dtp22 = new DataType(features2, eventtype2, obs22);

        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        durations.put(dtp21, 23l);
        durations.put(dtp12, 11l);
        TemporalDurationFeature featureOrderB = new TemporalDurationFeature(dtp22, durations);
        return featureOrderB;

    }

    private TemporalDurationFeature getMockTemporalDuartionFeatureOrderTypeB() {
        Observation obs11 = new Observation(new Element("content"), 112);
        int eventtype1 = 1;
        List<DomainFeature> features1 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "a", features1);
        Observation obs21 = new Observation(new Element("content"), 100);
        int eventtype2 = 2;
        List<DomainFeature> features2 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "b", features1);
        Observation obs22 = new Observation(new Element("content"), 123);

        DataType dtp21 = new DataType(features2, eventtype2, obs21);
        DataType dtp12 = new DataType(features1, eventtype1, obs11);
        DataType dtp22 = new DataType(features2, eventtype2, obs22);

        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        durations.put(dtp21, 23l);
        durations.put(dtp12, 11l);
        TemporalDurationFeature featureOrderB = new TemporalDurationFeature(dtp22, durations);
        return featureOrderB;

    }

    private TemporalDurationFeature getMockTemporalDuartionFeatureOrderTypeA() {
        Observation obs11 = new Observation(new Element("content"), 112);
        int eventtype1 = 1;
        List<DomainFeature> features1 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "a", features1);
        Observation obs21 = new Observation(new Element("content"), 100);
        int eventtype2 = 2;
        List<DomainFeature> features2 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "b", features1);
        Observation obs22 = new Observation(new Element("content"), 123);

        DataType dtp21 = new DataType(features2, eventtype2, obs21);
        DataType dtp12 = new DataType(features1, eventtype1, obs11);
        DataType dtp22 = new DataType(features2, eventtype2, obs22);

        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        durations.put(dtp12, 11l);
        durations.put(dtp21, 23l);
        TemporalDurationFeature featureOrderA = new TemporalDurationFeature(dtp22, durations);
        return featureOrderA;
    }

}
