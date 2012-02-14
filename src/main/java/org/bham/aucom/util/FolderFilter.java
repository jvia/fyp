package org.bham.aucom.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FolderFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return f != null && f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "folder filter only accepts file descriptors which point to folders. null is permitted";
	}

}
