package org.bham.aucom.fts.tranform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;

public class GenerateTemporalDurationFeatureTest {
    GenerateTemporalDurationFeature generateFeatures;
    TimeSeries<DataType> dataTypeTimeSeries;

    @Before
    public void setUp() throws Exception
    {
        generateFeatures = new GenerateTemporalDurationFeature();
        Collection<Integer> initialElement = new ArrayList<Integer>();
        generateFeatures.setGenerator(new TemporalDurationFeatureGenerator(initialElement));
    }

    @Test
    public void testITransformDataTypeWithOneDatType() throws Exception
    {
        dataTypeTimeSeries = createDataTypeTimeSeriesWithOneDataType(10);
        TemporalDurationFeature tdf = null;
        for (int i = 0; i < dataTypeTimeSeries.size(); i++) {
            tdf = generateFeatures.transform(dataTypeTimeSeries.get(i));
        }
        Assert.assertNotNull(tdf);
        Assert.assertEquals(1, tdf.getPredecessors().size());
        Assert.assertEquals(1, generateFeatures.getGenerator().lastOccurrences.size());

    }

    private TimeSeries<DataType> createDataTypeTimeSeriesWithOneDataType(int num) throws InterruptedException
    {
        TimeSeries<DataType> out = new DataTypeTimeSeries();
        for (int i = 0; i < num; i++) {
            List<DomainFeature> features = new ArrayList<DomainFeature>();
            Observation obs = new Observation();
            obs.setTimestamp(System.currentTimeMillis());
            features.add(new DomainFeature(Constants.EVENT_TYPE, "a"));
            DataType dtp = new DataType(features, 1, obs);
            out.add(dtp);
            Thread.sleep(10);
        }
        return out;
    }
    /**
     * This test checks whether the temporal feature is computed correctly in
     * case that two events/predecessors have been already seen/processed by
     * this temporal duration feature generator. The generator does not know any initial classes.
     * <br>
     * Input: 
     * <ul>
     * <li>Preceding event two : time stamp 100, source = a, scope = a, type = b, evenTypeId = 2</li>
     * <li>Preceding event one: time stamp 112, source = a, scope = a, type = a, evenTypeId = 1 </li>
     * <li>Current event : time stamp 123, source = a, scope = a, type = b, evenTypeId = 2 </li>
     * <li>Current event : time stamp 123, source = a, scope = a, type = b, evenTypeId= 2 </li>
     *</UL>
     * <br> Output should be a Temporal duration feature with the following durations for evenTypeIds:
     * <ul>
     * <li> 1 ---> 2 == 11 ms </li>
     * <li> 2 ---> 2 == 23 ms </li>
     *</UL>
  
     * @throws Exception
     */
    @Test
    public void testITransformDataTypeWithTwoPredecessorsWithInitialClasses() throws Exception
    {
        TimeSeries<DataType> mockDataTypeTimeSeries = getMockDataTypeTimeSeries ();
        TimeSeries<TemporalDurationFeature> tdfTs = new TemporalDurationFeatureTimeSeries();
        List<Integer> initialClasses = new ArrayList<Integer>();
        initialClasses.add(1);
        initialClasses.add(2);
        generateFeatures.getGenerator().addInitalClasses(initialClasses);
        generateFeatures.getGenerator().initializeLastOccurances();
        Assert.assertEquals(2, generateFeatures.getGenerator().lastOccurrences.size());
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(0)));
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(1)));
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(2)));
        Assert.assertEquals(3, tdfTs.size());
        
        // checking first element 
        Assert.assertNotNull(tdfTs.get(0));
        Assert.assertEquals(2, tdfTs.get(0).getPredecessors().size());
        Assert.assertEquals(100, tdfTs.get(0).getDurationFor(1));
        Assert.assertEquals(100, tdfTs.get(0).getDurationFor(2));
        // checking second element
        Assert.assertNotNull(tdfTs.get(1));
        Assert.assertEquals(2, tdfTs.get(1).getPredecessors().size());
        Assert.assertEquals(112, tdfTs.get(1).getDurationFor(1));
        Assert.assertEquals(12, tdfTs.get(1).getDurationFor(2));
        
        // checking third element 
        Assert.assertNotNull(tdfTs.get(2));
        Assert.assertEquals(2, tdfTs.get(2).getPredecessors().size());
        Assert.assertEquals(11, tdfTs.get(2).getDurationFor(1));
        Assert.assertEquals(23, tdfTs.get(2).getDurationFor(2));
        Assert.assertEquals(2, generateFeatures.getGenerator().lastOccurrences.size());
    }
    

    /**
     * This test checks whether the temporal feature is computed correctly in
     * case that two events/predecessors have been already seen/processed by
     * this temporal duration feature generator. The generator does not know any initial classes.
     * <br>
     * Input: 
     * <ul>
     * <li>Preceding event two : time stamp 100, source = a, scope = a, type = b, evenTypeId = 2</li>
     * <li>Preceding event one: time stamp 112, source = a, scope = a, type = a, evenTypeId = 1 </li>
     * <li>Current event : time stamp 123, source = a, scope = a, type = b, evenTypeId = 2 </li>
     * <li>Current event : time stamp 123, source = a, scope = a, type = b, evenTypeId= 2 </li>
     *</UL>
     * <br> Output should be a Temporal duration feature with the following durations for evenTypeIds:
     * <ul>
     * <li> 1 ---> 2 == 11 ms </li>
     * <li> 2 ---> 2 == 23 ms </li>
     *</UL>
  
     * @throws Exception
     */
    @Test
    public void testITransformDataTypeWithTwoPredecessorsWithoutInitialClasses() throws Exception
    {
        TimeSeries<DataType> mockDataTypeTimeSeries = getMockDataTypeTimeSeries ();
        TimeSeries<TemporalDurationFeature> tdfTs = new TemporalDurationFeatureTimeSeries();
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(0)));
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(1)));
        tdfTs.add(generateFeatures.iTransform(mockDataTypeTimeSeries.get(2)));
        Assert.assertEquals(3, tdfTs.size());
        
        // checking first element 
        Assert.assertNotNull(tdfTs.get(0));
        Assert.assertEquals(0, tdfTs.get(0).getPredecessors().size());
        Assert.assertEquals(0, tdfTs.get(0).getDurationFor(1));
        Assert.assertEquals(0, tdfTs.get(0).getDurationFor(2));
        // checking second element
        Assert.assertNotNull(tdfTs.get(1));
        Assert.assertEquals(1, tdfTs.get(1).getPredecessors().size());
        Assert.assertEquals(0, tdfTs.get(1).getDurationFor(1));
        Assert.assertEquals(12, tdfTs.get(1).getDurationFor(2));
        
        // checking third element 
        Assert.assertNotNull(tdfTs.get(2));
        Assert.assertEquals(2, tdfTs.get(2).getPredecessors().size());
        Assert.assertEquals(11, tdfTs.get(2).getDurationFor(1));
        Assert.assertEquals(23, tdfTs.get(2).getDurationFor(2));
        Assert.assertEquals(2, generateFeatures.getGenerator().lastOccurrences.size());
    }

    public  TimeSeries<DataType>  getMockDataTypeTimeSeries()
    {
        TimeSeries<DataType> tdpTs = new DataTypeTimeSeries();
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

        tdpTs.add(dtp21);
        tdpTs.add(dtp12);
        tdpTs.add(dtp22);
        return tdpTs;
    }

}
