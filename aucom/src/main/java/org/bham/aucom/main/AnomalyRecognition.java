package org.bham.aucom.main;

import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;

import java.io.*;


public class AnomalyRecognition {
    T2GramModelImp normalModel;
    File normalModelFile = new File("/home/rgolombe/workspace/aucom/data/tobi/session_iros10/model/hist100_norm134.md");

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

    public void trainModels() {
        normalModel = loadModel(normalModelFile);
        cpuModel = loadModel(cpuModelFile);
        asyncModel = loadModel(asyncModelFile);
        prbfslamModel = loadModel(prbfslamModelFile);
        playerModel = loadModel(playerModelFile);
    }

    private T2GramModelImp loadModel(File file) {
        ObjectInputStream in;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            return (T2GramModelImp) in.readObject();
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
