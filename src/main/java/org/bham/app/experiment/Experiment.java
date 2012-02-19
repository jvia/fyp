package org.bham.app.experiment;

import java.util.concurrent.Callable;

public interface Experiment extends Callable<Result> {
    public void preprocess();

    public void process();

    public void postprocess();


}
