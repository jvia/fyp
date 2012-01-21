package org.bham.aucom.models.probability;

import javax.swing.JPanel;

import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;


public abstract class ProbabilityInputPanel extends JPanel{
	
	private static final long serialVersionUID = -3552005289281360815L;
	public abstract ProbabilityFactory getProbabilityFactory();
	
}
