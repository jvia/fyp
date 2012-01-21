/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MonitorPanel.java
 *
 * Created on 19.06.2011, 13:19:07
 */

package org.bham.aucom.diagnoser.t2gram.detector;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.FactoryManagerInitalizationException;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.util.ExampleFileFilter;

/**
 * 
 * @author rgolombe
 */
public class T2GramDetectorPanel extends javax.swing.JPanel implements TimeSeriesStatusListener {
	private static final long serialVersionUID = 0L;
	T2GramDetector detector;

	/** Creates new form MonitorPanel */
	public T2GramDetectorPanel(T2GramDetector inDetector) {
		detector = inDetector;
		initComponents();
		appendDetectorInfoToTextArea();
	}

	/**
	 * 
	 */
	private void appendDetectorInfoToTextArea() {
		appendRowToInfoTextArea("model " + detector.getModel());
		appendRowToInfoTextArea("classificator " + detector.getClassificator());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        detectButton = new javax.swing.JToggleButton();
        pauseButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        numberEvaluatedElements = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTextArea = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Monitor"));
        setPreferredSize(new java.awt.Dimension(550, 400));
        setLayout(new java.awt.BorderLayout());

        buttonPanel.setPreferredSize(new java.awt.Dimension(480, 20));
        buttonPanel.setLayout(new java.awt.GridLayout(1, 4));

        detectButton.setText("start");
        detectButton.setFocusable(false);
        detectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        detectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        detectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(detectButton);

        pauseButton.setText("pause");
        pauseButton.setEnabled(false);
        buttonPanel.add(pauseButton);

        jButton2.setText("reset");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttonPanel.add(jButton2);

        jButton3.setText("saveResults");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        buttonPanel.add(jButton3);

        add(buttonPanel, java.awt.BorderLayout.NORTH);

        numberEvaluatedElements.setText("#");
        add(numberEvaluatedElements, java.awt.BorderLayout.PAGE_END);

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 200));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration"));

        jButton4.setText("load model");
        jButton4.setPreferredSize(new java.awt.Dimension(160, 20));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jButton6.setText("select classificator");
        jButton6.setPreferredSize(new java.awt.Dimension(167, 20));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6);

        jPanel1.add(jPanel2);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(550, 150));

        infoTextArea.setColumns(20);
        infoTextArea.setEditable(false);
        infoTextArea.setRows(5);
        infoTextArea.setPreferredSize(new java.awt.Dimension(540, 50));
        jScrollPane1.setViewportView(infoTextArea);

        jPanel1.add(jScrollPane1);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    		JFileChooser fileChooser = new JFileChooser(AucomIO.getInstance().getCurrentWorkingDirectory());
    		fileChooser.setFileFilter(new ExampleFileFilter("ml", "model"));
    		int result = fileChooser.showOpenDialog(this);
    		if (result == JFileChooser.APPROVE_OPTION) {
    			final File f = fileChooser.getSelectedFile();

    			Executors.newSingleThreadExecutor().submit(new Runnable() {
    				@Override
    				public void run() {
    					try {
    						loadModelAndRegisterForEvents(f);
    					}catch (Exception e){
    						JOptionPane.showMessageDialog(T2GramDetectorPanel.this, e.getMessage(), "Loading model failed", JOptionPane.ERROR_MESSAGE);
    					}
    				}
    			});
    		}
        }//GEN-LAST:event_jButton4ActionPerformed

        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            ClassificatorSelectionPanel t = new ClassificatorSelectionPanel(detector);
            JFrame f = new JFrame();
            f.add(t);
            f.setLayout(new FlowLayout());
            f.setSize(new Dimension(550, 200));
            f.setVisible(true);
}//GEN-LAST:event_jButton6ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		JFileChooser fileChooser = new JFileChooser(AucomIO.getInstance().getCurrentWorkingDirectory());
		fileChooser.setFileFilter(new ExampleFileFilter("ml", "model"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			final File f = fileChooser.getSelectedFile();

			Executors.newSingleThreadExecutor().submit(new Runnable() {
				@Override
				public void run() {
					try {
						loadModelAndRegisterForEvents(f);
					}catch (Exception e){
						JOptionPane.showMessageDialog(T2GramDetectorPanel.this, e.getMessage(), "Loading model failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}

	}// GEN-LAST:event_jButton1ActionPerformed

	private void detectButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_detectButtonActionPerformed
		if (detectButton.isSelected()) {
			try {
				detector.start(SystemConnection.getInstance().getObservationTimeSeries());
				detector.getOutput().addTimeSeriesStatusListener(this);
				detectButton.setText("stop");
				pauseButton.setEnabled(true);
			} catch (ActionFailedException e) {
				detectButton.setSelected(false);
				JOptionPane.showMessageDialog(this, e.getReason(), "Detector failure", JOptionPane.ERROR_MESSAGE);
				appendRowToInfoTextArea("start detector failed: " + e.getReason());
				detectButton.setText("start");
			} catch (FactoryManagerInitalizationException exception) {
				detectButton.setSelected(false);
				JOptionPane.showMessageDialog(this, exception.getLocalizedMessage(), "Detector failure", JOptionPane.ERROR_MESSAGE);
				appendRowToInfoTextArea("start detector failed: " + exception.getLocalizedMessage());
				detectButton.setText("start");
			}
		} else {
			detector.getOutput().removeTimeseriesStatusListener(this);
			detectButton.setText("start");
			pauseButton.setEnabled(false);
			detector.stop();
		}

		// If a string was returned, say so.
	}// GEN-LAST:event_detectButtonActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		File fileToWriteTo = new File(AucomIO.getInstance().getCurrentWorkingDirectory().getAbsolutePath() + File.separator + "results_" + detector.getOutput().getId().toString() + ".cl");
		AucomIO.getInstance().writeTimeSeries(detector.getOutput(), fileToWriteTo);
		infoTextArea.append(detector.getOutput().size() + " results save to " + fileToWriteTo.getAbsolutePath());
	}// GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JToggleButton detectButton;
    private javax.swing.JTextArea infoTextArea;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberEvaluatedElements;
    private javax.swing.JButton pauseButton;
    // End of variables declaration//GEN-END:variables
	void loadModelAndRegisterForEvents(File f) throws FileNotFoundException, DataAlreadyExistsException, IOException, ValidityException, ParsingException, ClassCastException {
		Logger.getLogger(this.getClass().getCanonicalName()).info("loading model from " + f.getAbsolutePath());
		Model model = AucomIO.getInstance().readFaultDetectionModel(f);
		detector.setModel(model);
		appendRowToInfoTextArea("model loaded: " + model.toString());
	}
	public void appendRowToInfoTextArea(String row){
		infoTextArea.append(row + "\n"); 
	}

	@Override
	public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
		numberEvaluatedElements.setText("# " + status.getEndIndex() + 1);
	}

}