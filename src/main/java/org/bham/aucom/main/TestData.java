package org.bham.aucom.main;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.sf.xcf.fts.engine.EngineThread;
import net.sf.xcf.fts.engine.Graph;
import org.bham.aucom.data.Observation;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestData {
    T2GramModelImp model;
    Graph graph;
    private boolean running;
    LinkedBlockingQueue<Observation> list;
    private EngineThread thread;
    ScheduledExecutorService service;

    public TestData(File in, File mfile, File out) {
//  try {
//   running = false;
//   model = T2GramModel.loadModel(mfile);
//   list = new LinkedBlockingQueue<RawData>(DataExtractor.getInstance()
//     .extract(in));
//   graph = new Graph().getConnector(new ObservableQueueSource<RawData>(list, TEST_SOURCE)).
//   connect(new ClassifyRawData(new BasicClassifier(new File("/home/rgolombe/classes2.txt")))).
//   connect(new TestT2GramModel(model)).
//   connect(new CalcEntropyAvgScore(model)).
//   connect(new ScoreToString()).
//   connect(new StringToBufferOutputEvent()).
//   connect(new OutputStreamSinkStrip(new FileOutputStream(out))).getGraph();
//
//   } catch (FileNotFoundException e) {
//    e.printStackTrace();
//    return;
//   }
    }

    private void run() {
        System.out.println("start");
        System.out.println("-->");
        System.out.println(model);
        System.out.println("<--");
        thread = new EngineThread(graph);
        thread.start();
        running = true;
        service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                quit();
            }
        }, 100, 500, TimeUnit.MILLISECONDS);
    }

    public void quit() {
        if (running) {
            if (list.size() == 0) {
                thread.interrupt();
                running = false;
                service.shutdown();
                System.out.println("data tested");
            }
        }
    }

    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        OptionSpec<File> t = parser.accepts("t").withRequiredArg().ofType(
                File.class);
        OptionSpec<File> m = parser.accepts("m").withRequiredArg().ofType(
                File.class);
        OptionSpec<File> o = parser.accepts("o").withRequiredArg().ofType(
                File.class);

        OptionSet option = parser.parse(args);
        if (!option.has(t) || !option.has(o) || !option.has(m)) {
            System.out.println("No file found on command line");
            System.exit(0);
        }
        File testFile = t.value(option);
        File model = m.value(option);
        File out = o.value(option);

        TestData testData = new TestData(testFile, model, out);
        testData.run();
    }

}
