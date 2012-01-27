/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptimizerPanel.java
 *
 * Created on 01.08.2011, 11:11:43
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import java.io.File;
import java.util.concurrent.Executors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.util.ExampleFileFilter;


/**
 *
 * @author rgolombe
 */
public class OptimizerPanel extends javax.swing.JPanel implements ClassificatorOptimizerStatusListener {
    ClassificatorOptimizer optimizer;
	/** Creates new form OptimizerPanel */
    public OptimizerPanel(ClassificatorOptimizer inOptimizer){
        initComponents();
        optimizer = inOptimizer;
        optimizer.addStatusListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadFileButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTextArea = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 150));

        loadFileButton.setText("data");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        jButton1.setText("start");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        infoTextArea.setColumns(20);
        infoTextArea.setEditable(false);
        infoTextArea.setRows(5);
        infoTextArea.setDoubleBuffered(true);
        jScrollPane1.setViewportView(infoTextArea);

        jButton2.setText("apply");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("model");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, loadFileButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(loadFileButton)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
    	JFileChooser fileChooser = new JFileChooser(AucomIO.getInstance().getCurrentWorkingDirectory());
    	String[] allowedFileEndings = {"obs", "score", "tdf", "tdp", "cl"};
		fileChooser.setFileFilter(new ExampleFileFilter(allowedFileEndings));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			final File f = fileChooser.getSelectedFile();

			Executors.newSingleThreadExecutor().submit(new Runnable() {
				@Override
				public void run() {
					try {
						@SuppressWarnings("unchecked")
						TimeSeries<Observation> tsObs = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(f);
						optimizer.setTimeseries(tsObs);
						infoTextArea.append("optimization timeseries loaded from " + f.getAbsolutePath());
						updateButtonStatus();
					}catch (Exception e){
						JOptionPane.showMessageDialog(OptimizerPanel.this, e.getMessage(), "Loading optimization data failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	try {
			optimizer.start();
		} catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(OptimizerPanel.this, exception.getMessage(), "optimizer start failed", JOptionPane.ERROR_MESSAGE);
		} catch (ActionFailedException exception) {
			JOptionPane.showMessageDialog(OptimizerPanel.this, exception.getReason(), "optimizer start failed", JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    	JFileChooser fileChooser = new JFileChooser(AucomIO.getInstance().getCurrentWorkingDirectory());
		fileChooser.setFileFilter(new ExampleFileFilter("ml", "model"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			final File f = fileChooser.getSelectedFile();

			Executors.newSingleThreadExecutor().submit(new Runnable() {
				@Override
				public void run() {
					try {
						Model model  = AucomIO.getInstance().readFaultDetectionModel(f);
						optimizer.getDetector().setModel(model);
						optimizer.setModel(model);
						infoTextArea.append("\n model loaded "+ model +"\n");
						updateButtonStatus();
					}catch (Exception e){
						JOptionPane.showMessageDialog(OptimizerPanel.this, e.getMessage(), "Loading model failed", JOptionPane.ERROR_MESSAGE);
					}
				}

			});
		}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(optimizer != null){
        	String str = "exchanging " + optimizer.getDetector().getClassificator() + " with " + optimizer.getBestAnomalyClassificator() + "\n";
        	infoTextArea.append(str);
        	optimizer.copyBestClassificatorToDetector();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea infoTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadFileButton;
    // End of variables declaration//GEN-END:variables
	
    @Override
	public void classificatorOptimizatorStatusChanged(ClassificatorOptimizerStatusEvent status) {
		infoTextArea.append(status.toString() + "\n");
	}
	
	void updateButtonStatus() {
		System.out.println(optimizer.preconditionsSatisfied());
		if(optimizer.preconditionsSatisfied()){
			jButton1.setEnabled(true);
			jButton2.setEnabled(true);
			
		}else{
			jButton1.setEnabled(false);
			jButton2.setEnabled(false);
		}
	}

}
