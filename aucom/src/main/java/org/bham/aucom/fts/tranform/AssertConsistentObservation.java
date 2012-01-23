package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.ConsistentObservationChecker;
import org.bham.aucom.data.Observation;

public class AssertConsistentObservation extends AbstractAucomTransformNode<Observation, Observation> {
	private final ConsistentObservationChecker checker;
	public AssertConsistentObservation() {
		super("AssertConsistentObservation");
		this.checker = new ConsistentObservationChecker();
	}

	@Override
	protected Observation iTransform(Observation input) throws Exception {
		if(checker.notConsistent(input)){// TODO fire events here, be more explicit what went wrong
			System.err.println("observation icnonsistent");
		}
		return input;
	}

}
