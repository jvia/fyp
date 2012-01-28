package org.bham.aucom.fts.tranform;

import java.util.Collection;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

public class TemporalDurationFeatureGeneratorTest {
    TemporalDurationFeatureGenerator tdfg;
    
    /**
     * Check whether null input is handled correctly.  
     * 
     * @throws NullPointerException
     */
    @Test
    public void testTemporalDurationFeatureGeneratorWithNullInput() throws NullPointerException
    {
        tdfg= new TemporalDurationFeatureGenerator(null);
    }

    @Test
    public void testTemporalDurationFeatureGeneratorWithEmptyCollectionInput() throws NullPointerException
    {
        tdfg= new TemporalDurationFeatureGenerator(new ArrayList<Integer>());
        Assert.assertEquals(0, tdfg.lastOccurrences.size());
    }

    @Test
    public void testTemporalDurationFeatureGeneratorWithNonEmptyInput() throws NullPointerException
    {
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        for(int i = 0 ; i < 10; i++){
            initialEventTypeIds.add(Integer.valueOf(i));
        }
        tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds );
        Assert.assertEquals(initialEventTypeIds.size(), tdfg.initialClasses.size());
    }

    @Test
    public void testClearInitialClasses()
    {
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        int num = 10;
        for(int i = 0 ; i < num; i++){
            initialEventTypeIds.add(Integer.valueOf(i));
        }
        tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds );
        initialEventTypeIds.clear();
        Assert.assertEquals(num, tdfg.initialClasses.size());
    }

    @Test
    public void testAddInitalClassesWithNullInput() throws NullPointerException
    {
        tdfg= new TemporalDurationFeatureGenerator(null );
        tdfg.addInitalClasses(null);
      
    }
    @Test
    public void testAddInitalClassesWithEmptyInput() throws NullPointerException
    {
        tdfg= new TemporalDurationFeatureGenerator(null);
        ArrayList<Integer> additionalEventTypeIds  = new ArrayList<Integer>();
        tdfg.addInitalClasses(additionalEventTypeIds);
        Assert.assertEquals(0, tdfg.initialClasses.size());
    }
    @Test
    public void testAddInitalClassesWith_EmptyInput() throws NullPointerException
    {
        tdfg= new TemporalDurationFeatureGenerator(null );
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        int num = 10;
        for(int i = 0 ; i < num; i++){
            initialEventTypeIds.add(Integer.valueOf(i));
        }
        tdfg.addInitalClasses(initialEventTypeIds);
        Assert.assertEquals(num, tdfg.initialClasses.size());
    }
    
    @Test
    public void testResetInitialClasses()
    {
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        int num = 10;
        for(int i = 0 ; i < num; i++){
            initialEventTypeIds.add(Integer.valueOf(i));
        }
        tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds);
        tdfg.resetInitialClasses();
        Assert.assertEquals(0, tdfg.initialClasses.size());
    }

    @Test
    public void testResetLastOccurances()
    {
            ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
            initialEventTypeIds.add(1);
            initialEventTypeIds.add(2);
            tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds);
            tdfg.initializeLastOccurances();
            Assert.assertEquals(0, tdfg.lastOccurrences.get(1).getTimestamp());
            Assert.assertEquals(0, tdfg.lastOccurrences.get(2).getTimestamp());
            TimeSeries<DataType> ts =   getMockDataTypeTimeSeries();
            tdfg.generateFeature(ts.get(0));
            tdfg.generateFeature(ts.get(1));
            tdfg.generateFeature(ts.get(2));
            Assert.assertEquals(112, tdfg.lastOccurrences.get(1).getTimestamp());
            Assert.assertEquals(123, tdfg.lastOccurrences.get(2).getTimestamp());
            tdfg.initializeLastOccurances();
            Assert.assertEquals(0, tdfg.lastOccurrences.get(1).getTimestamp());
            Assert.assertEquals(0, tdfg.lastOccurrences.get(2).getTimestamp());
    }
    @Test 
    public void testIsLastOccurancesInitialized(){
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        initialEventTypeIds.add(1);
        initialEventTypeIds.add(2);
        tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds);
        tdfg.initializeLastOccurances();
        tdfg.isLastOccurancesInitialized();
        TimeSeries<DataType> ts =   getMockDataTypeTimeSeries();
        tdfg.generateFeature(ts.get(0));
        tdfg.generateFeature(ts.get(1));
        tdfg.generateFeature(ts.get(2));
        Assert.assertEquals(112, tdfg.lastOccurrences.get(1).getTimestamp());
        Assert.assertEquals(123, tdfg.lastOccurrences.get(2).getTimestamp());
        tdfg.initializeLastOccurances();
        tdfg.isLastOccurancesInitialized();
    }

    @Test
    public void testGenerateFeatureOnDataTypesWithBigTimestamps()
    {
        ArrayList<Integer> initialEventTypeIds  = new ArrayList<Integer>();
        initialEventTypeIds.add(1);
        initialEventTypeIds.add(2);
        tdfg= new TemporalDurationFeatureGenerator(initialEventTypeIds);
        tdfg.initializeLastOccurances();
        tdfg.isLastOccurancesInitialized();
        TimeSeries<DataType> ts =   getMockDataTypeTimeSeriesWithBigTimestamps();
        TemporalDurationFeature f1 = tdfg.generateFeature(ts.get(0));
        TemporalDurationFeature f2 = tdfg.generateFeature(ts.get(1));
        TemporalDurationFeature f3 = tdfg.generateFeature(ts.get(2));
        Assert.assertTrue(f1.getTimestamp() != f2.getTimestamp());
        Assert.assertEquals(12,f2.getDurationFor(1));
        Assert.assertTrue(f1.getTimestamp() != f3.getTimestamp());
        Assert.assertTrue(f2.getTimestamp() != f3.getTimestamp());
        Assert.assertTrue(tdfg.lastOccurrences.get(1).getTimestamp() != tdfg.lastOccurrences.get(2).getTimestamp());
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
    public  TimeSeries<DataType>  getMockDataTypeTimeSeriesWithBigTimestamps()
    {
    	long ts = System.currentTimeMillis();
    	TimeSeries<DataType> tdpTs = new DataTypeTimeSeries();
    	Observation obs11 = new Observation(new Element("content"), ts+112);
    	int eventtype1 = 1;
    	List<DomainFeature> features1 = new ArrayList<DomainFeature>();
    	SourceScopeTypeEncoder.createFeatures("a", "a", "a", features1);
    	Observation obs21 = new Observation(new Element("content"), ts+100);
    	int eventtype2 = 2;
    	List<DomainFeature> features2 = new ArrayList<DomainFeature>();
    	SourceScopeTypeEncoder.createFeatures("a", "a", "b", features1);
    	Observation obs22 = new Observation(new Element("content"), ts+123);
    	
    	DataType dtp21 = new DataType(features2, eventtype2, obs21);
    	DataType dtp12 = new DataType(features1, eventtype1, obs11);
    	DataType dtp22 = new DataType(features2, eventtype2, obs22);
    	
    	tdpTs.add(dtp21);
    	tdpTs.add(dtp12);
    	tdpTs.add(dtp22);
    	return tdpTs;
    }

    /**
     * Test of addInitalClasses method, of class TemporalDurationFeatureGenerator.
     */
    @Test
    public void testAddInitalClasses() {
        System.out.println("addInitalClasses");
        Collection<Integer> inInitialClassesToAdd = null;
        TemporalDurationFeatureGenerator instance = null;
        instance.addInitalClasses(inInitialClassesToAdd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeLastOccurances method, of class TemporalDurationFeatureGenerator.
     */
    @Test
    public void testInitializeLastOccurances() {
        System.out.println("initializeLastOccurances");
        TemporalDurationFeatureGenerator instance = null;
        instance.initializeLastOccurances();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateFeature method, of class TemporalDurationFeatureGenerator.
     */
    @Test
    public void testGenerateFeature() {
        System.out.println("generateFeature");
        DataType in = null;
        TemporalDurationFeatureGenerator instance = null;
        TemporalDurationFeature expResult = null;
        TemporalDurationFeature result = instance.generateFeature(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
