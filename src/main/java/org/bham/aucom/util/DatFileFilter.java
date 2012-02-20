package org.bham.aucom.util;

import java.io.File;
import java.io.FilenameFilter;

public class DatFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String fileName) {
        return fileName.endsWith(".dat");
    }

}
