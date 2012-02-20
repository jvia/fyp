package org.bham.aucom.data.management;

import java.io.File;
import java.util.UUID;

public interface Loader {
	public abstract UUID load(File file);
}
