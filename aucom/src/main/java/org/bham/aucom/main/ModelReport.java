package org.bham.aucom.main;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.util.Tuple;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class ModelReport {

    public static void main(String[] args) {
        if (args.length == 0)
            return;
        Logger.getLogger("loading model from " + args[0]);
        Model model = new ModelReport().loadModel(new File(args[0]));
        ArrayList<Tuple<Integer, Integer>> tuple = model.getTransitionMatrix().keySet();
        ProbabilityDistribution prob = model.getTransitionMatrix().get(tuple.get(0).getFirst(), tuple.get(0).getSecond());
        Logger.getLogger(ModelReport.class.getCanonicalName()).info("Distribution: " + prob.getClass().getCanonicalName());
    }

    private Model loadModel(File file) {
        ObjectInputStream in;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            return (Model) in.readObject();
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
