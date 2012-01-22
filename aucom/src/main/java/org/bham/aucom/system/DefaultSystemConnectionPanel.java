/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DefaultSystemConnectionPanel.java
 *
 * Created on 19.06.2011, 14:31:20
 */

package org.bham.aucom.system;

import javax.swing.JOptionPane;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;


/**
 * 
 * @author rgolombe
 */
public class DefaultSystemConnectionPanel extends javax.swing.JPanel implements SystemConnectionStatusListener, TimeSeriesStatusListener {
	private static final long serialVersionUID = 0L;
	SystemConnection connection;

	/* Creates new form DefaultSystemConnectionPanel */
	public DefaultSystemConnectionPanel(SystemConnection inConnection) {
		connection = inConnection;
		connection.addSystemConnectionStatusListener(this);
		incomingSystemEventCounter= 0;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectButton = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        counterLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 70));
        setLayout(new java.awt.BorderLayout());

        connectButton.setText("connect");
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        add(connectButton, java.awt.BorderLayout.NORTH);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("info: ---");
        add(jLabel1, java.awt.BorderLayout.CENTER);

        counterLabel.setText("#0");
        add(counterLabel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

	private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton1ActionPerformed
		if (connectButton.isSelected()) {
			try {
				connection.connect();
				resetCounter();
				connection.getObservationTimeSeries().addTimeSeriesStatusListener(this);
				connectButton.setText("disconnect");
			} catch (SystemConnectionFailedException exception) {
				JOptionPane.showMessageDialog(this, exception.getReason(), "failed", JOptionPane.ERROR_MESSAGE);
				System.out.println("failed : " + exception.getReason());
				connectButton.setText("connect");
				connectButton.setSelected(false);
			} catch (ActionNotPermittedException exception) {
				System.out.println("failed : " + exception);
				connectButton.setText("connect");
				connectButton.setSelected(false);
			}
		} else {
			System.out.println("disconnecting");
			try {
				connection.disconnect();
				try {
					connection.getObservationTimeSeries().addTimeSeriesStatusListener(this);
				} catch (NullPointerException exception) {
					
				}
				connectButton.setText("connect");
			} catch (ActionNotPermittedException e) {
				System.out.println("failed " + e.getLocalizedMessage());
				connectButton.setText("disconnect");
			}
		}
	}// GEN-LAST:event_jToggleButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton connectButton;
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
	private int incomingSystemEventCounter;

	@Override
	public void handleSystemConnectionEvent(SystemConnectionStatusChangedEvent event) {
		if(event.getNewStatus().equals(SystemConnectionStatus.CONNECTED)){
			jLabel1.setText("info: connected");
		}
		if(event.getNewStatus().equals(SystemConnectionStatus.DISCONNECTED)){
			jLabel1.setText("info: disconnected");
		}
	}

	@Override
	public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
		incomingSystemEventCounter++;
		counterLabel.setText("#" +incomingSystemEventCounter);
		
	}
	public void resetCounter(){
		incomingSystemEventCounter= 0;
	}

	public boolean isReady() {
		return false;
	}
}
