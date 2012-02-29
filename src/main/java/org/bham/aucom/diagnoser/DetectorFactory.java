package org.bham.aucom.diagnoser;

import nu.xom.Document;

public interface DetectorFactory {
    public void configure(Document doc);

    public Detector create();
}
