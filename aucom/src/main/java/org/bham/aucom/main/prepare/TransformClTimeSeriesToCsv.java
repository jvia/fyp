package org.bham.aucom.main.prepare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.sf.xcf.fts.engine.EngineThread;
import net.sf.xcf.fts.engine.Graph;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.sink.OutputStreamSinkStrip;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.PostofixToString;
import org.bham.aucom.fts.tranform.RawDataToCsvRow;
import org.bham.aucom.fts.tranform.StringToBufferOutputEvent;
public class TransformClTimeSeriesToCsv {
	Graph g;
	private EngineThread engineThread;
	public TransformClTimeSeriesToCsv() {
	}

	public TransformClTimeSeriesToCsv(File in_file, File out_file) {
			UUID id;
				try {
					@SuppressWarnings("unchecked")
					TimeSeries<Classification> cl = (TimeSeries<Classification>)AucomIO.getInstance().readTimeSeries(in_file);
							this.g = new Graph().getConnector(new TimeSeriesSource<Classification>(cl, "cl_src")).
							connect(new RawDataToCsvRow()).
							connect(new PostofixToString("\n")).
							connect(new StringToBufferOutputEvent()).
							connect(new OutputStreamSinkStrip(new FileOutputStream(out_file))).
							getGraph();
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
						}	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OptionParser parser = new OptionParser();
		OptionSpec<File> f = parser.accepts("f").withRequiredArg().ofType(
				File.class);
		OptionSpec<File> o = parser.accepts("o").withRequiredArg().ofType(
				File.class);

		OptionSet option = parser.parse(args);
		if (!option.has(f) || !option.has(o)) {
			System.out.println("usage: -f classificationFile.cl -o outputfile.csv");
			return;
		}
			File in = f.value(option);
			File out = o.value(option);
			if(!in.exists()){
				System.out.println("input file "+in.getAbsolutePath()+"  not found ");
				return ; 
			}
			TransformClTimeSeriesToCsv t = new TransformClTimeSeriesToCsv(in, out);
			t.run();
			try {
				t.engineThread.join();
			} catch (InterruptedException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
	}
	private void run() {
		engineThread = new EngineThread(g);
		engineThread.start();
	}
}
