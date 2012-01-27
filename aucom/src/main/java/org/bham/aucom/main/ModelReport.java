package org.bham.aucom.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.util.Tupel;

public class ModelReport {

	public static void main(String[] args) {
		if(args.length == 0)
			return;
		Logger.getLogger("loading model from " + args[0]);
		T2GramModelImp model = new ModelReport().loadModel(new File(args[0]));
		ArrayList<Tupel<Integer, Integer>> tupel = model.getTransitionMatrix().keySet();
		ProbabilityDistribution prob = model.getTransitionMatrix().get(tupel.get(0).getFirstElement(), tupel.get(0).getSecondElement());
		Logger.getLogger(new ModelReport().getClass().getCanonicalName()).info("Distribution: " + prob.getClass().getCanonicalName());
	}
	private T2GramModelImp loadModel(File file){
		ObjectInputStream in;
		 
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			T2GramModelImp m = (T2GramModelImp) in.readObject();
			return m;
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
