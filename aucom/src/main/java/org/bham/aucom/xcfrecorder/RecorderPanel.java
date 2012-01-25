/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutomaticRecorderFrame.java
 *
 * Created on 06.06.2010, 19:37:50
 */

package org.bham.aucom.xcfrecorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.util.FileOperator;
import org.bham.aucom.util.FolderFilter;
import org.jfree.data.xy.XYSeries;


/**
 * 
 * @author biron
 */
public class RecorderPanel extends JPanel implements RecorderStatusListener {

	private static final long serialVersionUID = 1L;
	private Recorder recorder;
	private final Logger logger;

	boolean isGuiActivated() {
		try {
			return this.recorderToolbar.getComponent(0).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	void synchronizeStatusWithRecorder() {
		verifyFolderCanBeChanged();
		refreshGuiLabels();
		if ((getRecorder().isReady() || getRecorder().isRecording()) && !isGuiActivated()) {
			enableGuiForRecording();
		}
		if ((!getRecorder().isReady()  && !getRecorder().isRecording()) && isGuiActivated()) {
			disableGuiForRecording();
		}
	}

	void refreshGuiLabels() {
		this.fileLabel.setText("file: " + FileOperator.getName(getRecorder().getFileName()));
		this.folderLabel.setText("folder: " + getRecorder().getFolder().getAbsolutePath());
	}

	private void disableGuiForRecording() {
		for (int i = 0; i < this.recorderToolbar.getComponentCount(); i++) {
			this.recorderToolbar.getComponent(i).setEnabled(false);
		}
	}

	private void verifyFolderCanBeChanged() {
		try {
			this.recorderToolbar.getComponent(2).setEnabled(getRecorder() == null || !getRecorder().isRecording());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enableGuiForRecording() {
		for (int i = 0; i < this.recorderToolbar.getComponentCount(); i++) {
			this.recorderToolbar.getComponent(i).setEnabled(true);
		}
	}

	public RecorderPanel(Recorder recorder) {
		this.setRecorder(recorder);
		recorder.addRecorderStatusListener(this);
		logger = Logger.getLogger(this.getClass().getName());
		initComponents();
		customizeComponents();
		try {
			startRecorderFrameUpdater();
		} catch (ActionNotPermittedException e) {
			Logger.getLogger(this.getClass().getCanonicalName()).severe("coudn't start frequencyChartUpdater, reason: " + e.getMessage());
		}
		synchronizeStatusWithRecorder();
	}

	XYSeries frequencySeries;
	JLabel currentFrequencyLabel;
	private JLabel numberRecordedEventsLabel;

	JButton makeNavigationButton(String imgLocation, String actionCommand, String toolTipText, String altText, ActionListener buttonActionListener) {
		// Look for the image.
		URL imageURL = RecorderPanel.class.getResource(imgLocation);

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(buttonActionListener);

		if (imageURL != null) { // image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else { // no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}

	private void startRecorderFrameUpdater() throws ActionNotPermittedException {
		if (this.frequencyUpdaterService == null) {
			this.frequencyUpdaterService = Executors.newScheduledThreadPool(1);
			this.frequencyUpdaterService.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					try {
						int numberRecordedEvents = getRecorder().getNumberRecordedEvents();
						RecorderPanel.this.numberRecordedEventsLabel.setText(String.valueOf("#" + (numberRecordedEvents)));
						RecorderPanel.this.synchronizeStatusWithRecorder();
						RecorderPanel.this.refreshGuiLabels();
					} catch (Exception e) {
						e.printStackTrace();
						Logger.getLogger(this.getClass().getCanonicalName()).severe("coudn't update frequency chart, reason: " + e.getMessage());
					}
				}
			}, 1000, 100, TimeUnit.MILLISECONDS);
		} else {
			throw new ActionNotPermittedException("frequencyChartUpdater already running");
		}
	}

	private void customizeComponents() {
		this.numberRecordedEventsLabel = new JLabel("#0", SwingConstants.LEFT);
		this.numberRecordedEventsLabel.setPreferredSize(new Dimension(120, 20));
		this.numberRecordedEventsLabel.setForeground(Color.white);
		this.numberRecordedEventsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		this.frequencyDisplayPanel.add(this.numberRecordedEventsLabel, BorderLayout.BEFORE_LINE_BEGINS);

		addButtonsToRecorderToolBar();

		this.validate();
	}

	private void addButtonsToRecorderToolBar() {
		JButton tempButton;
		tempButton = this.makeNavigationButton("/toolbarButtonGraphics/media/Play16.gif", "record", "record", "record", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getRecorder().record();
				} catch (Exception e2) {
					System.err.println("coudn't start recorder, reason: " + e2.getMessage());
				}
			}
		});
		// tempButton.setEnabled(false);
		this.recorderToolbar.add(tempButton);
		tempButton = this.makeNavigationButton("/toolbarButtonGraphics/media/Stop16.gif", "stop", "stop recording", "stop", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getRecorder().stop();
				} catch (ActionNotPermittedException e1) {
					System.err.println("coudn't stop recorder, reason: " + e1.getMessage());
				} catch (Exception e2) {
					System.err.println("coudn't stop recorder, reason: " + e2.getMessage());
				}
			}
		});
		this.recorderToolbar.add(tempButton);
		tempButton = this.makeNavigationButton("/toolbarButtonGraphics/general/Open16.gif", "changeFolder", "choose new folder to save files in", "change folder", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser folderChooser = new JFileChooser();
					folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					folderChooser.setFileFilter(new FolderFilter());
					int result = folderChooser.showDialog(RecorderPanel.this, "Wählen");
					if (result == JFileChooser.APPROVE_OPTION) {
						getRecorder().changeFolder(folderChooser.getSelectedFile());
					}
				} catch (ActionNotPermittedException e1) {
					logger.info("coudn't start recorder, reason: " + e1.getMessage());
				} catch (Exception e2) {
					logger.info("coudn't start recorder, reason: " + e2.getMessage());
				}
			}
		});
		this.recorderToolbar.add(tempButton);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		recorderToolbar = new javax.swing.JToolBar();
		frequencyDisplayPanel = new javax.swing.JPanel();
		fileLabel = new javax.swing.JLabel();
		folderLabel = new javax.swing.JLabel();

		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				formKeyReleased(evt);
			}

			public void keyTyped(java.awt.event.KeyEvent evt) {
				formKeyTyped(evt);
			}
		});

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tag"));

		jButton1.setText("sent");
		jButton1.setEnabled(false);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField1.setText("type text here");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});
		jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jTextField1KeyPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				jTextField1KeyReleased(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		recorderToolbar.setFloatable(false);
		recorderToolbar.setRollover(true);

		frequencyDisplayPanel.setBackground(java.awt.Color.black);
		frequencyDisplayPanel.setLayout(new java.awt.BorderLayout(2, 0));

		fileLabel.setBackground(new java.awt.Color(255, 162, 0));
		fileLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
		fileLabel.setText("file: ");

		folderLabel.setText("folder:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(recorderToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup().addGap(18, 18, 18).addComponent(frequencyDisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addGap(12, 12, 12)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap())
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(fileLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
												.addComponent(folderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(recorderToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(frequencyDisplayPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(folderLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(fileLabel)));
	}// </editor-fold>//GEN-END:initComponents

	private void formKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyTyped

	}// GEN-LAST:event_formKeyTyped

	private void formKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyReleased
		// TODO add your handling code here:
	}// GEN-LAST:event_formKeyReleased

	private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyReleased
		if (this.jTextField1.getText().trim().equals("") || this.jTextField1.getText().trim().equals("type text here")) {
			this.jButton1.setEnabled(false);
		} else {
			this.jButton1.setEnabled(true);
		}
	}// GEN-LAST:event_jTextField1KeyReleased

	private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyPressed

	}// GEN-LAST:event_jTextField1KeyPressed

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed

	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		this.jTextField1.setText("type text here");
		this.jButton1.setEnabled(false);
	}// GEN-LAST:event_jButton1ActionPerformed

	synchronized void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}

	synchronized Recorder getRecorder() {
		return this.recorder;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel fileLabel;
	private javax.swing.JLabel folderLabel;
	private javax.swing.JPanel frequencyDisplayPanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JToolBar recorderToolbar;
	// End of variables declaration//GEN-END:variables
	private ScheduledExecutorService frequencyUpdaterService;

	@Override
	public void handleRecorderStatusEvent(RecorderStatusChangedEvent evt) {
		synchronizeStatusWithRecorder();
	}

}
