/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HysteresisThresholdPanel.java
 *
 * Created on 31.07.2010, 13:37:29
 */

package org.bham.aucom.gui.threshold;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.HysteresisAnomalyClassifier;

import javax.swing.*;

/**
 *
 * @author rgolombe
 */
public class HysteresisThresholdPanel extends AbstractCreateAnomalyClassifierPanel {

	private static final long serialVersionUID = 1L;
	/** Creates new form HysteresisThresholdPanel */
    public HysteresisThresholdPanel() {
        initComponents();
    }
	@Override
	public AnomalyClassifier getAnomalyClassifier() throws Exception{
		Double lower = Double.valueOf(this.lowertextField.getText());
		Double upper = Double.valueOf(this.upperTextField.getText());
		return new HysteresisAnomalyClassifier(lower, upper);
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperTextField = new javax.swing.JTextField();
        lowertextField = new javax.swing.JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();

        upperTextField.setText("1.0");

        lowertextField.setText("1.0");

        jLabel1.setText("Upper");

        jLabel2.setText("Lower");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(upperTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lowertextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lowertextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(upperTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private javax.swing.JTextField lowertextField;
    private javax.swing.JTextField upperTextField;
    // End of variables declaration//GEN-END:variables

}
