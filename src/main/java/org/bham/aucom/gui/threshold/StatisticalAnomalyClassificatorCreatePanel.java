/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatisticalThresholdPanel.java
 *
 * Created on 03.09.2010, 17:17:52
 */

package org.bham.aucom.gui.threshold;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;


/**
 * @author rgolombe
 */
public class StatisticalAnomalyClassificatorCreatePanel extends AbstractCreateAnomalyClassificatorPanel {
    private static final long serialVersionUID = 0L;

    /**
     * Creates new form StatisticalThresholdPanel
     */
    public StatisticalAnomalyClassificatorCreatePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        meanTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        varianceTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        windowSizeTextField = new javax.swing.JTextField();

        setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setText("mean");
        add(jLabel1);

        meanTextField.setText("0.63");
        add(meanTextField);

        jLabel2.setText("variance");
        add(jLabel2);

        varianceTextField.setText("0.03");
        add(varianceTextField);

        jLabel3.setText("window size");
        add(jLabel3);

        windowSizeTextField.setText("30");
        add(windowSizeTextField);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField meanTextField;
    private javax.swing.JTextField varianceTextField;
    private javax.swing.JTextField windowSizeTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public AnomalyClassificator getAnomalyClassificator() throws Exception {
        Double mean = Double.valueOf(this.meanTextField.getText());
        Double variance = Double.valueOf(this.varianceTextField.getText());
        Integer windowSize = Integer.valueOf(this.windowSizeTextField.getText());
        return new StatisticalAnomalyClassificator(mean, variance);
    }

}
