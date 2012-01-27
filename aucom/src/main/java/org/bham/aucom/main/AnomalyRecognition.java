package org.bham.aucom.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;

public class AnomalyRecognition {
	T2GramModelImp normalModel;
	File normalModelFile = new File("/home/rgolombe/workspace/org.bham.aucom/data/tobi/session_iros10/model/hist100_norm134.md");
	
	T2GramModelImp cpuModel;
	File cpuModelFile = new File("");
	
	T2GramModelImp asyncModel;
	File asyncModelFile = new File("");
	
	T2GramModelImp prbfslamModel;
	File prbfslamModelFile = new File("");
	T2GramModelImp playerModel;
	File playerModelFile = new File("");
	public AnomalyRecognition() {
		trainModels();
	}
	public void trainModels(){
		normalModel = loadModel(normalModelFile);
		cpuModel = loadModel(cpuModelFile);
		asyncModel = loadModel(asyncModelFile);
		prbfslamModel = loadModel(prbfslamModelFile);
		playerModel = loadModel(playerModelFile);
	}
	public void test(File f){
		
	}
	
	public static void main(String[] args) {

	}
//	public void save(File f, ScoreSequence sequence){
//		System.out.println("saving to : " +f);
//		FileWriter writer;
//		DecimalFormat formatter = new DecimalFormat("0.00000000"); 
//		try {
//			writer = new FileWriter(f);
//			for (int i = 0; i< sequence.size(); i++) {
//				Score s = sequence.get(i);
//				String outpurString = "";
//				outpurString += s.getTimestamp();
//				outpurString += ";";
//				try {
//					outpurString += formatter.parse(formatter.format(s.getValue()));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				outpurString += ";";
//				outpurString += s.getAbnormal();
//				outpurString += ";";
//				outpurString += s.getClassId();
//				outpurString += "\n";
//				writer.write(outpurString);
//			}
//			writer.close();
//		} catch (IOException e) {
//			Logger.getLogger(this.getClass().getCanonicalName())
//					.severe(
//							"couldn't write to file "
//									+ f.getAbsolutePath());
//		}
//}
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
