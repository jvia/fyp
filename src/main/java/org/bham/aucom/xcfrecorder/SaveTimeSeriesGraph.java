package org.bham.aucom.xcfrecorder;

import net.sf.xcf.fts.engine.EngineThread;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.*;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.sink.ObservableStreamSink;
import org.bham.aucom.fts.sink.SinkStatusListener;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.MarkNextDataPointAsLast;
import org.bham.aucom.util.Constants;

import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;

public class SaveTimeSeriesGraph extends AbstractAucomGraph implements SinkStatusListener, TimeSeriesStatusListener {
    private static final long serialVersionUID = 0L;
    private static final String OPENING_TAG_PART1 = "<ts:timeseries id=\"";
    private static final String OPENING_TAG_PART2 = "\" type=\"obs\" xmlns:ts=\"" + Constants.URI + "\" ><ts:elements>\n";
    private static final String XML_VERSION = "<?xml version=\"1.0\"?>\n";
    private static final String CLOSING_TAG = "</ts:elements></ts:timeseries>";
    EngineThread engineThread;
    transient OutputStream outputStream;

    TimeSeriesSource<Observation> source;
    Counter<Observation> counter;
    MarkNextDataPointAsLast<Observation> marker;
    ObservableStreamSink<Observation> sink;

    private TimeSeries<Observation> timeSeriesToSave;

    public SaveTimeSeriesGraph(TimeSeries<Observation> timeSeriesToSave, File fileToSaveTo) {
        super("SaveTimeSeriesGraph");
        try {
            Logger.getLogger(this.getClass().getCanonicalName()).info("saving to " + fileToSaveTo);
            this.outputStream = new FileOutputStream(fileToSaveTo);
            this.setTimeSeriesToSave(timeSeriesToSave);
            this.getTimeSeriesToSave().addTimeSeriesStatusListener(this);
            writeOpeningTag(this.outputStream);
            initGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initGraph() {
        source = new TimeSeriesSource<Observation>("observationTimeSeriesSource");
        source.setInput(new ObservationTimeSeries());
        counter = new Counter<Observation>();
        sink = new ObservableStreamSink<Observation>(this.outputStream);
        marker = new MarkNextDataPointAsLast<Observation>();
        sink.addSinkStatusListener(this);
        graph.connect(this.source, this.counter);
        graph.connect(this.counter, marker);
        graph.connect(marker, this.sink);
    }

    public int getNumberRecordedEvents() {
        return this.counter.getCounter();
    }

    private void writeOpeningTag(OutputStream outputStream) throws IOException {
        Logger.getLogger(this.getClass().getCanonicalName()).info("writing opening tag called  ... <?xml version=\"1.0\"?> <events>");
        outputStream.write(XML_VERSION.getBytes()); // wirting first
        // element of the recorded data
        String tag = generateTiemSeriesOpeningTag();
        outputStream.write(tag.getBytes()); // wirting first element of the
        // recorded data
    }

    private String generateTiemSeriesOpeningTag() {
        return OPENING_TAG_PART1 + UUID.randomUUID() + OPENING_TAG_PART2;
    }

    private void writeClosingTag(OutputStream outputStream) throws IOException {
        Logger.getLogger(this.getClass().getCanonicalName()).info("writing closing tag: ... </events>");
        outputStream.write(generateTimeSeriesClosingTag().getBytes());
    }

    private String generateTimeSeriesClosingTag() {
        return CLOSING_TAG;
    }

    public void stopGraph() {
        synchronized (source) {
            if (source.size() != 0) {
                Logger.getLogger(this.getClass().getCanonicalName()).info("marking next element as last");
                marker.setMarkNextElementAsLast();
            } else {
                setStatus(GraphStatus.READY);
            }
        }
    }

    @Override
    public void timeseriesStatusChanged(TimeSeriesStatusEvent status) {
        if (status.getStatus().equals(TimeSeriesStatus.ELEMENTS_ADDED)) {
            for (int i = status.getStartIndex(); i <= status.getEndIndex(); i++) {
                this.source.getInput().add(this.getTimeSeriesToSave().get(i));
            }
        }
    }

    @Override
    protected void cleanUp() {
        try {
            writeClosingTag(this.outputStream);
            this.outputStream.close();
            Logger.getLogger(this.getClass().getCanonicalName()).info(" file closed!");
            this.sink.removeAucomSinkStatusListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preconditionsSatisfied() {
        return getTimeSeriesToSave() != null;
    }

    @Override
    protected String getReason() {
        String s = "";
        if (!preconditionsSatisfied()) {
            s = "\n timeseries to save is null \n";
        }
        return s;
    }

    public TimeSeries<Observation> getTimeSeriesToSave() {
        return timeSeriesToSave;
    }

    public void setTimeSeriesToSave(TimeSeries<Observation> timeSeriesToSave) {
        this.timeSeriesToSave = timeSeriesToSave;
    }

}
