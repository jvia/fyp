/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ScoreView.java
 *
 * Created on 20.01.2010, 07:59:01
 */

package org.bham.aucom.gui;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.util.ExampleFileFilter;
import org.bham.aucom.util.TimeSeriesNotFoundException;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author rgolombe
 */
public class ScoreView extends javax.swing.JFrame {
    private static final long serialVersionUID = 0L;
    File dir = new File("/home/rgolombe/work/experiments/data/tobi/FollowMe/");

    /**
     * Creates new form ScoreView
     */
    public ScoreView() {
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        loadMenuItem.setText("load");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(loadMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGap(0, 206, Short.MAX_VALUE)
                                 );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGap(0, 63, Short.MAX_VALUE)
                               );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public TimeSeries<Score> load(File f) {
        UUID id;
        try {
            TimeSeries<Score> score = (TimeSeries<Score>) AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking(f.getName());
            id = score.getId();
            @SuppressWarnings("unchecked")
            TimeSeries<Score> l = (TimeSeries<Score>) DataManager.getInstance().getTimeSeriesById(id);
            return l;
        } catch (FileNotFoundException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (ValidityException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (ParsingException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (TimeSeriesNotFoundException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
        return null;

    }

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        ExampleFileFilter modelFilter = new ExampleFileFilter();
        modelFilter.addExtension("scr");
        JFileChooser fileChooser = new JFileChooser(dir);
        fileChooser.setFileFilter(modelFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            load(f);
        }
    }//GEN-LAST:event_loadMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScoreView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem loadMenuItem;
    // End of variables declaration//GEN-END:variables

}
