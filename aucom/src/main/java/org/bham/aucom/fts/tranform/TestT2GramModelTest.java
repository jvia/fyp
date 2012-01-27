package org.bham.aucom.fts.tranform;


import static org.hamcrest.CoreMatchers.is;
import net.sf.xcf.fts.engine.Graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.HysteresisAnomalyClassificator;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.models.probability.HistogramDistribution;
public class TestT2GramModelTest {
	T2GramModelImp model;
	GenerateTemporalProbabilityFeature modelTest;
	TimeSeriesSink<Classification> sink;
	TimeSeriesSource<Observation> source;
	ObservationTimeSeries intputQueue;
	Graph g ;
	@Before
	public void setUp() throws Exception {
		model = new T2GramModelImp(new KDEProbabilityFactory());
		double[] values = {90, 190,290,390};
		HistogramDistribution test = new HistogramDistribution("1_2", 100, values);
		test.setInfoDebugLevel();
		model.addDistribution(1, 2, test);
		test = new HistogramDistribution("2_1", 100, values);
		model.addDistribution(2, 1, test);
		test = new HistogramDistribution("1_1", 100, values);
		model.addDistribution(1, 1, test);
		test = new HistogramDistribution("2_2", 100, values);
		model.addDistribution(2, 2, test);
		modelTest = new GenerateTemporalProbabilityFeature();
		modelTest.setModel(model);
		g = new Graph();
		intputQueue =  new ObservationTimeSeries();
		source = new TimeSeriesSource<Observation>(intputQueue, "testQueue");
		ClassificationTimeSeries classificationTs = new ClassificationTimeSeries();
		sink = new TimeSeriesSink<Classification>(classificationTs);
		EncodeData encodeData = new EncodeData();
		TemporalDurationFeatureGenerator tdfGenerator = new TemporalDurationFeatureGenerator(this.model.getDimensions());
		GenerateTemporalDurationFeature generateDurationFeature = new GenerateTemporalDurationFeature();
		generateDurationFeature.setGenerator(tdfGenerator);
		g.connect(source, encodeData);
		g.connect(encodeData, generateDurationFeature);
		g.connect(generateDurationFeature, modelTest);
		CalcEntropyAvgScore scorer = new CalcEntropyAvgScore(this.model);
		Classificate classificate = new Classificate(new HysteresisAnomalyClassificator(0.0, 0.1));
		g.connect(modelTest, scorer);
		g.connect(scorer, classificate);
		g.connect(classificate, sink);

	}
	@Test
	public void testFirstT2GramData(){
		TemporalDurationFeature dat = new TemporalDurationFeature();
		modelTest.generate(dat);
		Assert.assertThat(-1l,is((dat.getTimestamp())));
//		Assert.assertEquals(dat.getPrecedessors().size(), 0);
//		Assert.assertEquals(dat.getTransitionProbabilityForPrecedessor(dat), LOWESTPROBABILITY);
		
	}
//	@Test
//	public void testT2GramData(){
////		ArrayList<DomainFeature> f = new ArrayList<DomainFeature>();
////		f.add(new DomainFeature("scope", "www"));
////		f.add(new DomainFeature("type", "insert"));
////		f.add(new DomainFeature("source", "src1"));
////		PointData dat1 = new PointData(f,  0l, 1);
////		modelTest.test(dat1);
////		Assert.assertEquals(dat1.getTimestamp(), 0);
////		Assert.assertEquals(dat1.getPrecedessors().size(), 0);
////		Assert.assertEquals(LOWESTPROBABILITY, dat1.getTransitionProbabilityForPrecedessor(dat1));
////		
////		PointData dat2 = new PointData(f, 1l, 2);
////		modelTest.test(dat2);
////		Assert.assertEquals(1,dat2.getTimestamp());
////		Assert.assertEquals(dat2.getPrecedessors().size(), 1);
////		Assert.assertEquals((dat2.getPrecedessors()).contains(dat1), true);
////		Assert.assertEquals(0.25, dat2.getTransitionProbabilityForPrecedessor(dat1));
////
////		PointData dat3 = new PointData(f,2l);
////		modelTest.test(dat3);
////		Assert.assertEquals(2,dat3.getTimestamp());
////		Assert.assertEquals(2, dat3.getPrecedessors().size());
////		Assert.assertEquals(true, (dat3.getPrecedessors()).contains(dat1));
////		Assert.assertEquals(true, (dat3.getPrecedessors()).contains(dat2));
////		Assert.assertEquals(0.25, dat3.getTransitionProbabilityForPrecedessor(dat1));
////		Assert.assertEquals(0.25, dat3.getTransitionProbabilityForPrecedessor(dat2));
//	}
}
