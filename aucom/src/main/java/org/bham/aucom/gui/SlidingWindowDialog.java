/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SlidingWindowDialog.java
 *
 * Created on 05.03.2010, 16:23:05
 */

package org.bham.aucom.gui;

import java.awt.Frame;

import org.bham.aucom.data.util.SlidingWindow;


/**
 * 
 * @author rgolombe
 */
public class SlidingWindowDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 0L;
	private final Frame myParent;
	private final SlidingWindow sw;
	private boolean approved = false;

	/* Creates new form SlidingWindowDialog */
    private SlidingWindowDialog(java.awt.Frame parent, boolean modal, SlidingWindow inSw) {
		super(parent, modal);
		initComponents();
		this.myParent = parent;
		sw = inSw;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applySlidingWindowButton = new javax.swing.JButton();
        windoOverlapTextField = new javax.swing.JTextField();
        windowSizeTextFiled = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        applySlidingWindowButton.setText("apply");
        applySlidingWindowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applySlidingWindowButtonActionPerformed(evt);
            }
        });

        windoOverlapTextField.setText("50");
        windoOverlapTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "overlap", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        windowSizeTextFiled.setText("100");
        windowSizeTextFiled.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "size", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(windowSizeTextFiled, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(windoOverlapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(applySlidingWindowButton, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(windowSizeTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(windoOverlapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applySlidingWindowButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void applySlidingWindowButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_applySlidingWindowButtonActionPerformed
		try {
				sw.setIntervalSize(getSlidingWindowSize());
				sw.setIntervalOverlapSize(getWindowOverlapSize());
				this.setVisible(false);
				this.setApproved(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}// GEN-LAST:event_applySlidingWindowButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SlidingWindowDialog dialog = new SlidingWindowDialog(new javax.swing.JFrame(), true, new SlidingWindow(0, 0));
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public void setjTextField2(javax.swing.JTextField jTextField2) {
		this.windoOverlapTextField = jTextField2;
	}

	javax.swing.JTextField getjTextField2() {
		return this.windoOverlapTextField;
	}

	int getSlidingWindowSize() {
		try {
			return Integer.parseInt(windoOverlapTextField.getText());
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		}

	}

	int getWindowOverlapSize() {
		try {
			return Integer.parseInt(getjTextField2().getText());
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		}

	}

	private void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isApproved() {
		return approved;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applySlidingWindowButton;
    private javax.swing.JTextField windoOverlapTextField;
    private javax.swing.JTextField windowSizeTextFiled;
    // End of variables declaration//GEN-END:variables

}
