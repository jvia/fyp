package org.bham.aucom.fts.tranform;


public class TestT2GramModelTest {
//	T2GramModelImp model;
//	GenerateTemporalProbabilityFeature modelTest;
//	TimeSeriesSink<Classification> sink;
//	TimeSeriesSource<Observation> source;
//	ObservationTimeSeries intputQueue;
//	Graph g ;
//	@Before
//	public void setUp() throws Exception {
//		model = new T2GramModelImp(new KDEProbabilityFactory());
//		double[] values = {90, 190,290,390};
//		HistogramDistribution test = new HistogramDistribution("1_2", 100, values);
//		test.setInfoDebugLevel();
//		model.addDistribution(1, 2, test);
//		test = new HistogramDistribution("2_1", 100, values);
//		model.addDistribution(2, 1, test);
//		test = new HistogramDistribution("1_1", 100, values);
//		model.addDistribution(1, 1, test);
//		test = new HistogramDistribution("2_2", 100, values);
//		model.addDistribution(2, 2, test);
//		modelTest = new GenerateTemporalProbabilityFeature();
//		modelTest.setModel(model);
//		g = new Graph();
//		intputQueue =  new ObservationTimeSeries();
//		source = new TimeSeriesSource<Observation>(intputQueue, "testQueue");
//		ClassificationTimeSeries classificationTs = new ClassificationTimeSeries();
//		sink = new TimeSeriesSink<Classification>(classificationTs);
//		EncodeData encodeData = new EncodeData();
//		TemporalDurationFeatureGenerator tdfGenerator = new TemporalDurationFeatureGenerator(this.model.getDimensions());
//		GenerateTemporalDurationFeature generateDurationFeature = new GenerateTemporalDurationFeature();
//		generateDurationFeature.setGenerator(tdfGenerator);
//		g.connect(source, encodeData);
//		g.connect(encodeData, generateDurationFeature);
//		g.connect(generateDurationFeature, modelTest);
//		CalcEntropyAvgScore scorer = new CalcEntropyAvgScore(this.model);
//		Classificate classificate = new Classificate(new HysteresisAnomalyClassificator(0.0, 0.1));
//		g.connect(modelTest, scorer);
//		g.connect(scorer, classificate);
//		g.connect(classificate, sink);
//
//	}
//	@Test
//	public void testFirstT2GramData(){
//		TemporalDurationFeature dat = new TemporalDurationFeature();
//		modelTest.generate(dat);
//		Assert.assertThat(-1l,is((dat.getTimestamp())));
//
//	}

}
