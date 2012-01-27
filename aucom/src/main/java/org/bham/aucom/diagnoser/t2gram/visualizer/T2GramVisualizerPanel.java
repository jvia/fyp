/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * T2GramVisualizerPanel.java
 *
 * Created on Jun 21, 2011, 4:01:11 PM
 */
package org.bham.aucom.diagnoser.t2gram.visualizer;

/**
 *
 * @author rgolombe
 */
public class T2GramVisualizerPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 0L;
	T2GramVisualizer  visualizer;
    /** Creates new form T2GramVisualizerPanel */
    public T2GramVisualizerPanel(T2GramVisualizer inVisualizer) {
    	visualizer = inVisualizer;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText("score");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	if(visualizer.scoreChartIsVisisble()){
    		return;
    	}
    	visualizer.showScoreChart();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
