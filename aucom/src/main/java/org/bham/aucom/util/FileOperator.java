package org.bham.aucom.util;

import java.io.File;


public class FileOperator {
	private FileOperator(){
		
	}
	public static String getExtension(File file){
	    String ext;
	    int mid= file.getName().lastIndexOf(".");
	    ext=file.getName().substring(mid+1,file.getName().length());  
	    return ext;
	}
	public static String getName(File file){
		String filename="";
	    int mid= file.getName().lastIndexOf(".");
	    if(mid != -1){
	    	filename= file.getName().substring(0,mid);
	    }
		return filename;
		
	}
	public static String getPath(File file){
		String filePath;
	    int mid= file.getAbsolutePath().lastIndexOf(File.separator);
	    filePath = file.getAbsolutePath().substring(0,mid);
	    return filePath;
	}
}
