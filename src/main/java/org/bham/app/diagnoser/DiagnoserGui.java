/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutomaticRecorderFrame.java
 *
 * Created on 06.06.2010, 19:37:50
 */
package org.bham.app.diagnoser;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.Presentable;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.T2GramModelTrainer;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer.ClassifierOptimizer;
import org.bham.aucom.diagnoser.t2gram.visualizer.T2GramVisualizer;
import org.bham.aucom.system.FactoryManagerInitalizationException;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.xcfrecorder.Recorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author biron
 */
public class DiagnoserGui extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public DiagnoserGui(SystemConnection inConnection) {
        presentables = new ArrayList<Presentable>();
        try {
            initComponents();
            customizeComponents();
            registerPresentable(inConnection);
        } catch (SecurityException exception) {
            exception.printStackTrace();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    List<Presentable> presentables;

    public void registerPresentable(Presentable newPresentable) {
        presentables.add(newPresentable);
        newPresentable.getPanel().setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), newPresentable.getPanel().getName()));
        presentablesTabbedPane.addTab(newPresentable.getPanel().getName(), newPresentable.getPanel());
        presentablesTabbedPane.validate();
        validateTree();
    }

    public void deregisterPresentable(Presentable newPresentable) {
        presentables.remove(newPresentable);
        validateTree();
    }

    XYSeries frequencySeries;
    JLabel currentFrequencyLabel;
    JLabel numberRecordedEventsLabel;

    protected JButton makeNavigationButton(String imgLocation, String actionCommand, String toolTipText, String altText, ActionListener buttonActionListener) {
        // Look for the image.
        URL imageURL = Recorder.class.getResource(imgLocation);

        // Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(buttonActionListener);

        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            button.setText(altText);
            //System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    private void stopRecorderFrameUpdater() throws ActionNotPermittedException {
        if (this.frequencyUpdaterService != null) {
            this.frequencyUpdaterService.shutdown();
            this.frequencyUpdaterService = null;
        } else {
            throw new ActionNotPermittedException("no frequencyChartUpdater present");
        }
    }

    private void customizeComponents() {
        this.frequencySeries = new XYSeries("frequencies");
        JFreeChart frequencyChart = ChartFactory.createXYLineChart("", "", "", new XYSeriesCollection(this.frequencySeries), PlotOrientation.VERTICAL, false, false, false);
        frequencyChart.setBackgroundPaint(Color.black);
        XYPlot frequencyPlotToCustomize = frequencyChart.getXYPlot();
        frequencyPlotToCustomize.getRenderer().setBasePaint(Color.white);
        frequencyPlotToCustomize.getRenderer().setSeriesStroke(0, new BasicStroke(3.0f));
        frequencyPlotToCustomize.getRenderer().setSeriesPaint(0, Color.white);
        frequencyPlotToCustomize.getRangeAxis().setAxisLineVisible(false);
        frequencyPlotToCustomize.getRangeAxis().setTickLabelsVisible(false);
        frequencyPlotToCustomize.getDomainAxis().setTickLabelsVisible(false);
        frequencyPlotToCustomize.getDomainAxis().setAxisLineVisible(false);
        frequencyPlotToCustomize.setBackgroundPaint(Color.black);
        ChartPanel chartPanel = new ChartPanel(frequencyChart);
        chartPanel.setPreferredSize(new Dimension(200, 20));
        this.currentFrequencyLabel = new JLabel("0Hz", SwingConstants.CENTER);
        this.currentFrequencyLabel.setPreferredSize(new Dimension(80, 20));
        this.currentFrequencyLabel.setForeground(Color.white);
        this.currentFrequencyLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        this.numberRecordedEventsLabel = new JLabel("#0", SwingConstants.LEFT);
        this.numberRecordedEventsLabel.setPreferredSize(new Dimension(120, 20));
        this.numberRecordedEventsLabel.setForeground(Color.white);
        this.numberRecordedEventsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        this.setResizable(false);
        this.validate();
    }

    /**
     * This method is called from within the constructor to initialize the
     * form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        presentablesTabbedPane = new javax.swing.JTabbedPane();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diagnoser");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        presentablesTabbedPane.setPreferredSize(new java.awt.Dimension(750, 200));
        getContentPane().add(presentablesTabbedPane, java.awt.BorderLayout.CENTER);

        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        statusLabel.setPreferredSize(new java.awt.Dimension(500, 40));
        getContentPane().add(statusLabel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jButton1KeyPressed
        if (evt.getKeyCode() == 77) {
        }
    }// GEN-LAST:event_jButton1KeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyTyped
    }// GEN-LAST:event_formKeyTyped

    private void formKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_formKeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyPressed
    }// GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        if (args.length == 1) {
            //            final String systemName = args[0];
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        // TODO read configuration from xml file
                        SystemConnection connection = null;
                        try {
                            connection = SystemConnection.getInstance();
                        } catch (FactoryManagerInitalizationException ex) {
                            Logger.getLogger(DiagnoserGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        DiagnoserGui gui = new DiagnoserGui(connection);
                        gui.registerPresentable(new Recorder(connection));
                        gui.registerPresentable(new T2GramModelTrainer(new T2GramModelImp(new KDEProbabilityFactory())));
                        T2GramDetector t2gdetector = new T2GramDetector();
                        gui.registerPresentable(t2gdetector);
                        gui.registerPresentable(new ClassifierOptimizer(t2gdetector));
                        gui.registerPresentable(new T2GramVisualizer(t2gdetector));
                        gui.setVisible(true);
                    } catch (SecurityException exception) {
                        exception.printStackTrace();
                    } catch (IllegalArgumentException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } else {
            System.out.println("usage: diagnoser systemName");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane presentablesTabbedPane;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
    private ScheduledExecutorService frequencyUpdaterService;
}
