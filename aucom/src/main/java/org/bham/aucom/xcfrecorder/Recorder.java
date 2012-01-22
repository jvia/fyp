package org.bham.aucom.xcfrecorder;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.Presentable;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.*;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.system.*;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.FileOperator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.*;

public class Recorder implements Presentable, SystemConnectionStatusListener, TimeSeriesStatusListener, GraphStatusListener {

    private File folder;
    private SaveTimeSeriesGraph saveTimeSeriesGraph;
    private TimeSeries<Observation> bufferObservationTimeSeries;
    Logger logger;
    RecorderPanel panel;
    private File fileName;
    private RecorderState currentState;
    private RecorderState oldState;
    private SystemConnection systemConnection;

    public Recorder(SystemConnection inSystemConnection) {
        this(AucomIO.getInstance().getCurrentWorkingDirectory(), inSystemConnection);
    }

    public Recorder(File folder, SystemConnection inSystemConnection) {
        bufferObservationTimeSeries = new ObservationTimeSeries();
        setState(RecorderState.NOTREADY);
        oldState = RecorderState.NOTREADY;
        setSystemConnection(inSystemConnection);
        this.logger = Logger.getLogger(this.getClass().getCanonicalName());
        this.setFolder(folder);
        this.logger.info("recorder ready ...");
        setFileName(new File(""));
    }

    public boolean isReady() {
        return getState().equals(RecorderState.READY);
    }

    public void setVisible(boolean b) {
        this.panel.setVisible(b);
    }

    public boolean isVisible() {
        return this.panel.isVisible();
    }

    public String getNextFileName() {
        return "/record_" + new DecimalFormat("000").format(getNextFileNumber(this.getFolder())) + ".obs";
    }

    /**
     * starts recording to a new file.
     */
    public void record() throws ActionFailedException {
        bufferObservationTimeSeries.clear();
        this.setFileName(new File(this.getFolder().getAbsolutePath() + getNextFileName()));
        systemConnection.getObservationTimeSeries().addTimeSeriesStatusListener(this);
        saveTimeSeriesGraph = new SaveTimeSeriesGraph(bufferObservationTimeSeries, getFileName());
        saveTimeSeriesGraph.addGraphListener(this);
        saveTimeSeriesGraph.start();
        Logger.getLogger(this.getClass().getCanonicalName()).info("recording to file " + getFileName());
    }

    public boolean isRecording() {
        return currentState.equals(RecorderState.RECORDING);
    }

    public void stop() throws ActionNotPermittedException {
        System.out.println("stoping recorder buffer size " + bufferObservationTimeSeries.size());
        if (currentState.equals(RecorderState.RECORDING)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("stopping current recorder");
            if (systemConnection != null) {
                if (systemConnection.getObservationTimeSeries() != null) {
                    systemConnection.getObservationTimeSeries().removeTimeseriesStatusListener(this);
                }
            }
            saveTimeSeriesGraph.stopGraph();
        } else {
            throw new ActionNotPermittedException("no recorder to stop");
        }
    }

    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        OptionSpec<File> folder = parser.accepts("f").withRequiredArg().ofType(File.class);
        OptionSet option = parser.parse(args);
        File saveFolder = new File(System.getProperty("user.dir") + File.separator + "data");
        if (option.has(folder)) {
            File f = folder.value(option);
            if (f.exists()) {
                if (f.isDirectory()) {
                    saveFolder = f;
                } else {
                    System.err.println("couldn't find folder" + f);
                    return;
                }
            }
        }
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {

            @Override
            public String format(LogRecord arg0) {
                return "[" + arg0.getLevel() + "]" + "  Class: " + arg0.getSourceClassName() + "  Method: " + arg0.getSourceMethodName() + ": \"" + arg0.getMessage() + "\"\n";
            }
        });
        reconfigureLoggerHandler(handler);
        new Recorder(saveFolder, null);
    }

    /**
     * @param handler
     */
    private static void reconfigureLoggerHandler(ConsoleHandler handler) {
        Handler[] h = Logger.getLogger("").getHandlers();
        for (Handler aH : h) {
            Logger.getLogger("").removeHandler(aH);
        }
        Logger.getLogger("").addHandler(handler);
    }

    public int getNextFileNumber(File folder) {
        int number = 0;
        File[] files = folder.listFiles();

        for (int i = 0, filesLength = files.length; i < filesLength; i++) {
            File file = files[i];
            if (file.isFile()) {
                if (FileOperator.getExtension(file).equals("obs")) {
                    String name_noExt = FileOperator.getName(file);
                    String[] name_Number_parts = name_noExt.split("\\_");
                    if (name_Number_parts.length == 2) {
                        String name_number = name_noExt.split("\\_")[1];
                        try {
                            number = Math.max(number, new DecimalFormat("000").parse(name_number).intValue());
                        } catch (ParseException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }
        return number + 1;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileName
     */
    public File getFileName() {
        return this.fileName;
    }

    public void startNewFile() throws ActionNotPermittedException, ActionFailedException {
        stop();
        record();
    }

    public void changeFolder(File newFolder) throws ActionNotPermittedException {
        this.setFolder(newFolder);
    }

    /**
     * @param folder the folder to set
     */
    private void setFolder(File folder) {
        this.folder = folder;
    }

    /**
     * @return the folder
     */
    public File getFolder() {
        return this.folder;
    }

    public int getNumberRecordedEvents() {
        if (this.saveTimeSeriesGraph == null) {
            return 0;
        }
        return this.saveTimeSeriesGraph.getNumberRecordedEvents();
    }

    public void setTimeSeries(TimeSeries<Observation> bufferObservationTimeSeries) {
        this.bufferObservationTimeSeries = bufferObservationTimeSeries;
    }

    public TimeSeries<Observation> getTimeSeries() {
        return this.bufferObservationTimeSeries;
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new RecorderPanel(this);
            panel.setName("Recorder");
            panel.setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, 180));
        }
        return panel;
    }

    public void setSystemConnection(SystemConnection inSystemConnection) {
        if (inSystemConnection != null) {
            if (systemConnection != null) {
                if (currentState.equals(RecorderState.RECORDING)) {
                    try {
                        stop();
                    } catch (ActionNotPermittedException e) {
                        logger.finest("coudn't stop recorder although the state was running. This indicates a bug");
                    }
                }
                systemConnection.removeSystemConnectionStatusListener(this);
                setState(RecorderState.NOTREADY);
            }
            systemConnection = inSystemConnection;
            systemConnection.addSystemConnectionStatusListener(this);
            if (systemConnection.getConnectionStatus().equals(SystemConnectionStatus.CONNECTED)) {
                setState(RecorderState.READY);
            }
        }
    }

    public SystemConnection getSystemConnection() {
        return systemConnection;
    }

    /*
      * event handling ---->
      */

    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    public void addRecorderStatusListener(RecorderStatusListener listener) {
        this.listenerList.add(RecorderStatusListener.class, listener);
    }

    public void removeRecorderStatusListener(RecorderStatusListener listener) {
        this.listenerList.remove(RecorderStatusListener.class, listener);
    }

    // This method is used to fire TrainingStatusChangedEvents
    void fireRecorderStatusChangedEvent(RecorderStatusChangedEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SystemConnectionStatusListener.class) {
                ((RecorderStatusListener) listeners[i + 1]).handleRecorderStatusEvent(evt);
            }
        }
    }

    /*
      * <---- event handling
      */

    public SystemConnectionInfo getInfo() {
        return null;
    }

    private void setState(RecorderState inState) {
        oldState = currentState;
        currentState = inState;
        fireRecorderStatusChangedEvent(new RecorderStatusChangedEvent(this, oldState, currentState));

    }

    public RecorderState getState() {
        return currentState;
    }

    public RecorderState getOldState() {
        return oldState;
    }

    @Override
    public void handleSystemConnectionEvent(SystemConnectionStatusChangedEvent event) {
        if (event.getOldStatus().equals(SystemConnectionStatus.DISCONNECTED) && event.getNewStatus().equals(SystemConnectionStatus.CONNECTED)) {
            if (getState().equals(RecorderState.RECORDING)) {
                logger.finest("got notification about SystemConnection while recording. This indicates a bug");
            }
            setState(RecorderState.READY);
        }
        if (event.getOldStatus().equals(SystemConnectionStatus.CONNECTED) && event.getNewStatus().equals(SystemConnectionStatus.DISCONNECTED)) {
            if (getState().equals(RecorderState.RECORDING)) {
                try {
                    stop();
                } catch (ActionNotPermittedException e) {
                    logger.finest("coudn't stop recorder although the state was running. This indicates a bug");
                }
            }
            setState(RecorderState.NOTREADY);
        }
    }

    @Override
    public void graphStatusChanged(GraphStateChangedEvent evt) {
        logger.info("graph state change from " + evt.getPreviousState() + " to " + evt.getNewState());
        if (evt.getPreviousState().equals(GraphStatus.RUNNING) && evt.getNewState() == GraphStatus.READY) {
            saveTimeSeriesGraph.stop();
            saveTimeSeriesGraph.removeMyEventListener(this);
            saveTimeSeriesGraph = null;
            if (systemConnection.isConnected()) {
                setState(RecorderState.READY);
            } else {
                setState(RecorderState.NOTREADY);
            }
        } else if (evt.getPreviousState().equals(GraphStatus.READY) && evt.getNewState() == GraphStatus.RUNNING) {
            setState(RecorderState.RECORDING);
        }

    }

    @Override
    public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
        if (status.getStatus().equals(TimeseriesStatus.ELEMENTSADDED)) {
            for (int i = status.getStartIndex(); i <= status.getEndIndex(); i++) {
                bufferObservationTimeSeries.add(systemConnection.getObservationTimeSeries().get(i));
            }
        }
    }

}