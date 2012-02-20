package org.bham.aucom.diagnoser;

import org.bham.aucom.Presentable;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;

public interface ModelTrainer extends Presentable {
    /*
      * @input: the model to be trained
      */

    public void start(TimeSeries<Observation> trainingData) throws Exception;

    public void stop() throws Exception;


    public void addModelTrainerListener(ModelTrainerListener listener);

    public void removeModelTrainerListener(ModelTrainerListener listener);

    public void removeAllListeners();

    /*
      * @input: new training data which is than used to train a model
      */

    public Model getModel();

    public void setModel(Model inModel) throws ClassCastException;


    /*
      * resets the knowledge of this ModelTrainer object
      */
    public void reset();

}
