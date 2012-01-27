package org.bham.aucom.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.bham.aucom.data.RangeScoreTest;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoderTest;
import org.bham.aucom.data.io.xml.in.converter.XmlToClassificationTimeSeriesConverterTest;
import org.bham.aucom.data.io.xml.in.converter.XmlToScoreTimeSeriesConverterTest;
import org.bham.aucom.data.io.xml.in.converter.XmlToTemporalDurationFeatureTimeSeriesConverterTest;
import org.bham.aucom.data.io.xml.out.converter.ClassificationTimeSeriesToXmlConverterTest;
import org.bham.aucom.data.io.xml.out.converter.DataTypeTimSeriesToXmlConverterTest;
import org.bham.aucom.data.io.xml.out.converter.ObservationTimeSeriesToXmlConverterTest;
import org.bham.aucom.data.io.xml.out.converter.ScoreTimeSeriesToXmlConverterTest;
import org.bham.aucom.data.io.xml.out.converter.TemporalDurationTimeSeriesFeatureToXmlConverterTest;
import org.bham.aucom.data.io.xml.out.converter.TemporalProbabilityFeatureTimeSeriesToXmlConverterTest;
import org.bham.aucom.data.management.TimeSeriesLoaderTest;
import org.bham.aucom.data.management.XmlToDatatypeTimeSeriesConverterTest;
import org.bham.aucom.data.management.XmlToObservationTimeSeriesConverterTest;
import org.bham.aucom.data.timeseries.TimeSereriesDataTypeTest;
import org.bham.aucom.data.util.DataManagerTest;
import org.bham.aucom.fts.graph.DetectorGraphTest;
import org.bham.aucom.fts.source.TimeSeriesSourceTest;
import org.bham.aucom.fts.tranform.CalcMeanvalueTest;
import org.bham.aucom.fts.tranform.CropTimestampFromDataTest;
import org.bham.aucom.fts.tranform.TestT2GramModelTest;
import org.bham.aucom.models.probability.HistogramDistributionTest;
import org.bham.aucom.models.probability.KernelDensityDistributionTest;
import org.bham.aucom.models.probability.LogarithmicBinCalculatorTest;
import org.bham.aucom.util.HysteresisThresholdTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
					TimeSeriesLoaderTest.class,
					ObservationTimeSeriesToXmlConverterTest.class,
					XmlToObservationTimeSeriesConverterTest.class,

					DataTypeTimSeriesToXmlConverterTest.class,
					XmlToDatatypeTimeSeriesConverterTest.class,
					
					TemporalDurationTimeSeriesFeatureToXmlConverterTest.class,
					XmlToTemporalDurationFeatureTimeSeriesConverterTest.class,
					
					TemporalProbabilityFeatureTimeSeriesToXmlConverterTest.class,
					XmlToTemporalDurationFeatureTimeSeriesConverterTest.class,
					
					ScoreTimeSeriesToXmlConverterTest.class, 
					XmlToScoreTimeSeriesConverterTest.class, 
					
					ClassificationTimeSeriesToXmlConverterTest.class, 
					XmlToClassificationTimeSeriesConverterTest.class,

					DataManagerTest.class, 
					RangeScoreTest.class,
					LogarithmicBinCalculatorTest.class,
					TimeSereriesDataTypeTest.class,
					DetectorGraphTest.class, 
					TimeSeriesSourceTest.class, 
					CalcMeanvalueTest.class, 
					CropTimestampFromDataTest.class, 
					TestT2GramModelTest.class, 
					HistogramDistributionTest.class, 
					KernelDensityDistributionTest.class, 
					HysteresisThresholdTest.class, 
					LogarithmicBinCalculatorTest.class,
					SourceScopeTypeEncoderTest.class
	})
public class AucomTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite(AucomTestSuite.class.getName());
		//$JUnit-BEGIN$

	
		//$JUnit-END$
		return suite;
	}

}
