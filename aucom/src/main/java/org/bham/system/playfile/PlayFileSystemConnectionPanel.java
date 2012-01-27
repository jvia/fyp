/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * XcfRecordSystemConnectionPanel.java
 *
 * Created on 10.07.2011, 18:02:16
 */

package org.bham.system.playfile;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.aucom.system.SystemConnectionStatus;
import org.bham.aucom.system.SystemConnectionStatusChangedEvent;
import org.bham.aucom.system.SystemConnectionStatusListener;

/**
 * 
 * @author rgolombe
 */
public class PlayFileSystemConnectionPanel extends javax.swing.JPanel implements SystemConnectionStatusListener {
	ScheduledExecutorService service;
	private PlayFileSystemConnection connection;
	/** Creates new form XcfRecordSystemConnectionPanel */
	public PlayFileSystemConnectionPanel(PlayFileSystemConnection inConnection) {
		initComponents();
		connection = inConnection;
		connection.addSystemConnectionStatusListener(this);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        fileProgressBar = new javax.swing.JProgressBar();
        loopCheckbox = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(500, 73));

        jToggleButton1.setText("connect");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        fileProgressBar.setDoubleBuffered(true);
        fileProgressBar.setStringPainted(true);

        loopCheckbox.setText("loop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fileProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loopCheckbox)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loopCheckbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton1ActionPerformed
		if (jToggleButton1.isSelected()) {
			try {
				connection.connect();
				// connection.getObservationTimeSeries().addTimeSeriesStatusListener(this);
				jToggleButton1.setText("disconnect");
			} catch (SystemConnectionFailedException exception) {
				JOptionPane.showMessageDialog(this, exception.getReason(), "failed", JOptionPane.ERROR_MESSAGE);
				System.out.println("failed : " + exception.getReason());
				jToggleButton1.setText("connect");
				jToggleButton1.setSelected(false);
			} catch (ActionNotPermittedException exception) {
				System.out.println("failed : " + exception);
				jToggleButton1.setText("connect");
				jToggleButton1.setSelected(false);
			}
		} else {
			System.out.println("disconnecting");
			try {
				connection.disconnect();
				try {
					// connection.getObservationTimeSeries().addTimeSeriesStatusListener(this);
				} catch (NullPointerException exception) {
					exception.printStackTrace();
				}
				jToggleButton1.setText("connect");
			} catch (ActionNotPermittedException e) {
				System.out.println("failed " + e.getLocalizedMessage());
				jToggleButton1.setText("disconnect");
			}
		}
	}// GEN-LAST:event_jToggleButton1ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		JFileChooser fileChooser = new JFileChooser(AucomIO.getInstance().getCurrentWorkingDirectory());
		// fileChooser.setFileFilter(new ExampleFileFilter("ml", "model"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			final File f = fileChooser.getSelectedFile();
			final ExecutorService ex = Executors.newSingleThreadExecutor();
					ex.submit(new Runnable() {
				@Override
				public void run() {
					try {
						connection.setFile(f);
						jButton1.setText(f.getName());
						ex.shutdown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}// GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar fileProgressBar;
    private javax.swing.JButton jButton1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JCheckBox loopCheckbox;
	 // End of variables declaration//GEN-END:variables
	void updateProgressBar() {
		if(connection != null){
			fileProgressBar.setMaximum(connection.getNumElements());
			fileProgressBar.setValue(connection.getProgress());
		}
	}

	@Override
	public void handleSystemConnectionEvent(SystemConnectionStatusChangedEvent event) {
		if (event.getOldStatus().equals(SystemConnectionStatus.DISCONNECTED) && event.getNewStatus().equals(SystemConnectionStatus.CONNECTED)) {
			startProgressbarUpdater();
		}
		if (event.getOldStatus().equals(SystemConnectionStatus.CONNECTED) && event.getNewStatus().equals(SystemConnectionStatus.DISCONNECTED)) {
			try {
				stopProgressBarUpdater();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
				
			if(jToggleButton1.isSelected()){
				jToggleButton1.setSelected(false);
				jToggleButton1.setText("connect");
			}
		}
	}

	/**
	 * 
	 */
	private void stopProgressBarUpdater() {
		System.out.println("stopProgressBarUpdater");
		try {
			service.shutdownNow();
			service = null;
			fileProgressBar.setMaximum(0);
			fileProgressBar.setValue(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void startProgressbarUpdater() {
		System.out.println("startProgressbarUpdater");
		service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setName("progressbar thread");
				return t;
			}
		});
		service.scheduleAtFixedRate(new Runnable() {
			public void run() {
				updateProgressBar();
			}

		}, 0, 500, TimeUnit.MILLISECONDS);
	}

}