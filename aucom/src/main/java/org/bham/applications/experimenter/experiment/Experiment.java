package org.bham.applications.experimenter.experiment;

import org.bham.applications.experimenter.data.Result;

import java.util.concurrent.Callable;


public interface Experiment extends Callable<Result> {
    public void preprocess() throws Exception;

    public void process() throws Exception;

    public void postprocess() throws Exception;
}
