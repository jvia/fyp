package org.bham.aucom.util;

import java.io.File;


public class FileOperator {
	private FileOperator(){
		
	}
	public static String getExtension(File file){
	    String ext="";
	    int mid= file.getName().lastIndexOf(".");
	    ext=file.getName().substring(mid+1,file.getName().length());  
	    return ext;
	}
	public static String getName(File file){
		String fname="";
	    int mid= file.getName().lastIndexOf(".");
	    if(mid != -1){
	    	fname= file.getName().substring(0,mid);
	    }
		return fname;
		
	}
	public static String getPath(File file){
		String fpath="";
	    int mid= file.getAbsolutePath().lastIndexOf(File.separator);
	    fpath = file.getAbsolutePath().substring(0,mid);
	    return fpath;
	}
}
