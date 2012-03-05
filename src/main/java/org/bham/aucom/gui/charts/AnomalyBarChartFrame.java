/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnomalyBarChartFrame.java
 *
 * Created on 23.11.2009, 10:39:53
 */

package org.bham.aucom.gui.charts;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Tuple;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author rgolombe
 */
public class AnomalyBarChartFrame extends javax.swing.JFrame implements ListDataListener {

    private static final long serialVersionUID = 5916732456468501494L;
    private DefaultCategoryDataset dataSet;
    private JFreeChart barChart;
    ArrayList<Score> data;
    int binsize;

    public void setBarChart(JFreeChart barChart) {
        this.barChart = barChart;
    }

    public JFreeChart getBarChart() {
        return this.barChart;
    }

    public void setDataSet(DefaultCategoryDataset dataSet) {
        this.dataSet = dataSet;
    }

    public DefaultCategoryDataset getDataSet() {
        return this.dataSet;
    }

    /**
     * Creates new form AnomalyBarChartFrame
     */
    public AnomalyBarChartFrame(TimeSeries<Score> inData) {
        initComponents();
        this.logger = Logger.getLogger(this.getClass().getCanonicalName());
        setDataSet(new DefaultCategoryDataset());
        setBarChart(ChartFactory.createBarChart("Anomaly", "anomalyType", "Score", getDataSet(), PlotOrientation.VERTICAL, true, true, false));
        this.chartPanel.setLayout(new GridBagLayout());
        ChartPanel tmp = new ChartPanel(getBarChart());
        this.chartPanel.add(tmp);
        this.chartPanel.validate();
        if (getData().size() != 0) {
            addValues(getData());
        }
        pack();
    }

    public ArrayList<Tuple> generateBarchartOutput(ArrayList<Score> list, int binSize) {
        ArrayList<Tuple> out = new ArrayList<Tuple>();
        long firstBinNumber = list.get(0).getTimestamp() % binsize;
        long lastBinNumber = list.get(list.size() - 1).getTimestamp() % binsize;
        for (long i = firstBinNumber; i <= lastBinNumber; i++) {
            for (long j = i * binsize; j < i * binsize + binsize; j++) {

            }
        }
        return null;
    }

    public void addValue(Score inScore) {
        this.logger.info("adding: " + inScore);
        if (inScore != null) {
//   String status = "normal";
//   if (inScore.getAbnormal() == SystemFaultStatus.ABNORMAL) {
//    status = "abnormal";
//   }
//   String sequenceName = getSequenceName(inScore);
//   increaseValueForSequence(status, sequenceName);
//   this.logger.info("new val is: " + this.dataSet.getValue(sequenceName, status));
        } else {
            this.logger.warning("warning inScore is null");
        }
    }

    /**
     * @param status
     * @param sequenceName
     */
    private void increaseValueForSequence(String status, String sequenceName) {
        Double val = (Double) this.dataSet.getValue(sequenceName, status);
        if (val != null) {
            this.dataSet.addValue(val + 1, sequenceName, status);
        } else {
            this.dataSet.addValue(1, sequenceName, status);
        }
    }

    public void addValues(List<Score> inScores) {
        this.dataSet.clear();
        for (Score score : inScores) {
            addValue(score);
        }
    }

    public void syncDataWithDataSet() {
        addValues(getData());
        System.out.println("sync: " + getData().size());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        this.chartPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(this.chartPanel);
        this.chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 734, Short.MAX_VALUE));
        chartPanelLayout.setVerticalGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 505, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(this.chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addComponent(this.chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // new AnomalyBarChartFrame(new MonitorableArrayList<Score>(new
                // ArrayList<Score>())).setVisible(true);
                // TODO: fix the problem directly above
            }
        });
    }

    public void setData(ArrayList<Score> data) {
        this.data = data;
    }

    public List<Score> getData() {
        return Collections.synchronizedList(data);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private Logger logger;

    // End of variables declaration//GEN-END:variables
    @Override
    public void contentsChanged(ListDataEvent arg0) {
    }

    @Override
    public void intervalAdded(ListDataEvent arg0) {
    }

    @Override
    public void intervalRemoved(ListDataEvent arg0) {
    }

}