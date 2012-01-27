package org.bham.aucom.diagnoser;

import nu.xom.Document;

public interface IdentifierFactory {
	public void configure(Document doc);
	public Detector create(); 
}
