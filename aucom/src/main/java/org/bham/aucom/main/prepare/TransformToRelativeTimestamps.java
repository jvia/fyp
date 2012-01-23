package org.bham.aucom.main.prepare;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.sf.xcf.fts.engine.EngineThread;
import net.sf.xcf.fts.engine.Graph;

import java.io.File;
public class TransformToRelativeTimestamps {
	private Graph graph;
	private EngineThread thread;
	private TransformToRelativeTimestamps(File in, File out) {
		// TODO fix this here

//		ArrayList<DataType> list;
//		try {
//			list = ObservationLoader.getInstance()
//			.extract(in);
//			this.graph = new Graph().getConnector(new SequenceQueueSource<DataType>(new DataTypeTimeSeries(list,""), SOURCE)).
//			connect(new SubClassFilter<AbstractData, PointData>(PointData.class)).
//			connect(new CropTimestampFromData()).
//			connect(new RawDataToCsvRow()).
//			connect(new PosToFixToString("\n")).
//			connect(new StringToBufferOutputEvent()).
//			connect(new OutputStreamSinkStrip(new FileOutputStream(out))).getGraph();
//		} catch (FileNotFoundException e) {
//			System.out.println("file: " + in + " not found");
//			System.exit(0);
//		}
	}

	void run() {
		this.thread = new EngineThread(this.graph);
		this.thread.start();
	}
	public static void main(String[] args) {
		OptionParser parser = new OptionParser();
		OptionSpec<File> f = parser.accepts("f").withRequiredArg().ofType(
				File.class);
		OptionSpec<File> o = parser.accepts("o").withRequiredArg().ofType(
				File.class);
		OptionSet option = parser.parse(args);
		if (!option.has(f) || !option.has(o)) {
			System.out.println("No file found on command line");
			return;
		}
			File in = f.value(option);
			File out = o.value(option);
			TransformToRelativeTimestamps transform = new TransformToRelativeTimestamps(in, out);
			transform.run();
			try {
				transform.thread.join();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
