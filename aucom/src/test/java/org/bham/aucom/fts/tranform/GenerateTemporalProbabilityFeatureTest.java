package org.bham.aucom.fts.tranform;

import junit.framework.Assert;
import nu.xom.Element;
import org.bham.aucom.data.*;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.models.probability.KernelDensityDistribution;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.HashMatrix;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class GenerateTemporalProbabilityFeatureTest {
    @Test
    public void testITransformTemporalDurationFeatureWhenModelNull() throws Exception
    {
        GenerateTemporalProbabilityFeature gtpf = new GenerateTemporalProbabilityFeature();
        TemporalProbabilityFeature f = gtpf.transform(TemporalDurationFeature.createRandomTemporalDurationFeature());
        Assert.assertNull(f);
    }

    @Test
    public void testITransformTemporalDurationFeatureWithModelPresent() throws Exception{
        GenerateTemporalProbabilityFeature gtpf = new GenerateTemporalProbabilityFeature();
        gtpf.setModel(getMockModel2());
        TemporalDurationFeature feature = getMockFeature();
        TemporalProbabilityFeature f = gtpf.iTransform(feature);
        Assert.assertNotNull(f);
        Assert.assertEquals(2,f.getPredecessors().size());
        Assert.assertEquals(0.44444,f.getProbabilityFor(1), 0.001);
        Assert.assertEquals(0.66666,f.getProbabilityFor(2), 0.001);
        Assert.assertEquals( Constants.LOWESTPROBABILITY,f.getProbabilityFor(3), Constants.LOWESTPROBABILITY);
        Assert.assertEquals(11, f.getDurationFor(1));
        Assert.assertEquals(23, f.getDurationFor(2));
    }

    private T2GramModelI getMockModel2()
    {
        return new T2GramModelI() {
            
            @Override
            public void removeModelListener()
            {
                
            }
            
            @Override
            public boolean isTrained()
            {
                return true;
            }
            
            @Override
            public String getName()
            {
                return "mockmodel";
            }
            
            @Override
            public UUID getId()
            {
                return null;
            }
            
            @Override
            public void addModelListener()
            {
            }
            
            @Override
            public int size()
            {
                return 2;
            }
            
            @Override
            public boolean hasDistributionFor(Integer from, Integer to)
            {
                return false;
            }
            
            @Override
            public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix()
            {
                return null;
            }
            
            @Override
            public double getProbability(int from, int to, long timespan)
            {
                   if(from == 1 && to == 2){
                       return 0.4;
                   }
                   if(from == 2 && to == 2){
                       return 0.6;
                   }
                return 0;
            }
            
            @Override
            public int getNumberDistirbutions()
            {
                return 2;
            }
            
            @Override
            public double getMaxProbabilityFor(int from, int to)
            {
                if(from == 1 && to == 2){
                    return 0.9;
                }
                if(from == 2 && to == 2){
                    return 0.9;
                }
                return 0.0;
            }
            
            @Override
            public double getEntropyOfDistribution(int indexOne, int indexTwo)
            {
                if(indexOne == 1 && indexTwo == 2){
                    return 0.01;
                }
                if(indexOne == 2 && indexTwo == 2){
                    return 0.09;
                }
                return 0;
            }
            
            @Override
            public ProbabilityDistribution getDistributionFor(Integer from, Integer to)
            {
                return null;
            }
            
            @Override
            public ProbabilityFactory getDistributionFactory()
            {
                return null;
            }
            
            @Override
            public Collection<Integer> getDimensions()
            {
                return null;
            }
            
            @Override
            public void addDistribution(Integer from, Integer to, ProbabilityDistribution dist)
            {
            }
        };
    }

    public TemporalDurationFeature getMockFeature()
    {
        Observation obs11 = new Observation(new Element("content"), 112);
        int eventtype1 = 1;
        List<DomainFeature> features1 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "a", features1);
        Observation obs21 = new Observation(new Element("content"), 100);
        int eventtype2 = 2;
        List<DomainFeature> features2 = new ArrayList<DomainFeature>();
        SourceScopeTypeEncoder.createFeatures("a", "a", "b", features1);
        Observation obs22 = new Observation(new Element("content"), 123);
        DataType dtp12 = new DataType(features1,eventtype1, obs11);
        DataType dtp21 = new DataType(features2,eventtype2, obs21);
        DataType dtp22 = new DataType(features2,eventtype2, obs22);

        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        durations.put(dtp12, 11l);
        durations.put(dtp21, 23l);
        return new TemporalDurationFeature(dtp22, durations);
    }

    public T2GramModelI getMockModel()
    {
        T2GramModelImp model = new T2GramModelImp(new ProbabilityFactory() {
            
            @Override
            public ProbabilityDistribution create()
            {
                return null;
            }
            
            @Override
            public ProbabilityDistribution create(double[] trainingValues)
            {
                return null;
            }
        });
        double[] values12= {10, 8, 10, 12, 11, 11, 10, 8, 12};
        model.addDistribution(1, 2, new KernelDensityDistribution(1.0, values12));
        double[] values22 = {22, 23, 25, 26, 27, 22, 21, 22, 22};
        model.addDistribution(2, 2, new KernelDensityDistribution(1.0,values22));
        return model;
    }

    /**
     * Test of iTransform method, of class GenerateTemporalProbabilityFeature.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        TemporalDurationFeature arg0 = null;
        GenerateTemporalProbabilityFeature instance = new GenerateTemporalProbabilityFeature();
        TemporalProbabilityFeature expResult = null;
        TemporalProbabilityFeature result = instance.iTransform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generate method, of class GenerateTemporalProbabilityFeature.
     */
    @Test
    public void testGenerate() {
        System.out.println("generate");
        TemporalDurationFeature dataToTest = null;
        GenerateTemporalProbabilityFeature instance = new GenerateTemporalProbabilityFeature();
        TemporalProbabilityFeature expResult = null;
        TemporalProbabilityFeature result = instance.generate(dataToTest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class GenerateTemporalProbabilityFeature.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        T2GramModelI model = null;
        GenerateTemporalProbabilityFeature instance = new GenerateTemporalProbabilityFeature();
        instance.setModel(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class GenerateTemporalProbabilityFeature.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        GenerateTemporalProbabilityFeature instance = new GenerateTemporalProbabilityFeature();
        T2GramModelI expResult = null;
        T2GramModelI result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
