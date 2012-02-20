package org.bham.aucom.main.prepare;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bham.aucom.fts.graph.DataTypeExtractionGraph;

public class GetDataTypesFromObsTimeSeries {
	DataTypeExtractionGraph g; 
	public GetDataTypesFromObsTimeSeries(List<File> files) {
		g = new DataTypeExtractionGraph();
	}
	public static void main(String[] args) {
		if(args.length>0){
			List<File> files = new LinkedList<File>(); 
			for(int i = 0; i < args.length;i++){
				files.add(new File(args[i]));
			}
		new GetDataTypesFromObsTimeSeries(files);
		}
	}

}
