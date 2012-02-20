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

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.util.ExampleFileFilter;
import org.bham.aucom.util.MonitorableArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreChartFrame extends javax.swing.JFrame implements
                                                        ListDataListener {
    File dir = new File("/home/rgolombe/work/experiments/data/tobi/FollowMe/");
    MonitorableArrayList<Score> data;
    private static final long serialVersionUID = 1L;
    XYSeriesCollection dataset = new XYSeriesCollection();
    private JFreeChart chart;
    private List<Score> externDataset;

    /**
     * Creates new form ProbabilityChartFrame
     */
    public ScoreChartFrame(MonitorableArrayList<Score> inData) {
        initComponents();
        initChart(inData);
    }

    public void syncDataWithDataSet() {
        if (this.isVisible()) {
            List<Score> scoreToDisplay = getDisplayableScores();
            List<Score> scoreToHide = getHideableScores();
            for (Score score : scoreToDisplay) {
                addV(score);
            }
            for (Score score : scoreToHide) {
                removeV(score);
            }
        }

    }

    public List<Score> getHideableScores() {
        int from = new Long(visibleDataSlider.getValue()).intValue();
        int to = from + rangeSlider.getValue() - 1;
        return getScoresNotInRange(externDataset, from, to);
    }

    public List<Score> getDisplayableScores() {
        int from = visibleDataSlider.getValue();
        int to = from + rangeSlider.getValue() - 1;
        // System.out.println("DisplayableScores " + from + " "+ to);
        return getScoresInRange(externDataset, from, to);
    }

    public List<Score> getScoresNotInRange(List<Score> data, int from, int to) {
        List<Score> list = new ArrayList<Score>();
        for (Score score : externDataset) {
            int timestamp = ((Long) score.getTimestamp()).intValue();
            // System.out.println("NOT IN RANGE " + from + " " + to + " " +
            // timestamp);
            if (timestamp < from || timestamp > to) {
                list.add(score);
            }
        }
        // System.out.println("numScores NOT in range: " + list.size());
        return list;
    }

    public List<Score> getScoresInRange(List<Score> data, int from, int to) {
        List<Score> list = new ArrayList<Score>();
        for (Score score : externDataset) {
            int timestamp = ((Long) score.getTimestamp()).intValue();
            // System.out.println("IN RANGE " + from + " " + to + " " +
            // timestamp);
            // System.out.println("from >= timestamp" + (from >= timestamp));
            // System.out.println("timestamp <= to" + (timestamp <= to));
            if (timestamp >= from && timestamp <= to) {
                list.add(score);
            }
        }
        // System.out.println("numScores in range: " + list.size());
        return list;
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        System.out.println("contentsChanged");
        // throw new NotImplementedException();
    }

    public void updateSliderRanges() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (externDataset.size() > 0) {
                    int firstElement = ((Long) externDataset.get(0).getTimestamp()).intValue();
                    int lastElement = ((Long) externDataset.get(externDataset.size() - 1).getTimestamp()).intValue();
                    // System.out.println("firstElement" + firstElement +
                    // " lastElement" +lastElement);
                    visibleDataSlider.setMinimum(firstElement);
                    visibleDataSlider.setMaximum(lastElement);
                    rangeSlider.setMaximum(lastElement - firstElement + 1);
                }
            }
        });
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        updateSliderRanges();
        syncDataWithDataSet();
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
    }

    public void initChart(MonitorableArrayList<Score> inData) {
        inData.addListDataListener(this);
        data = inData;
        externDataset = Collections.synchronizedList(inData);
        dataset = new XYSeriesCollection();
        setChart(ChartFactory.createXYLineChart("Probabilities", "Time",
                                                "Score", dataset, PlotOrientation.VERTICAL, true, true, false));
        chartPanel.setLayout(new GridBagLayout());
        ChartPanel tmp = new ChartPanel(chart);
        chartPanel.add(tmp);
        chartPanel.validate();
        syncDataWithDataSet();
        updateSliderRanges();

    }

    public void removeV(final Score inScore) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                removeValue(inScore);
            }
        });
    }

    public void addV(final Score inScore) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                addValue(inScore);
            }
        });
    }

    public void addValue(Score inScore) {
        // TODO FIX
//		if (inScore != null) {
//			XYDataItem item = inScore.asXYDataItem();
//			Comparable seriesName = (String) inScore.getDataSet();
//			XYSeries series;
//			synchronized (dataset) {
//				if (dataset.indexOf(seriesName) == -1) {
//					series = new XYSeries(seriesName);
//					System.out.println("new series : " + seriesName);
//					dataset.addSeries(series);
//				} else {
//					if (dataset.getSeries(seriesName).indexOf(item.getX()) < 0) {
//						// System.out.println("ADDING: "+ inScore);
//						series = dataset.getSeries(seriesName);
//						series.add(item);
//					} else {
//						int index = dataset.getSeries(seriesName).indexOf(
//								item.getX());
//						if (((Double) dataset.getSeries(seriesName).getX(index))
//								.intValue() != item.getYValue()) {
//							dataset.getSeries(seriesName).remove(index);
//							dataset.getSeries(seriesName).add(item);
//						}
//					}
//				}
//			}
//		} else {
//			System.out.println("warning inScore is null");
//		}
    }

    public void removeValue(Score inScore) {
        // TODO FIX
//		if (inScore != null) {
//			int timestamp = ((Long) inScore.getTimestamp()).intValue();
//			Comparable setType = (String) inScore.getDataSet();
//			synchronized (dataset) {
//				if (dataset.indexOf(setType) != -1) {
//					if (dataset.getSeries(setType).indexOf(timestamp) >= 0) {
//						// System.out.println("REMOVE: " + inScore);
//						XYSeries series = dataset.getSeries(setType);
//						series.remove(new Integer(timestamp));
//					}
//				}
//			}
//		}
    }

    public void addValues(List<Score> inScores) {
        for (Score score : inScores) {
            addValue(score);
        }
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public JFreeChart getChart() {
        return chart;
    }

    @Override
    public String toString() {
        String out = "rowKey:";
        return out;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rangeSlider = new javax.swing.JSlider();
        visibleDataSlider = new javax.swing.JSlider();
        chartPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        load = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setResizable(false);

        rangeSlider.setMinimum(1);
        rangeSlider.setOrientation(SwingConstants.VERTICAL);
        rangeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderStateChanged(evt);
            }
        });

        visibleDataSlider.setPaintLabels(true);
        visibleDataSlider.setPaintTrack(false);
        visibleDataSlider.setValue(0);
        visibleDataSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                visibleDataSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
                chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 652, Short.MAX_VALUE)
                                           );
        chartPanelLayout.setVerticalGroup(
                chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 395, Short.MAX_VALUE)
                                         );

        load.setText("File");

        jMenuItem1.setText("load");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        load.add(jMenuItem1);

        jMenuBar1.add(load);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                      .addComponent(visibleDataSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                      .addComponent(chartPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                      .addGap(18, 18, 18)
                                      .addComponent(rangeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addContainerGap(36, Short.MAX_VALUE))
                                 );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                  .addContainerGap(43, Short.MAX_VALUE)
                                                                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                  .addComponent(rangeSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(chartPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                  .addComponent(visibleDataSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                  .addContainerGap())
                               );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("unchecked")
    public TimeSeries<Score> load(File f) {
        TimeSeries<Score> l = null;
        try {
            l = (TimeSeries<Score>) AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking(f.getName());
            DataManager.getInstance().addTimeSeries(l);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ValidityException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (ParsingException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
        return l;
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ExampleFileFilter modelFilter = new ExampleFileFilter();
        modelFilter.addExtension("scr");
        JFileChooser fileChooser = new JFileChooser(dir);
        fileChooser.setFileFilter(modelFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            load(f);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void rangeSliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_rangeSliderStateChanged
        syncDataWithDataSet();
    }// GEN-LAST:event_rangeSliderStateChanged

    private void visibleDataSliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_visibleDataSliderStateChanged
        syncDataWithDataSet();
    }// GEN-LAST:event_visibleDataSliderStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MonitorableArrayList<Score> s = new MonitorableArrayList<Score>(
                        new ArrayList<Score>());
                ScoreChartFrame p = new ScoreChartFrame(s);
                p.setDefaultCloseOperation(EXIT_ON_CLOSE);
                p.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu load;
    private javax.swing.JSlider rangeSlider;
    private javax.swing.JSlider visibleDataSlider;
    // End of variables declaration//GEN-END:variables
}
