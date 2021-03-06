/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModelViewer.java
 *
 * Created on 19.01.2010, 11:26:20
 */

package org.bham.aucom.gui;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.ExampleFileFilter;
import org.bham.aucom.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author rgolombe
 */
public class ModelViewer extends javax.swing.JFrame {
    private static final long serialVersionUID = 0L;
    T2GramModelI model;
    ExampleFileFilter modelFilter;
    File dir = new File("/home/rgolombe/workspace/org.bham.aucom/data/tobi_iros10_session/records/");

    /**
     * Creates new form ModelViewer
     */
    public ModelViewer() {
        initComponents();
        modelFilter = new ExampleFileFilter();
        modelFilter.addExtension("ml");
        modelFilter.setDescription("Trained Model");
    }

    public void load(File inFileToLoad) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(inFileToLoad));
            Model tmp = (Model) in.readObject();
            model = (T2GramModelI) tmp;
            System.out.println(model);
            updateView();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayDistribution(String label) {
        String parts[] = label.split("->");
//        int from = Integer.parseInt(parts[0]);
//        int to = Integer.parseInt(parts[1]);
//  new HistogramView(model.transitionMatrix.get(from, to)).setVisible(true);
    }

    public void updateView() {
        distributionPanel.removeAll();
//  System.out.println("updating");
        int col = 10;
        int row = model.getTransitionMatrix().keySet().size() / col;
        distributionPanel.setLayout(new GridLayout(row, col));
        for (Tuple<Integer, Integer> t : model.getTransitionMatrix().keySet()) {
            System.out.println(t);
            String label = t.toString();
            JButton b = new JButton(label);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    displayDistribution(((JButton) arg0.getSource()).getText());
                    String[] parts = ((JButton) arg0.getSource()).getText().split("->");
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    try {
                        new ProbabilityDisplayer(ModelViewer.this.model.getDistributionFor(from, to)).setVisible(true);
                    } catch (ClassCastException e) {
                        System.out.println("Warning: cannot display this model");
                    }
                }
            });
            distributionPanel.add(b);
        }
        distributionPanel.validate();
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

        distributionPanel = new javax.swing.JPanel();
        eventsLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        loadModel = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        eventsLabel.setText("Events:");

        loadModel.setText("File");

        jMenuItem1.setText("load model");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        loadModel.add(jMenuItem1);

        jMenuBar1.add(loadModel);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(distributionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addComponent(eventsLabel)
                                      .addContainerGap(341, Short.MAX_VALUE))
                                 );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addComponent(distributionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                      .addComponent(eventsLabel)
                                      .addContainerGap())
                               );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser(dir);
        fileChooser.setFileFilter(modelFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            load(f);
        }
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModelViewer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel distributionPanel;
    private javax.swing.JLabel eventsLabel;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu loadModel;
    // End of variables declaration//GEN-END:variables

}
