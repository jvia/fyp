package org.bham.aucom.diagnoser.t2gram.visualizer;

import org.bham.aucom.Presentable;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.gui.charts.VisualizerScoreChart;
import org.bham.aucom.util.Constants;

import javax.swing.*;
import java.awt.*;

public class T2GramVisualizer implements Presentable {
    JPanel panel;
    T2GramDetector detector;
    JFrame scoreChartFrame;
    VisualizerScoreChart scoreChart;
    T2GramVisualizerGraph graph;

    public T2GramVisualizer(T2GramDetector inDetector) {
        detector = inDetector;
    }

    public void start() throws ActionFailedException {
        if (detector != null && detector.getOutput() != null) {
            graph = new T2GramVisualizerGraph(detector.getOutput());
            graph.start();
        } else {
            throw new ActionFailedException("detector not ready");
        }
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new T2GramVisualizerPanel(this);
            panel.setName("Visualizer");
            panel.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 40));

        }
        return panel;
    }

    public boolean scoreChartIsVisisble() {
        return scoreChartFrame != null && scoreChartFrame.isVisible();
    }

    public void showScoreChart() {
        System.out.println("showScoreChart");
        try {
            if (scoreChart == null) {
                start();
                scoreChart = new VisualizerScoreChart();
                scoreChart.setPreferredSize(new Dimension(800, 550));
                scoreChartFrame = new JFrame("Score Visualization");
                scoreChartFrame.setSize(new Dimension(800, 600));
                scoreChartFrame.setLayout(new FlowLayout());
                scoreChartFrame.add(scoreChart);
            }
            scoreChart.registerData(graph.getOuput());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scoreChartFrame.setVisible(true);
                }
            });
        } catch (ActionFailedException exception) {
            exception.printStackTrace();
        }
    }

    public void hideScoreChart() {
        if (scoreChart == null) {
            return;
        }
        scoreChart.unregisterData(graph.getOuput());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scoreChartFrame.setVisible(false);
            }
        });
    }
}
