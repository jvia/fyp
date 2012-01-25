package org.bham.system.playfile;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JPanel;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.aucom.util.Constants;

public class PlayFileSystemConnection extends SystemConnection {
    private File file;
    private PlayFileGraph graph;

    public PlayFileSystemConnection() {
        super("ReplayFileSystemConnection");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void iConnect() throws SystemConnectionFailedException {
        if (isConnected()) {
            return;
        }
        if (getFile() == null) {
            throw new SystemConnectionFailedException("no file to play");
        }
        graph = new PlayFileGraph();
        try {
            graph.setInput((TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(getFile()));
        } catch (Exception exception) {
            throw new SystemConnectionFailedException(exception.getLocalizedMessage());
        }
        try {
            graph.start();
        } catch (ActionFailedException exception) {
            throw new SystemConnectionFailedException("couldn't start XcfPlayGraph, reason:" + exception.getMessage());
        }
    }

    public int getNumElements() {
        if (graph != null && graph.getInput() != null) {
            return graph.getInput().size();
        }
        return 0;
    }

    public int getProgress() {
        if (graph != null && graph.getObservationTimeSeries() != null) {
            return graph.getObservationTimeSeries().size();
        }
        return 0;
    }

    @Override
    public void iDisconnect() {
        if (!isConnected()) {
            return;
        }
        graph.stop();
        graph = null;
    }

    @Override
    public TimeSeries<Observation> getObservationTimeSeries() {
        if (graph == null) {
            return null;
        }
        if (graph.getObservationTimeSeries() == null) {
            return null;
        }
        return graph.getObservationTimeSeries();
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new PlayFileSystemConnectionPanel(this);
            panel.setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, Constants.DEFAULT_PRESENTABLE_HEIGHT));
            panel.setName(name);
        }
        return panel;
    }

    File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
