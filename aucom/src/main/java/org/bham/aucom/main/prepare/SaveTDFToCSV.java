package org.bham.aucom.main.prepare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.io.IllegalOutputType;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;

public class SaveTDFToCSV {
	public SaveTDFToCSV(File in, File out) {
		try {
			TimeSeries<?>  tsIn = AucomIO.getInstance().readTimeSeries(in, "xml");
			AucomIO.getInstance().writeTimeSeries(tsIn, out, "csv");
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (ValidityException exception) {
			exception.printStackTrace();
		} catch (DataAlreadyExistsException exception) {
			exception.printStackTrace();
		} catch (ParsingException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (IllegalOutputType exception) {
			exception.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("usage: tdf2csv in.tdf out.csv");
			System.exit(1);
		}
		new SaveTDFToCSV(new File(args[0]), new File(args[0]));
	}
}
