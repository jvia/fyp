package org.bham.aucom.gui;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.encoder.Encoder;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.gui.charts.ProbabilityChartFrame;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupSequencesListListener extends MouseAdapter {
    JPopupMenu selectedIndexPopupMenu;
    private ProbabilityChartFrame frame;
    private JList sequenceList;
    Encoder classifier = SourceScopeTypeEncoder.getInstance();

    public PopupSequencesListListener(ProbabilityChartFrame frame, JList sequenceList) {
        this.selectedIndexPopupMenu = new JPopupMenu("menu");
        this.frame = frame;
        this.setSequenceList(sequenceList);
        JMenuItem item = new JMenuItem("show events");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                XYSeries series = getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
                showNodeGraph(series);
            }
        });
        this.selectedIndexPopupMenu.add(item);
        item = new JMenuItem("show histogram");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                XYSeries series = getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
                showEventHistogram(series);
            }
        });
        this.selectedIndexPopupMenu.add(item);
        item = new JMenuItem("show threshold");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                XYSeries series = getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
//    getFrame().removeline();
//    getFrame().setThresholdValue(((ScoreSequence)getSequenceList().getSelectedValue()).getThreshold().getMeanValue());
            }
        });
        //TODO add action to show threshold
        this.selectedIndexPopupMenu.add(item);

    }

    private void showNodeGraph(XYSeries s) {
    }

    private void showEventHistogram(XYSeries s) {
//  Range r = this.frame.getVisibleIntervalDomainAxis();
//  int from = (int) r.getLowerBound();
//  int to = (int) r.getUpperBound();
//  TimeSeries<Score> sequence = this.frame.getScoreSequence(s);
//  ArrayList<Score> vals = sequence.getElementsInRange(from, to);
//  DefaultCategoryDataset set = new DefaultCategoryDataset();
//  HashMap<String, Integer> v = new HashMap<String, Integer>();
//  String name = sequence.toString();
//  for (Score tmp : vals) {
//   String className = this.classifier.convertEnconding(tmp
//     .getEventType());
//   if (!v.containsKey(className))
//    v.put(className, 1);
//   else
//    v.put(className, v.get(className) + 1);
//  }
//  for (String key : v.keySet()) {
//   set.addValue(v.get(key), name, key);
//  }
//  JFreeChart chart = ChartFactory.createBarChart("score: "
//    + name + " || model:"
//    + sequence.getGeneratorID(), "Events", "Frequency", set,
//    PlotOrientation.VERTICAL, true, true, false);
//  ChartPanel panel = new ChartPanel(chart);
//  JFrame frame = new JFrame("score: " + name + " || model:"
//    + sequence.getGeneratorID());
//  frame.setSize(new Dimension(800,400));
//  frame.setLayout(new GridLayout(1, 1));
//  frame.getContentPane().add(panel);
//  frame.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        maybePopup(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        maybePopup(arg0);
    }

    public void maybePopup(MouseEvent arg0) {
        Point klickLocation = arg0.getPoint();
        JList l = (JList) arg0.getSource();
        int selectedIndex = l.getSelectedIndex();
        int clickedIndex = l.locationToIndex(klickLocation);
        if (arg0.isPopupTrigger()) {
            Rectangle boundary = l.getCellBounds(clickedIndex, clickedIndex);
            boolean isHit = boundary.contains(klickLocation);
            System.out.println("boundary" + boundary);
            System.out.println("klickLocation" + klickLocation);
            System.out.println("selectedIndex" + selectedIndex);
            System.out.println("clickedIndex" + clickedIndex);
            System.out.println("isHit" + isHit);
            if (isHit) {
                updateSelectedIndexPopup();
                this.selectedIndexPopupMenu.show(l, l.getX(), l.getY());
            }
        }
    }

    private void updateSelectedIndexPopup() {
        // TODO Auto-generated method stub

    }

    public void setFrame(ProbabilityChartFrame frame) {
        this.frame = frame;
    }

    public ProbabilityChartFrame getFrame() {
        return this.frame;
    }

    public void setSequenceList(JList sequenceList) {
        this.sequenceList = sequenceList;
    }

    public JList getSequenceList() {
        return this.sequenceList;
    }
}
