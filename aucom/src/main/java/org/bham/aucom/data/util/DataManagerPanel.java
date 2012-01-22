/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DataManagerPanel.java
 *
 * Created on Jun 20, 2011, 1:06:02 PM
 */
package org.bham.aucom.data.util;

import org.bham.aucom.util.AucomListModelAdapter;

/**
 *
 * @author rgolombe
 */
public class DataManagerPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 0L;    
	DataManager manager;
    /* Creates new form DataManagerPanel */
    public DataManagerPanel(DataManager inManager) {
    	manager = inManager;
        initComponents();
        jListModel = new AucomListModelAdapter();
        manager.addDataModelStatusListener(jListModel);
        jList1.setModel(jListModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setLayout(new java.awt.GridLayout(1, 0));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
	private AucomListModelAdapter jListModel;

    /*
     * Implementation of the accepts function of the @DataModeListener 
     * Indicates that we are interested in events about all types of data 
     */
}
