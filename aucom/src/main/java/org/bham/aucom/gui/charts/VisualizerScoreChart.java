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

import net.sf.xcf.fts.Triple;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.SystemFaultStatus;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VisualizerScoreChart extends javax.swing.JPanel {
    private boolean showSnapShot = true;
    long faultBeginHorizintalCoordinate;
    double verticalCoordinate = -0.1;
    private static final long serialVersionUID = 1L;
    long startMonitoringTimeStamp = -1l;
    XYSeriesCollection fittnessSet = new XYSeriesCollection();
    private boolean isCurrentTimeSeriesFaulty = false;
    HashMap<BlockingQueue<Triple<Long, Double, SystemFaultStatus>>, XYSeries> series;
    JFreeChart chart;
    private double thresholdValue;
    int hrz = 10;
    ScheduledExecutorService service;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(new Dimension(600, 400));
        f.add(new VisualizerScoreChart());
        f.setVisible(true);
    }

    /**
     * Creates new form Monitor score chart
     */
    public VisualizerScoreChart() {
        Logger.getLogger(this.getClass().getName()).setLevel(Level.SEVERE);
        initComponents();
        initChart();
        visibleRange = 1000;
        startUpdating();
        Logger.getLogger(this.getClass().getCanonicalName()).info("VisualizerScoreChart initalized");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VisualizerScoreChart.this.rangeSlider.setValue(VisualizerScoreChart.this.rangeSlider.getMaximum());
                VisualizerScoreChart.this.rangeSlider.setVisible(false);
            }
        });
    }

    public void registerData(BlockingQueue<Triple<Long, Double, SystemFaultStatus>> input) {
        try {
            XYSeries s = new XYSeries("test");
            s.setMaximumItemCount(1000);
            this.series.put(input, s);
            this.fittnessSet.addSeries(s);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void unregisterData(BlockingQueue<Triple<Long, Double, SystemFaultStatus>> input) {
        if (!this.series.containsKey(input)) {
            return;
        }
        this.fittnessSet.removeSeries(series.get(input));
        series.remove(input);
    }

    public void startUpdating() {
        this.service = Executors.newScheduledThreadPool(1);

        this.service.scheduleAtFixedRate(new Runnable() {

            @SuppressWarnings("boxing")
            @Override
            public void run() {
                try {
                    reportIfYourAreOnEventDispatcherThread("startUpdating");
                    VisualizerScoreChart.this.chart.setNotify(false);
                    for (BlockingQueue<Triple<Long, Double, SystemFaultStatus>> bc : VisualizerScoreChart.this.series.keySet()) {
                        XYSeries s = VisualizerScoreChart.this.series.get(bc);
                        Triple<Long, Double, SystemFaultStatus> triple;
                        while (!bc.isEmpty()) {
                            triple = bc.take();
                            if (!isShowSnapShot()) {
                                s.addOrUpdate(triple.first.doubleValue() / (visibleRange * 0.01), triple.second.doubleValue());
                            } else if (bc.isEmpty()) {
                                s.addOrUpdate(triple.first.doubleValue() / (visibleRange * 0.01), triple.second.doubleValue());
                            }
                        }
                        updateDomainAxis();
                        updateSliderRanges();
                        VisualizerScoreChart.this.chart.setNotify(true);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, 100, 1000 / this.hrz, TimeUnit.MILLISECONDS);
    }

    public void updateDomainAxis() {
        // 2147483647
        int from = this.visibleDataSlider.getValue();
        int to = from + visibleRange;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        plot.getDomainAxis().setLowerBound(from);
        plot.getDomainAxis().setUpperBound(to);
        plot.getRangeAxis().setRange(-0.5, 1.5);
    }

    public void reportIfYourAreOnEventDispatcherThread(String str) {
        if (SwingUtilities.isEventDispatchThread()) {
            System.out.println("---------------------------------------------------------------------- " + this.getClass().getName() + " On EventDispatcher " + str);
        }
    }

    public void updateSliderRanges() {
        double maxValue_tmp = Double.MIN_VALUE;
        double minValue_tmp = Double.MAX_VALUE;
        for (Object tmp_series : VisualizerScoreChart.this.fittnessSet.getSeries()) {
            maxValue_tmp = Math.max(maxValue_tmp, ((XYSeries) tmp_series).getMaxX());
            minValue_tmp = Math.min(minValue_tmp, ((XYSeries) tmp_series).getMinX());
        }
        final double maxValue = maxValue_tmp;
        final double minValue = minValue_tmp;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VisualizerScoreChart.this.visibleDataSlider.setMinimum((int) minValue);
                VisualizerScoreChart.this.visibleDataSlider.setMaximum((int) maxValue);
                if (VisualizerScoreChart.this.showHeadCheckbox.isSelected()) {
                    VisualizerScoreChart.this.visibleDataSlider.setValue(VisualizerScoreChart.this.visibleDataSlider.getMaximum() - visibleRange);
                }
            }
        });
    }

    public void initChart() {
        // this.sequencesList.addMouseListener(new
        // PopupSequencesListListener(this, this.sequencesList));
        this.fittnessSet = new XYSeriesCollection();
        this.series = new HashMap<BlockingQueue<Triple<Long, Double, SystemFaultStatus>>, XYSeries>();
        setChart(ChartFactory.createXYLineChart("", "Time (ms)", "fitness/faults", this.fittnessSet, PlotOrientation.VERTICAL, false, false, false));

        XYPlot plot = (XYPlot) getChart().getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.getRangeAxis().setTickLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
        plot.getRangeAxis().setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
        plot.getDomainAxis().setTickLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
        plot.getDomainAxis().setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
        plot.getRangeAxis().setRange(-0.5, 1.5);
        // plot.setRangeGridlinePaint(Color.black);
        // ((XYLineAndShapeRenderer)(plot).getRenderer()).setShapesVisible(true);
        // ((XYLineAndShapeRenderer)(plot).getRenderer()).setAutoPopulateSeriesPaint(false);

        (plot).getRenderer().setBasePaint(Color.black);
        ((XYLineAndShapeRenderer) (plot).getRenderer()).setAutoPopulateSeriesPaint(false);
        this.chartPanel.setLayout(new GridLayout(1, 1));
        ChartPanel tmp = new ChartPanel(this.chart);
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
        return this.getName();
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
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        visibleDataSlider = new javax.swing.JSlider();
        chartPanel = new javax.swing.JPanel();
        rangeSlider = new javax.swing.JSlider();
        showHeadCheckbox = new javax.swing.JCheckBox();

        visibleDataSlider.setPaintLabels(true);
        visibleDataSlider.setValue(0);
        visibleDataSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domain axis", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        visibleDataSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                visibleDataSliderStateChanged(evt);
            }
        });

        chartPanel.setPreferredSize(new java.awt.Dimension(800, 250));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 571, Short.MAX_VALUE));
        chartPanelLayout.setVerticalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 267, Short.MAX_VALUE));

        rangeSlider.setMaximum(10000);
        rangeSlider.setMinimum(1);
        rangeSlider.setPaintLabels(true);
        rangeSlider.setValue(1);
        rangeSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Visible timespan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        rangeSlider.setPreferredSize(new java.awt.Dimension(800, 40));
        rangeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderStateChanged(evt);
            }
        });

        showHeadCheckbox.setText("sync");
        showHeadCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHeadCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                      .addGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                              layout.createSequentialGroup().addContainerGap().addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                                    .addComponent(rangeSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                              layout.createSequentialGroup().addContainerGap().addComponent(visibleDataSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup().addContainerGap(530, Short.MAX_VALUE).addComponent(showHeadCheckbox))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(rangeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(showHeadCheckbox).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(visibleDataSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
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

    public double getThresholdValue() {
        return this.thresholdValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JSlider rangeSlider;
    private javax.swing.JCheckBox showHeadCheckbox;
    javax.swing.JSlider visibleDataSlider;
    private int visibleRange;

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

    /**
     * @param classification
     */
    protected void annotate_FAULTINDUCED_IfNecessary(Classification classification) {
        if (classification.containsAttribute("comment")) {

            XYPointerAnnotation annotation2 = new XYPointerAnnotation("fault\ninduced!", classification.getTimestamp() - startMonitoringTimeStamp, verticalCoordinate, Math.PI / 2.0);
            annotation2.setTipRadius(5.0);
            annotation2.setBaseRadius(20.0);
            annotation2.setArrowLength(10.0);
            annotation2.setFont(new Font("SansSerif", Font.BOLD, 13));
            annotation2.setPaint(Color.black);
            annotation2.setTextAnchor(TextAnchor.TOP_CENTER);
            annotation2.setArrowPaint(Color.black);
            ((XYPlot) VisualizerScoreChart.this.getChart().getPlot()).addAnnotation(annotation2);
        }
    }

    protected void annotateFault_END_IfNecessary(Classification classification) {
        if (classification.getStatus().equals(SystemFaultStatus.NORMAL) && isCurrentTimeSeriesFaulty()) {
            long horizontalCoordinate = classification.getTimestamp();
            XYPointerAnnotation endPointer = new XYPointerAnnotation("", horizontalCoordinate, verticalCoordinate, -Math.PI / 2.0);
            endPointer.setTipRadius(5.0);
            endPointer.setBaseRadius(20.0);
            endPointer.setArrowLength(10.0);
            endPointer.setFont(new Font("SansSerif", Font.BOLD, 13));
            endPointer.setPaint(Color.red);
            endPointer.setTextAnchor(TextAnchor.TOP_CENTER);
            endPointer.setArrowPaint(Color.red);
            ((XYPlot) VisualizerScoreChart.this.getChart().getPlot()).addAnnotation(endPointer);

            XYLineAnnotation faultEndBegindLine = new XYLineAnnotation(faultBeginHorizintalCoordinate, verticalCoordinate, horizontalCoordinate, verticalCoordinate, new BasicStroke(2.0f), Color.red);
            ((XYPlot) VisualizerScoreChart.this.getChart().getPlot()).addAnnotation(faultEndBegindLine);
            setCurrentTimeSeriesFaulty(false);
            System.out.println(faultBeginHorizintalCoordinate);
        }
    }

    protected void annotateFault_BEGIN_IfNecessary(Classification classification) {
        if (classification.getStatus().equals(SystemFaultStatus.ABNORMAL) && !isCurrentTimeSeriesFaulty()) {
            long horizontalCoordinate = classification.getTimestamp();
            faultBeginHorizintalCoordinate = horizontalCoordinate;
            XYPointerAnnotation annotation2 = new XYPointerAnnotation("", horizontalCoordinate, verticalCoordinate, Math.PI / 2.0);
            annotation2.setTipRadius(5.0);
            annotation2.setBaseRadius(20.0);
            annotation2.setArrowLength(10.0);
            annotation2.setFont(new Font("SansSerif", Font.BOLD, 13));
            annotation2.setPaint(Color.red);
            annotation2.setTextAnchor(TextAnchor.TOP_CENTER);
            annotation2.setArrowPaint(Color.red);
            ((XYPlot) VisualizerScoreChart.this.getChart().getPlot()).addAnnotation(annotation2);
            setCurrentTimeSeriesFaulty(true);
        }
    }

    public void reset() {
        for (XYSeries s : this.series.values()) {
            s.clear();
        }
    }

    synchronized void setCurrentTimeSeriesFaulty(boolean isCurrentTimeSeriesFaulty) {
        this.isCurrentTimeSeriesFaulty = isCurrentTimeSeriesFaulty;
    }

    synchronized boolean isCurrentTimeSeriesFaulty() {
        return isCurrentTimeSeriesFaulty;
    }

    public boolean isReady() {
        return false;
    }

    public boolean isShowSnapShot() {
        return showSnapShot;
    }

    public void setShowSnapShot(boolean showSnapShot) {
        this.showSnapShot = showSnapShot;
    }
}
