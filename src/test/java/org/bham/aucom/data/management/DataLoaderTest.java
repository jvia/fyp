package org.bham.aucom.data.management;


import junit.framework.Assert;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class DataLoaderTest {
	
	
	// TestFiles
	File dir = new File("data"+File.separatorChar+"unittestdata"+File.separatorChar);
	File scoreFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTScoreSeries.score");
	File obsevrationFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTObservationTimeSeries.obs");
	File tdfFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTTemporalDurationFeatureTimeSeries.tdf");
	File tpfFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTTemporalProbabilityFeatureTimeSeries.tpf");
	File dataTypeFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTDataTypeTimeSeries.dtp");
	File classificationFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTClassificationTimeSeries.cl");
	// TestFiles
	
	
	AucomIO loader = AucomIO.getInstance();
	@Before
	public void setUp() throws Exception {
		DataModel.getInstance().clear();
		System.out.println(this.scoreFile.getAbsolutePath());
		Assert.assertTrue(this.scoreFile.exists());
	}
	@Test
	public void loadScoreTest(){
		UUID id;
		try {
			id = this.loader.readTimeSeries(this.scoreFile).getId();
			Assert.assertNotNull(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			Assert.fail("exception caught");
		} 
	}
	@Test
	public void loadObservationDataTest(){
		UUID id;
		try {
			id = this.loader.readTimeSeries(this.obsevrationFile).getId();
			Assert.assertNotNull(id);
			Assert.assertEquals(1, DataModel.getInstance().getNumerTimeseries());
			TimeSeries<?> ts = DataModel.getInstance().getTimeSeriesById(id);
			Assert.assertNotNull(ts);
			Assert.assertEquals(id, ts.getId());
			try {
				@SuppressWarnings("unchecked")
				TimeSeries<Observation> ts2 = (TimeSeries<Observation>)ts;
				Assert.assertNotNull(ts2);
			} catch (Exception exception) {
				Assert.fail("timeseries is not of type observation");
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			Assert.fail("exception caught");
		} 
	}
	@Test
	public void exceptionsTest(){
		boolean exceptionCatched = false;
		try {
			this.loader.readTimeSeries(new File("")).getId();
		} catch (FileNotFoundException exception) {
			exceptionCatched = true;
		} catch (ValidityException exception) {
		} catch (ParsingException exception) {
		} catch (IOException exception) {
		}
		Assert.assertTrue(exceptionCatched);
		
	}
	

}
