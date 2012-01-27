package org.bham.app.experiment;

import java.util.concurrent.Callable;

public interface Experiment extends Callable<Result>{
		public void preprocess() throws Exception;
		public void process() throws Exception;
		public void postprocess() throws Exception;
}
