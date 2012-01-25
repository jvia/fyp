package org.bham.aucom.data.io;

import nu.xom.ParsingException;

import java.io.File;
import java.io.IOException;

public interface IOInterface<T> {
    public T read(File fileToLoad) throws ParsingException, IOException;

    public boolean write(T timeSeriesToWrite, File fileToWriteTo);
}
