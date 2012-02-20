package org.bham.aucom.util;

import java.io.File;
import java.io.FileFilter;

public class ExtensionFileFilter implements FileFilter {
    private final String ext;

    public ExtensionFileFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File pathname) {
        String extension = FileOperator.getExtension(pathname);
        return getExt().equals(extension);
    }

    private String getExt() {
        return ext;
    }

}
