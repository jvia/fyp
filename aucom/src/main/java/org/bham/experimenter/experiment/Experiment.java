package org.bham.experimenter.experiment;

import java.util.concurrent.Callable;

import org.bham.experimenter.data.Result;


public interface Experiment extends Callable<Result>{
		public void preprocess() throws Exception;
		public void process() throws Exception;
		public void postprocess() throws Exception;
}
