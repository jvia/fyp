package org.bham.aucom.data.io;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IOInterface<T> {
    public T read(File fileToLoad) throws FileNotFoundException, ValidityException, ParsingException, IOException;

    public boolean write(T timeSeriesTowrite, File fileToWriteTo);
}
