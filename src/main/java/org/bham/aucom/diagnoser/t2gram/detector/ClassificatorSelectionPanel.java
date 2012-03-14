/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClassificatorSelectionPanel.java
 *
 * Created on 01.07.2011, 15:58:35
 */

package org.bham.aucom.diagnoser.t2gram.detector;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author rgolombe
 */
public class ClassificatorSelectionPanel extends javax.swing.JPanel implements ActionListener {
    private static final long serialVersionUID = 0L;
    /**
     * Creates new form ClassificatorSelectionPanel
     *
     * @param classificatorSelector
     */
    T2GramDetector model;
    AnomalyClassificatorFactory acFactory;

    public ClassificatorSelectionPanel(T2GramDetector inModel) {
        initComponents();
        model = inModel;
        acFactory = new AnomalyClassificatorFactory();
        acFactory.add("statistical", StatisticalAnomalyClassifier.class, StatisticalAnomalyClassificatorConfigurator.class, StatisticalAnomalyClassificatorConfiguratorPanel.class);
        acCombobox.addActionListener(this);
        acCombobox.setModel(new DefaultComboBoxModel(acFactory.getRegisteredACNames()));
        acCombobox.setSelectedIndex(0);
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

        acCombobox = new javax.swing.JComboBox();
        configuratorpanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 180));

        acCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        acCombobox.setPreferredSize(new java.awt.Dimension(500, 27));
        add(acCombobox);

        configuratorpanel.setPreferredSize(new java.awt.Dimension(500, 60));
        add(configuratorpanel);

        jButton1.setText("OK");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 29));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setClassificator();
        getTopFrame().dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static Frame getTopFrame() {
        Frame[] frames = Frame.getFrames();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].getFocusOwner() != null) {
                return frames[i];
            }
        }
        if (frames.length > 0) {
            return frames[0];
        }
        return null;
    }

    private void setClassificator() {
        model.setClassificator(currentClassifier);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acCombobox;
    private javax.swing.JPanel configuratorpanel;
    private javax.swing.JButton jButton1;
    private AnomalyClassifier currentClassifier;

    // End of variables declaration//GEN-END:variables
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String acName = (String) cb.getSelectedItem();
        System.out.println(acName);
        try {
            if (configuratorpanel.getComponents().length > 0) {
                configuratorpanel.remove(0);
            }
            currentClassifier = acFactory.create(acName);
            configuratorpanel.add(acFactory.getConfiguratorPanel(acFactory.getConfigurator(currentClassifier)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
