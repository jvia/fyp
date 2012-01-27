package org.bham.aucom.data.io;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;

public class FaultDetectionModelIOTest {
	File tmpFile = null;
	Model model = null;
	ModelIO io = new ModelIO();

	@Before
	public void setUp() throws Exception {
		this.model = createTestModel();
		this.tmpFile = AucomIO.getInstance().generateFileFor(this.model.getId(), "ml");
		System.out.println(this.tmpFile.getAbsolutePath());
	}

	private Model createTestModel() {
		Model model = new T2GramModelImp(new KDEProbabilityFactory());
//		ModelTrainer trainer  = new T2GramModelTrainer();
//		trainer.putTrainingData(DataType.createRandomDataType());
//		trainer.putTrainingData(DataType.createRandomDataType());
//		trainer.putTrainingData(DataType.createRandomDataType());
//		try {
//			trainer.start(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return model; 
	}

	@Test
	public void testWrite() {
		try {
			this.io.write(this.model, this.tmpFile);
		} catch (Exception exception) {
			exception.printStackTrace();
			fail();
		}
	}
	@Test
	public void testPlainRead(){
		try {
			UUID tmp = this.model.getId();
			String name = this.model.getName();
			File f = new File("tmp.ser");
			ObjectOutputStream objectOutputStream;
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
			objectOutputStream.writeObject(this.model);
			objectOutputStream.flush();
			objectOutputStream.close();
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
			Model model2 = (Model)objectInputStream.readObject();
			Assert.assertEquals(tmp, model2.getId());
			Assert.assertEquals(name, model2.getName());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testRead() {
		try {
			UUID tmp = this.model.getId();
			String name = this.model.getName();
			this.io.write(this.model, this.tmpFile);
			Model model2 = this.io.read(this.tmpFile);
			Assert.assertEquals(tmp, model2.getId());
			Assert.assertEquals(name, model2.getName());
//
//			T2GramFaultDetectionModel model3 = (T2GramFaultDetectionModel) this.io.read(new File("/homes/rgolombe/workspace/aucom_ndft/92be9010-e79e-4465-9962-2ce4fbe963cf.ml"));
//			Assert.assertTrue(model3.getModel().isTrained());
//			Assert.assertTrue(model3.getModel().getNumberAvailableDistirbutions() > 0);
			
			
			//this.model = new T2GramFaultDetectionModel(new T2GramModelConfiguration("tmpModel", new KDEProbabilityFactory(200.0), new HysteresisAnomalyClassificator(0.5, 0.99), new SlidingWindow(100, 50)));			
		} catch (Exception exception) {
			exception.printStackTrace();
			fail(exception.getLocalizedMessage());
		}
	}
	
	@After
	public void cleanUp(){
//		this.tmpFile.delete();
	}

}
