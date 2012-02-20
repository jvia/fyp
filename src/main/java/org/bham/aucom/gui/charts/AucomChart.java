/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProbabilityChartFrame.java
 *
 * Created on 20.11.2009, 16:12:46
 */

package org.bham.aucom.gui.charts;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Tuple;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AucomChart extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    private XYSeriesCollection dataset = new XYSeriesCollection();
    HashMap<TimeSeries<Score>, Tuple<XYSeries, Integer>> series;
    DefaultListModel sequencesListModel;
    private JFreeChart chart;
    private double thresholdValue;
    int hrz = 50;
    ScheduledExecutorService service;
    private final String type;

    /**
     * Creates new form ProbabilityChartFrame
     */
    public AucomChart(String type) {
        Logger.getLogger(this.getClass().getName()).setLevel(Level.SEVERE);
        this.type = type;
        initComponents();
        initChart();
        startUpdating();
    }

    public void registerData(TimeSeries<Score> sequence) {
        info("registering currently with " + sequence.size() + " items");
        if (this.series.containsKey(sequence)) {
            throw new IllegalArgumentException("Sequence allready registered");
        }
        XYSeries s = new XYSeries(this.getName());
        this.series.put(sequence, new Tuple<XYSeries, Integer>(s, 0));
        // s.setNotify(false);
        getDataset().addSeries(s);
        this.sequencesListModel.addElement(sequence);
        // addDataToSeries(sequence, 0, 50);
    }

    public void unregisterData(TimeSeries<Score> sequence) {
        if (!this.series.containsKey(sequence)) {
            throw new IllegalArgumentException("Unknown sequence " + sequence);
        }
        Tuple<XYSeries, Integer> t = this.series.get(sequence);
        getDataset().removeSeries(t.getFirstElement());
        this.sequencesListModel.removeElement(sequence);
    }

    public void startUpdating() {
        this.service = Executors.newScheduledThreadPool(1);
        this.service.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                try {
                    for (TimeSeries<Score> s : AucomChart.this.series.keySet()) {
                        Tuple<XYSeries, Integer> t = AucomChart.this.series.get(s);
                        int from = t.getSecondElement();
                        int to = (int) Math.min(s.get(s.size() - 1).getTimestamp(), from + 1000 / AucomChart.this.hrz);
                        updateDomainAxis();
                        updateSliderRanges();
                        t.getFirstElement().setNotify(false);
                        addDataToseriesInTimespan(s, from, to);
                        t.getFirstElement().setNotify(true);
                        t.setSecondElement(to);
                    }
                } catch (Throwable e) {
                    // TODO: handle exception
                }
            }
        }, 100, 1000 / this.hrz, TimeUnit.MILLISECONDS);
    }

    public void addDataToseriesInTimespan(final TimeSeries<Score> sequence, final int from, final int to) {
//  final ArrayList<Score> list = sequence.getElementsInRange(from, to);
        // if(list.size()>0){
        // System.out.print("from " + from + " first "
        // +list.get(0).getTimestamp());
        // System.out.println(" to " + to +" last "
        // +list.get(list.size()-1).getTimestamp());
        // }
//  final XYSeries s = this.series.get(sequence).getFirstElement();
//
//  SwingUtilities.invokeLater(new Runnable() {
//   @Override
//   public void run() {
//    for (Score score : list) {
//     s.addOrUpdate(score.getTimestamp(), score.getValue());
//    }
//   }
//  });
    }

    public void removeDataFromSeries(TimeSeries<Score> sequence, int from, int to) {
        // updateDomainAxis();
        XYSeries s = this.series.get(sequence).getFirstElement();
        synchronized (sequence) {

            for (int i = from; i <= to; i++) {
                Number x = sequence.get(i).getTimestamp();
                if (s.indexOf(x) >= 0)
                    s.remove(x);
            }
        }
    }

    public Range getVisibleIntervalDomainAxis() {
        XYPlot plot = (XYPlot) this.chart.getPlot();
        Range range = plot.getDomainAxis().getRange();
        return range;

    }

    public XYSeries getScoreXYSeries(TimeSeries<Score> sequence) {
        return this.series.get(sequence).getFirstElement();
    }

    public TimeSeries<Score> getScoreSequence(XYSeries ser) {
        for (TimeSeries<Score> s : this.series.keySet()) {
            if (ser.equals(this.series.get(s).getFirstElement())) {
                return s;
            }
        }
        info("returnining zero");
        return null;
    }

    public void removeline() {
        synchronized (getDataset()) {

            if (getDataset().indexOf("Threshold") != -1) {
                XYSeries series = getDataset().getSeries("Threshold");
                if (!series.isEmpty()) {
                    Double min = series.getMinX();
                    Double max = series.getMaxX();
                    if (series.indexOf(min) != -1)
                        series.remove(min);
                    if (series.indexOf(max) != -1)
                        series.remove(max);
                }
            }
        }
    }

    public void drawLine() {
        synchronized (getDataset()) {

            final XYSeries series;

            if (getDataset().indexOf("Threshold") == -1) {
                XYSeries tmp = new XYSeries("Threshold");
                getDataset().addSeries(tmp);
            }
            series = getDataset().getSeries("Threshold");
            if (!series.isEmpty()) {
                Double min = series.getMinX();
                Double max = series.getMaxX();
                if (series.indexOf(min) != -1)
                    series.remove(min);
                if (series.indexOf(max) != -1)
                    series.remove(max);
            }
            final double min_x = getDataset().getDomainLowerBound(false);
            final double max_x = getDataset().getDomainUpperBound(false);
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    series.add(new XYDataItem(min_x, getThresholdValue()));
                    series.add(new XYDataItem(max_x, getThresholdValue()));
                }
            });
        }
    }

    public void updateDomainAxis() {
        int from = getVisibleDataSlider().getValue();
        int to = from + this.rangeSlider.getValue();
        XYPlot plot = (XYPlot) this.chart.getPlot();
        plot.getDomainAxis().setLowerBound(from);
        plot.getDomainAxis().setUpperBound(to);
    }

    public void updateSliderRanges() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                double maxValue = Double.MIN_VALUE;
                double minValue = Double.MAX_VALUE;
                for (Object series : getDataset().getSeries()) {
                    maxValue = Math.max(maxValue, ((XYSeries) series).getMaxX());
                    minValue = Math.min(minValue, ((XYSeries) series).getMinX());
                }
                getVisibleDataSlider().setMinimum((int) minValue);
                getVisibleDataSlider().setMaximum((int) maxValue);
                if (getShowHeadCheckbox().isSelected()) {
                    getVisibleDataSlider().setValue(getVisibleDataSlider().getMaximum() - AucomChart.this.rangeSlider.getValue());
                }
            }
        });
    }

    public void initChart() {
        setThresholdValue(0.5);
        this.sequencesListModel = new DefaultListModel();
        this.sequencesList.setModel(this.sequencesListModel);
        // sequencesList.addMouseListener(new PopupSequencesListListener(this,
        // sequencesList));
        setDataset(new XYSeriesCollection());
        this.series = new HashMap<TimeSeries<Score>, Tuple<XYSeries, Integer>>();
        setChart(ChartFactory.createXYLineChart("", "Time (ms)", "Score value", getDataset(), PlotOrientation.VERTICAL, true, true, true));
        XYPlot plot = (XYPlot) getChart().getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.getRangeAxis().setTickLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        plot.getDomainAxis().setTickLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        // plot.setRangeGridlinePaint(Color.black);
        // ((XYLineAndShapeRenderer)(plot).getRenderer()).setShapesVisible(true);
        // ((XYLineAndShapeRenderer)(plot).getRenderer()).setAutoPopulateSeriesPaint(false);
        // ((XYLineAndShapeRenderer)(plot).getRenderer()).setBasePaint(Color.black);
        this.chartPanel.setLayout(new GridLayout(1, 1));
        ChartPanel tmp = new AucomChartPanel(this.chart);
        this.chartPanel.add(tmp);
        this.chartPanel.validate();
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public JFreeChart getChart() {
        return this.chart;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setVisibleDataSlider(new javax.swing.JSlider());
        this.chartPanel = new javax.swing.JPanel();
        this.rangeSlider = new javax.swing.JSlider();
        setShowHeadCheckbox(new javax.swing.JCheckBox());
        this.jScrollPane1 = new javax.swing.JScrollPane();
        this.sequencesList = new javax.swing.JList();

        getVisibleDataSlider().setPaintLabels(true);
        getVisibleDataSlider().setValue(0);
        getVisibleDataSlider().setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domain axis", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        getVisibleDataSlider().addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                visibleDataSliderStateChanged(evt);
            }
        });

        this.chartPanel.setPreferredSize(new java.awt.Dimension(800, 250));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(this.chartPanel);
        this.chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 919, Short.MAX_VALUE));
        chartPanelLayout.setVerticalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 284, Short.MAX_VALUE));

        this.rangeSlider.setMaximum(20000);
        this.rangeSlider.setMinimum(1);
        this.rangeSlider.setPaintLabels(true);
        this.rangeSlider.setValue(1);
        this.rangeSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Visible timespan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        this.rangeSlider.setPreferredSize(new java.awt.Dimension(800, 40));
        this.rangeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderStateChanged(evt);
            }
        });

        getShowHeadCheckbox().setText("sync");
        getShowHeadCheckbox().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHeadCheckboxActionPerformed(evt);
            }
        });

        this.sequencesList.setBorder(javax.swing.BorderFactory.createTitledBorder("Sequences"));
        this.jScrollPane1.setViewportView(this.sequencesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                      .addGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(this.jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                                    .addGroup(
                                            layout.createSequentialGroup().addComponent(getVisibleDataSlider(), javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(getShowHeadCheckbox()))
                                    .addComponent(this.chartPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                                    .addComponent(this.rangeSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                      .addComponent(this.rangeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(this.chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(getShowHeadCheckbox())
                                    .addComponent(getVisibleDataSlider(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(this.jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showHeadCheckboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_showHeadCheckboxActionPerformed

    }// GEN-LAST:event_showHeadCheckboxActionPerformed

    private void rangeSliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_rangeSliderStateChanged
        // updateSliderRanges();
        updateDomainAxis();
    }// GEN-LAST:event_rangeSliderStateChanged

    private void visibleDataSliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_visibleDataSliderStateChanged
        // updateSliderRanges();
        updateDomainAxis();
    }// GEN-LAST:event_visibleDataSliderStateChanged

// /**
//  * @param args
//  *            the command line arguments
//  */
// public static void main(String args[]) {
//  try {
//   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//  } catch (ClassNotFoundException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  } catch (InstantiationException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  } catch (IllegalAccessException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  } catch (UnsupportedLookAndFeelException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//
//  java.awt.EventQueue.invokeLater(new Runnable() {
//   @Override
//   public void run() {
//    ArrayList<Score> s = new ArrayList<Score>();
//    ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
//    features.add(new DomainFeature("scope", "a"));
//    features.add(new DomainFeature("type", "b"));
//    features.add(new DomainFeature("source", "c"));
//
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.1));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.2));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.3));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.4));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.5));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.6));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.7));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.8));
//    s.add(new SingleScore(new TemporalProbabilityFeature(), 0.9));
//    TimeSeries<Score> sequence = new ScoreTimeSeries();
//    sequence.setId(UUID.randomUUID());
//    sequence.setGeneratedFrom(UUID.randomUUID());
//    sequence.setGenerator(UUID.randomUUID());
//    AucomChart p = new AucomChart("test");
//    p.registerData(sequence);
//    p.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    p.setVisible(true);
//
//   }
//  });
// }

    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
        drawLine();
    }

    public double getThresholdValue() {
        return this.thresholdValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider rangeSlider;
    private javax.swing.JList sequencesList;
    private javax.swing.JCheckBox showHeadCheckbox;
    private javax.swing.JSlider visibleDataSlider;

    // End of variables declaration//GEN-END:variables
    public void contentsChanged(ListDataEvent e) {
        // // if (this.isVisible()) {
        // // addDataToseriesInTimespan((ScoreSequence) e.getSource(),
        // e.getIndex0(), e
        // // .getIndex1());
        // // updateSliderRanges();
        // }
    }

    public void info(String msg) {
        Logger.getLogger(this.getClass().getName()).info(msg);
    }

    public void severe(String msg) {
        Logger.getLogger(this.getClass().getName()).severe(msg);
    }

    public String getType() {
        return this.type;
    }

    void setDataset(XYSeriesCollection dataset) {
        this.dataset = dataset;
    }

    XYSeriesCollection getDataset() {
        return this.dataset;
    }

    private void setVisibleDataSlider(javax.swing.JSlider visibleDataSlider) {
        this.visibleDataSlider = visibleDataSlider;
    }

    private javax.swing.JSlider getVisibleDataSlider() {
        return this.visibleDataSlider;
    }

    private void setShowHeadCheckbox(javax.swing.JCheckBox showHeadCheckbox) {
        this.showHeadCheckbox = showHeadCheckbox;
    }

    private javax.swing.JCheckBox getShowHeadCheckbox() {
        return this.showHeadCheckbox;
    }
}
