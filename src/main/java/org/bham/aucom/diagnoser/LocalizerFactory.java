package org.bham.aucom.diagnoser;

import nu.xom.Document;

public interface LocalizerFactory  {
	public void configure(Document doc);
	public Localizer create();
}
