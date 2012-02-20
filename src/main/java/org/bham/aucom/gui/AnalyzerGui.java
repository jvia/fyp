/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 20.11.2009, 16:39:51
 */

package org.bham.aucom.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

/**
 * @author rgolombe
 */
public class AnalyzerGui extends javax.swing.JFrame implements Observer, WindowListener {
    private static final long serialVersionUID = 0L;

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }
//	private static final long serialVersionUID = 1L;
//	private FaultDetectionModel currentModel;
//	private ScoreFrameListModel scorePlots;
//	ExampleFileFilter testFileFilter = new ExampleFileFilter();
//	ExampleFileFilter modelFilter = new ExampleFileFilter();
//	ExampleFileFilter thresholdFilter = new ExampleFileFilter();
//	ExampleFileFilter testedDataFilter = new ExampleFileFilter();
//
//	/** Creates new form MainFrame */
//	public AnalyzerGui() {
//		initComponents();
//		initFrameListModel();
//		initDatasetListModel();
//		initDatasetPopupMenu();
//		initScoreListModel();
//		initScorePopupMenu();
//		initModelListModel();
//		initModelPopupMenu();
//		initFilter();
//		validate();
//	}
//
//	public void initFilter() {
//		this.testFileFilter.addExtension("dat");
//		this.testFileFilter.setDescription("Experiment data");
//		this.testFileFilter.addExtension("csv");
//		this.testFileFilter.setDescription("Experiment data");
//		this.modelFilter.addExtension("md");
//		this.modelFilter.setDescription("Trained Model");
//		this.thresholdFilter.addExtension("th");
//		this.thresholdFilter.setDescription("Tresholds");
//		this.testedDataFilter.addExtension("tdat");
//		this.testedDataFilter.setDescription("Tested .dat data");
//	}
//
//	public void initModelListModel() {
//		this.getModelList().setModel(new AucomListModelAdapter(FaultDetectionModel.class));
//		this.getDataSetJList().setCellRenderer(new DatasetListCellRenderer());
//		this.getModelList().setCellRenderer(new ModelListCellRenderer());
//	}
//
//	private void initFrameListModel() {
//		this.setScorePlots(new ScoreFrameListModel());
//	}
//
//	private void initScoreListModel() {
////		this.getResultsList().setModel(Data
////				new Aucom ListModelAdapter().getInstance().getScoreSequencesAsListModel());
//		this.getResultsList().setCellRenderer(new ScoreListCellRenderer());
//		this.getResultsList().addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				if (arg0.getClickCount() == 2 && !arg0.isPopupTrigger()) {
//					JList l = (JList) arg0.getSource();
//					TimeSeries<Score> sequence = (TimeSeries<Score>) l.getSelectedValue();
//					setResultsInfo(sequence);
//				}
//
//			}
//		});
//	}
//
//	private void initDatasetListModel() {
//
//		this.getDataSetJList().setModel(new AucomListModelAdapter(DataType.class));
//		this.getDataSetJList().getModel().addListDataListener(new ListDataListener() {
//			@Override
//			public void intervalRemoved(ListDataEvent arg0) {
//				updateTestSubmenuItems(arg0);
//				updateThresholdSubmenuItems(arg0);
//				updateTrainSubmenuItems(arg0);
//			}
//
//			@Override
//			public void intervalAdded(ListDataEvent arg0) {
//				updateTestSubmenuItems(arg0);
//				updateThresholdSubmenuItems(arg0);
//				updateTrainSubmenuItems(arg0);
//			}
//
//			@Override
//			public void contentsChanged(ListDataEvent arg0) {
//				updateTestSubmenuItems(arg0);
//				updateThresholdSubmenuItems(arg0);
//				updateTrainSubmenuItems(arg0);
//			}
//		});
//	}
//
//	void updateThresholdSubmenuItems(ListDataEvent arg0) {
//		System.out.println("updateThresholdSubmenuItems");
//		this.getSubMenuTresholdSelectedIndexmodelPopup().removeAll();
//		List<TimeSeries<Observation>> list = DataManager.getInstance().getDataSequencesAsList();
//		for (final TimeSeries<Observation> sequence: list) {
//			final String name = sequence.getName();
//			JMenuItem menuitem = new JMenuItem(name);
//
//			menuitem.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					calculateThresholdOnActiveModel(sequence);
//				}
//			});
//			this.getSubMenuTresholdSelectedIndexmodelPopup().add(menuitem);
//		}
//
//	}
//
//	void updateTestSubmenuItems(ListDataEvent arg0) {
//		this.getSubMenuTestSelectedIndexmodelPopup().removeAll();
//		List<DataSequence> list = DataManager.getInstance().getDataSequencesAsList();
//		for (final DataSequence sequence: list) {
//			final String name = sequence.getName();
//			JMenuItem menuitem = new JMenuItem(name);
//			menuitem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					info("displaying score sequence name dialog");
//					String result = JOptionPane.showInputDialog(AnalyzerGui.this, "type name of the result", sequence.getName());
//					if ((result != null) && (result.length() > 0)) {
//						testDataSequenceOnActiveModel(sequence, result);
//					} else {
//						severe("coudn't test " + sequence.getName() + " name form dialog is " + result);
//					}
//				}
//			});
//			getSubMenuTestSelectedIndexmodelPopup().add(menuitem);
//		}
//
//	}
//
//	void updateTrainSubmenuItems(ListDataEvent arg0) {
//		getSubMenuTrainSelectedIndexModelPopup().removeAll();
//		List<DataSequence> list = DataManager.getInstance().getDataSequencesAsList();
//		for (final DataSequence sequence: list) {
//			final String name = sequence.getName();
//			JMenuItem menuitem = new JMenuItem(name);
//			menuitem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					trainModel(sequence);
//				}
//			});
//			getSubMenuTrainSelectedIndexModelPopup().add(menuitem);
//		}
//
//	}
//
//	public void calculateThresholdOnActiveModel(DataSequence sequence) {
//		if (getActiveModel() == null) {
//			System.out.println("Error: active Model missing");
//			return;
//		}
//		try {
//			// TODO: change the way the sequence is named
//			getActiveModel().calculateThreshold(sequence);
//		} catch (IllegalStateException e) {
//			System.out.println(e.getMessage());
//		}
//		if (!getActiveModel().isTrained())
//			System.out.println("Warning: Model not trained");
//	}
//
//	public void testDataSequenceOnActiveModel(DataSequence sequence, String resultName) {
//		info("testDataSequenceOnActiveModel called");
//		if (getActiveModel() == null) {
//			severe("Active model is null");
//			return;
//		}
//		try {
////			ScoreSequence scoreSequence = DataManager.getInstance().newScoreSequence(resultName, sequence, getActiveModel());
////			if(sequence.getType().equals(DataSequence.DYNAMIC_SOURCE)){
////				getActiveModel().setStopOnLastItem(false);
////			}else{
////				getActiveModel().setStopOnLastItem(true);
////			}
//			ScoreSequence scoreSequence = getActiveModel().startMonitor(sequence);
//			DataManager.getInstance().addScoreSequence(scoreSequence);
//			Logger.getLogger(this.getClass().getCanonicalName()).info("testing " + sequence.getName() + " on model " + getActiveModel()+ " output sequence is " + scoreSequence.getName());
//		} catch (IllegalStateException e) {
//			System.out.println(e.getMessage());
//		}
//		if (!getActiveModel().isTrained())
//			System.out.println("Warning: Model not trained");
//	}
//
//	public void initScorePopupMenu() {
//		getResultsList().addMouseListener(new PopupScoreListListener(getResultsList(), getScorePlots(), this));
//	}
//
//	public void severe(String msg) {
//		Logger.getLogger(this.getClass().getName()).severe(msg);
//	}
//
//	public void info(String info) {
//		Logger.getLogger(this.getClass().getName()).info(info);
//	}
//
//	/*
//	 * Build the popup menu for models consisting of the following items: - test
//	 * - train - calculcate threshold - rename model - save model - reset model
//	 * - delete model - cancel action/specific action (dynamic)
//	 */
//	public String generateInforStringForDataSet(DataSequence sequence){
//		String out = "-------------------\n"; 
//		out += "name: "  + sequence.getName() + "\n";
//		out += "number events: "  + sequence.size() + "\n";
//		AbstractData[] dataArray = new AbstractData[sequence.size()];
//		String numTypes = "unknown";
//		if(sequence.containsAttribute(NUM_TYPES)){
//			numTypes = sequence.getAttributeValue(NUM_TYPES);
//		}
//		out += "data types: "  + numTypes + "\n";
//		out += "duration: " + (sequence.get(sequence.size()-1).getTimestamp() + sequence.get(0).getTimestamp())/1000 + " seconds\n";
//		out += 		 "-------------------"; 
//		return out;
//	}
//	public String generateInfoStringForModel(T2GramSytemModel model){
//		String out = "";
//		out += 		 "-------------------\n"; 
//		out += "is trained: " + model.isTrained() +  "\n";
//		out += "number trained Distributions: " + model.getModel().getNumberAvailableDistirbutions() +  "\n";
//		out += "number possible Distributions: " + model.getModel().getNumberPossibleDistirbutions() +  "\n";
//		out += 		 "-------------------"; 
//		return out;
//	}
//	public void initDatasetPopupMenu() {
//		JPopupMenu seletectedDatasetPopup = new JPopupMenu();
//		JMenuItem menuItem;
//		menuItem = new JMenuItem("info");
//		menuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				DataSequence selectedValue = (DataSequence)AnalyzerGui.this.getDataSetJList().getSelectedValue();
//				final String infoString = generateInforStringForDataSet(selectedValue);
//				java.awt.EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						InfoPanel infoPanel = new InfoPanel(infoString);
//						Point locationMainFrame = AnalyzerGui.this.getLocation();
//						Dimension dimensionMainFrame = AnalyzerGui.this.getSize();
//						Point infoPanelLocation = new Point((int)(locationMainFrame.getX() + dimensionMainFrame.getWidth()), (int)(locationMainFrame.getY()-dimensionMainFrame.getHeight()));
//						infoPanel.setLocation(infoPanelLocation);
//						infoPanel.setVisible(true);
//					}
//				});
//				
//			}
//		});
//		seletectedDatasetPopup.add(menuItem);
//		menuItem = new JMenuItem("unload");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// delete dataset.
//			}
//		});
//		seletectedDatasetPopup.add(menuItem);
//
//		JPopupMenu datasetPopup = new JPopupMenu();
//		menuItem = new JMenuItem("load");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				displayDatasetFileDialog(arg0);
//			}
//		});
//		datasetPopup.add(menuItem);
//		getDataSetJList().addMouseListener(new PopupDatasetListener(getDataSetJList(), seletectedDatasetPopup, datasetPopup));
//	}
//
//	public void initModelPopupMenu() {
//		JMenuItem menuItem;
//		setSelectedIndexModelPopup(new JPopupMenu());
//		menuItem = new JMenuItem("info");
//		menuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				T2GramSytemModel selectedModel = (T2GramSytemModel)AnalyzerGui.this.getModelList().getSelectedValue();
//				final String infoString = generateInfoStringForModel(selectedModel);
//				java.awt.EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						InfoPanel infoPanel = new InfoPanel(infoString);
//						Point locationMainFrame = AnalyzerGui.this.getLocation();
//						Dimension dimensionMainFrame = AnalyzerGui.this.getSize();
//						Point infoPanelLocation = new Point((int)(locationMainFrame.getX() + dimensionMainFrame.getWidth()), (int)(locationMainFrame.getY()-dimensionMainFrame.getHeight()));
//						infoPanel.setLocation(infoPanelLocation);
//						infoPanel.setVisible(true);
//					}
//				});
//				
//			}
//		});
//		getSelectedIndexModelPopup().add(menuItem);
//		setSubMenuTestSelectedIndexmodelPopup(new JMenu("test"));
//		getSelectedIndexModelPopup().add(getSubMenuTestSelectedIndexmodelPopup());
//
//		setSubMenuTrainSelectedIndexModelPopup(new JMenu("train"));
//		getSelectedIndexModelPopup().add(getSubMenuTrainSelectedIndexModelPopup());
//
//		setSubMenuTresholdSelectedIndexmodelPopup(new JMenu("calc threshold"));
//		getSelectedIndexModelPopup().add(getSubMenuTresholdSelectedIndexmodelPopup());
//
//		menuItem = new JMenuItem("roc");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO remove
//			}
//		});
//		getSelectedIndexModelPopup().add(menuItem);
//
//		menuItem = new JMenuItem("rename");
//		// menuItem.addActionListener(this);
//		getSelectedIndexModelPopup().add(menuItem);
//
//		menuItem = new JMenuItem("save");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				DataManager.getInstance().saveModel(AnalyzerGui.this.getActiveModel());
//			}
//
//		});
//		// menuItem.addActionListener(this);
//		getSelectedIndexModelPopup().add(menuItem);
//
//		menuItem = new JMenuItem("reset");
//		// menuItem.addActionListener(this);
//		getSelectedIndexModelPopup().add(menuItem);
//
//		getSelectedIndexModelPopup().add(new JSeparator());
//		getSelectedIndexModelPopup().add(new JSeparator());
//
//		menuItem = new JMenuItem("unload");
//		// menuItem.addActionListener(this);
//		getSelectedIndexModelPopup().add(menuItem);
//
//		menuItem = new JMenuItem("distributions");
//		menuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				Logger.getLogger(AnalyzerGui.this.getClass().getCanonicalName()).info("going to display distributions of " + getActiveModel());
//				if (getActiveModel().getModel().getTransitionMatrix().values().size() != 0
//						) {
//					
//				}
//			}
//		});
//		getSelectedIndexModelPopup().add(menuItem);
//		menuItem = new JMenuItem("entropy");
//		menuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ModelEntropyView view = new ModelEntropyView(AnalyzerGui.this.currentModel);
//				JFrame frame = new JFrame();
//				frame.setLayout(new GridLayout(1, 1));
//				frame.setSize(new Dimension(900, 600));
//				frame.getContentPane().add(view);
//				frame.setVisible(true);
//			}
//		});
//		getSelectedIndexModelPopup().add(menuItem);
//
//		JPopupMenu modelPopup = new JPopupMenu();
//		menuItem = new JMenuItem("load");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				displayModelloadFileDialog(arg0);
//			}
//		});
//		modelPopup.add(menuItem);
//
//		menuItem = new JMenuItem("new");
//		menuItem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				Logger.getLogger(AnalyzerGui.this.getClass().getCanonicalName()).info("new model requested");
//				T2GramFaultDetectionModel newModel = createNewAucomModel();
//				if(newModel!= null)
//					DataManager.getInstance().addModel(newModel);
//			}
//		});
//		modelPopup.add(menuItem);
//		getModelList().addMouseListener(new ModelPopupListener(getModelList(), getSelectedIndexModelPopup(), modelPopup));
//	}
//	T2GramSytemModel createNewAucomModel() {
//		CreateModelDialog modelDialog = new CreateModelDialog(this);
//		modelDialog.setVisible(true);
//		
//		return modelDialog.getModel();
//	}
//	/**
//	 * This method is called from within the constructor to initialize the form.
//	 * WARNING: Do NOT modify this code. The content of this method is always
//	 * regenerated by the Form Editor.
//	 */
//	@SuppressWarnings("unchecked")
//	// <editor-fold defaultstate="collapsed"
//	// <editor-fold defaultstate="collapsed"
//	// <editor-fold defaultstate="collapsed"
//	// <editor-fold defaultstate="collapsed"
//	// <editor-fold defaultstate="collapsed"
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//
//        this.buttonGroup1 = new javax.swing.ButtonGroup();
//        this.jRadioButton1 = new javax.swing.JRadioButton();
//        this.jPanel3 = new javax.swing.JPanel();
//        this.jPanel5 = new javax.swing.JPanel();
//        this.jScrollPane1 = new javax.swing.JScrollPane();
//        this.modelList = new javax.swing.JList();
//        this.modelInfo = new javax.swing.JPanel();
//        this.tresholdTextField = new javax.swing.JTextField();
//        this.binSizeLabel = new javax.swing.JLabel();
//        this.statusLabel = new javax.swing.JLabel();
//        this.modelTrainedLabel = new javax.swing.JLabel();
//        this.jPanel7 = new javax.swing.JPanel();
//        this.jScrollPane3 = new javax.swing.JScrollPane();
//        this.dataSetJList = new javax.swing.JList();
//        this.datasetInfo = new javax.swing.JPanel();
//        this.jLabel5 = new javax.swing.JLabel();
//        this.jPanel8 = new javax.swing.JPanel();
//        this.jScrollPane2 = new javax.swing.JScrollPane();
//        this.resultsList = new javax.swing.JList();
//        this.resultsInfo = new javax.swing.JPanel();
//        this.lengthLabel = new javax.swing.JLabel();
//        this.numEventsLabel = new javax.swing.JLabel();
//        this.resultNameLabel = new javax.swing.JLabel();
//        this.anomalyLabel = new javax.swing.JLabel();
//
//        this.jRadioButton1.setText("jRadioButton1");
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        this.jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
//        this.jPanel3.setLayout(new java.awt.GridLayout(3, 0, 5, 0));
//
//        this.jPanel5.setLayout(new java.awt.GridLayout(1, 2));
//
//        this.jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
//
//        this.modelList.setBorder(javax.swing.BorderFactory.createTitledBorder("models"));
//        this.modelList.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                modelListMouseClicked(evt);
//            }
//        });
//        this.modelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
//                modelListValueChanged(evt);
//            }
//        });
//        this.jScrollPane1.setViewportView(this.modelList);
//
//        this.jPanel5.add(this.jScrollPane1);
//
//        this.modelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("model info"));
//        this.modelInfo.setLayout(new java.awt.GridLayout(4, 1));
//
//        this.tresholdTextField.setText("0.0");
//        this.tresholdTextField.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                tresholdTextFieldActionPerformed(evt);
//            }
//        });
//        this.modelInfo.add(this.tresholdTextField);
//        this.modelInfo.add(this.binSizeLabel);
//        this.modelInfo.add(this.statusLabel);
//        this.modelInfo.add(this.modelTrainedLabel);
//
//        this.jPanel5.add(this.modelInfo);
//
//        this.jPanel3.add(this.jPanel5);
//
//        this.jPanel7.setLayout(new java.awt.GridLayout(1, 0));
//
//        this.dataSetJList.setBorder(javax.swing.BorderFactory.createTitledBorder("datasets"));
//        this.jScrollPane3.setViewportView(this.dataSetJList);
//
//        this.jPanel7.add(this.jScrollPane3);
//
//        this.datasetInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("dataset info"));
//        this.datasetInfo.setLayout(new java.awt.GridLayout(4, 0));
//        this.datasetInfo.add(this.jLabel5);
//
//        this.jPanel7.add(this.datasetInfo);
//
//        this.jPanel3.add(this.jPanel7);
//
//        this.jPanel8.setLayout(new java.awt.GridLayout(1, 2));
//
//        this.resultsList.setBorder(javax.swing.BorderFactory.createTitledBorder("results"));
//        this.resultsList.setMaximumSize(new java.awt.Dimension(80, 95));
//        this.resultsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
//                resultsListValueChanged(evt);
//            }
//        });
//        this.jScrollPane2.setViewportView(this.resultsList);
//
//        this.jPanel8.add(this.jScrollPane2);
//
//        this.resultsInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("results info"));
//        this.resultsInfo.setLayout(new java.awt.GridLayout(4, 0));
//        this.resultsInfo.add(this.lengthLabel);
//        this.resultsInfo.add(this.numEventsLabel);
//        this.resultsInfo.add(this.resultNameLabel);
//        this.resultsInfo.add(this.anomalyLabel);
//
//        this.jPanel8.add(this.resultsInfo);
//
//        this.jPanel3.add(this.jPanel8);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(this.jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addComponent(this.jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addContainerGap())
//        );
//
//        pack();
//    }// </editor-fold>//GEN-END:initComponents
//
//	private void tresholdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tresholdTextFieldActionPerformed
//		//TODO fix the setting of new thresholds
////		if (getActiveModel() != null) {
////			try {
////				double val = Double.valueOf(((JTextField) evt.getSource()).getText());
////				getActiveModel().getThreshold().setMeanValue(val);
////			} catch (NumberFormatException e) {
////				e.printStackTrace();
////			}
////		}
//	}// GEN-LAST:event_tresholdTextFieldActionPerformed
//
//	private void resultsListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_resultsListValueChanged
//		TimeSeries<Score> seq = (TimeSeries<Score>) ((JList)evt.getSource()).getSelectedValue();
//		setResultsInfo(seq);
//	}// GEN-LAST:event_resultsListValueChanged
//
//	public void setResultsInfo(TimeSeries<Score> scoreSequence) {
//		long first = 0;
//		long last = 0;
//		if (scoreSequence.size() > 0) {
//			first = ((Score) scoreSequence.get(0)).getTimestamp();
//			last = ((Score) scoreSequence.get(scoreSequence.size() - 1)).getTimestamp();
//		}
//		long diff = last - first;
//		this.resultNameLabel.setText("generator: " + scoreSequence.getGeneratorID());
//		this.lengthLabel.setText(diff / 1000 + " seconds");
//		this.numEventsLabel.setText(scoreSequence.size() + " events");
//		ScoreTimeSeriesStatistics statistics = new ScoreTimeSeriesStatistics(scoreSequence);
//		this.anomalyLabel.setText("% anomaly: " + statistics.getAnomalyValue());
//	}
//
//
//
//	void displayModelloadFileDialog(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadModelActionPerformed
//		JFileChooser fc = new JFileChooser();
//		fc.setFileFilter(this.modelFilter);
//		int result = fc.showOpenDialog(this);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File f = fc.getSelectedFile();
//			try {
//				DataLoader.getInstance().load(f);
//			} catch (FileNotFoundException exception) {
//				// TODO Auto-generated catch block
//				exception.printStackTrace();
//			} catch (ValidityException exception) {
//				// TODO Auto-generated catch block
//				exception.printStackTrace();
//			} catch (DataAllreadyExistsException exception) {
//				// TODO Auto-generated catch block
//				exception.printStackTrace();
//			} catch (ParsingException exception) {
//				// TODO Auto-generated catch block
//				exception.printStackTrace();
//			} catch (IOException exception) {
//				// TODO Auto-generated catch block
//				exception.printStackTrace();
//			}
//		}
//	}// GEN-LAST:event_loadModelActionPerformed
//
//	public void recalculateAnomaly(TimeSeries<Score> sequence) {
//		if (getActiveModel() != null) {
//			info("recalculateAnomaly called on " + sequence);
//			try {
//				// TODO build a graph which recalculates the score on the timeseries 
//				//getActiveModel().startMonitor(sequence);
//			} catch (Throwable e) {
//				e.printStackTrace();
//			}
//		} else {
//			severe("not active model present");
//		}
//	}
//
//	
//
//	private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_recordButtonActionPerformed
//		JFileChooser fileChooser = new JFileChooser(DataManager.getInstance().getCurrentWorkingDirectoryPath() + "/dat");
//		fileChooser.setFileFilter(this.testFileFilter);
//		int result = fileChooser.showOpenDialog(this);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File f = fileChooser.getSelectedFile();
//
//			try {
//				if (!f.exists()) {
//					f.createNewFile();
//					;
//					if (!f.canWrite())
//						throw new IOException("cannot write to file " + f.getName());
//				}
//
//			} catch (FileNotFoundException e) {
//				JOptionPane.showMessageDialog(this, "File: " + e.getMessage() + " does not exist");
//				e.printStackTrace();
//			} catch (IOException e) {
//				JOptionPane.showMessageDialog(this, e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}// GEN-LAST:event_recordButtonActionPerformed
//
//	private void modelListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_modelListValueChanged
//		T2GramSytemModel model = (T2GramSytemModel) getModelList().getSelectedValue();
//		System.out.println("setting active model to " + model);
//		setActiveModel(model);
//		setInfoModelPanel(model);
//
//	}// GEN-LAST:event_modelListValueChanged
//
//	public void setInfoModelPanel(T2GramSytemModel model) {
//		// TODO fix the info model panel 
//		this.modelInfo.setBorder(new TitledBorder("model info: " + model));
//		this.tresholdTextField.setText(String.valueOf(model.getAnomalyClassificator().toString()));
//		this.modelTrainedLabel.setText("Is trained: " + model.isTrained());
//		this.statusLabel.setText("Is acting: " + model.isActing());
//	}
//
//	private void modelListMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_modelListMouseClicked
//		if (evt.getClickCount() == 2) {
//			setInfoModelPanel(getActiveModel());
//		}
//	}// GEN-LAST:event_modelListMouseClicked
//
//	void trainModel(TimeSeries<Observation> sequence) {// GEN-FIRST:event_jMenuItem1ActionPerformed
//		getActiveModel().startTrain(sequence);
//	}// GEN-LAST:event_jMenuItem1ActionPerformed
//
//	public void addDataSet(TimeSeries<Observation> data){
//		DataManager.getInstance().addDataSequence(data);
//	}
//	void displayDatasetFileDialog(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
//		/*
//		 * Check whether the active model is trained. If not display a warning.
//		 * Check whether the active model is currently in action. If so, reject.
//		 * Else show file selection, extract data, encapsulate it in a
//		 * DataSequence object and save it in the dataset hashmap
//		 */
//		JFileChooser fileChooser = new JFileChooser(DataManager.getInstance().getCurrentWorkingDirectory().getAbsolutePath() + "/csv");
//		fileChooser.setFileFilter(this.testFileFilter);
//		int result = fileChooser.showOpenDialog(this);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File f = fileChooser.getSelectedFile();
//			DataManager.getInstance().loadDatasetFromFile(f);
//		}
//	}// GEN-LAST:event_jMenuItem2ActionPerformed
//
//	/**
//	 * @param args
//	 *            the command line arguments
//	 */
//	public static void main(String args[]) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new AnalyzerGui().setVisible(true);
//			}
//		});
//	}
//
//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private javax.swing.JLabel anomalyLabel;
//    private javax.swing.JLabel binSizeLabel;
//    private javax.swing.ButtonGroup buttonGroup1;
//    private javax.swing.JList dataSetJList;
//    private javax.swing.JPanel datasetInfo;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JPanel jPanel3;
//    private javax.swing.JPanel jPanel5;
//    private javax.swing.JPanel jPanel7;
//    private javax.swing.JPanel jPanel8;
//    private javax.swing.JRadioButton jRadioButton1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JScrollPane jScrollPane2;
//    private javax.swing.JScrollPane jScrollPane3;
//    private javax.swing.JLabel lengthLabel;
//    private javax.swing.JPanel modelInfo;
//    private javax.swing.JList modelList;
//    private javax.swing.JLabel modelTrainedLabel;
//    private javax.swing.JLabel numEventsLabel;
//    private javax.swing.JLabel resultNameLabel;
//    private javax.swing.JPanel resultsInfo;
//    private javax.swing.JList resultsList;
//    private javax.swing.JLabel statusLabel;
//    private javax.swing.JTextField tresholdTextField;
//    // End of variables declaration//GEN-END:variables
//	private JPopupMenu selectedIndexModelPopup;
//	private JMenu subMenuTestSelectedIndexmodelPopup;
//	private JMenu subMenuTresholdSelectedIndexmodelPopup;
//	private JMenu subMenuTrainSelectedIndexModelPopup;
//
//	public void update(Observable o, Object arg) {
//		System.out.println(arg);
//	}
//
//	public String[] displayCreateModelFrame() {
//		CreateModelFrame f = new CreateModelFrame();
//		f.setModalityType(ModalityType.APPLICATION_MODAL);
//		f.setVisible(true);
//		String[] out = { "#", "#" };
//		out[0] = f.getModelName();
//		out[1] = f.getBinSizeAsString();
//		System.out.println(out[1]);
//		return out;
//
//	}
//
//	public void windowActivated(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void windowClosed(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void windowClosing(WindowEvent arg0) {
//	}
//
//	public void windowDeactivated(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void windowDeiconified(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void windowIconified(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void windowOpened(WindowEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	private void setSubMenuTestSelectedIndexmodelPopup(JMenu subMenuTestSelectedIndexmodelPopup) {
//		this.subMenuTestSelectedIndexmodelPopup = subMenuTestSelectedIndexmodelPopup;
//	}
//
//	private JMenu getSubMenuTestSelectedIndexmodelPopup() {
//		return this.subMenuTestSelectedIndexmodelPopup;
//	}
//
//	public void setSubMenuTrainSelectedIndexModelPopup(JMenu subMenuTrainSelectedIndexModelPopup) {
//		this.subMenuTrainSelectedIndexModelPopup = subMenuTrainSelectedIndexModelPopup;
//	}
//
//	public JMenu getSubMenuTrainSelectedIndexModelPopup() {
//		return this.subMenuTrainSelectedIndexModelPopup;
//	}
//
//	public void setActiveModel(FaultDetectionModel activeModel) {
//		this.currentModel = activeModel;
//	}
//
//	public FaultDetectionModel getActiveModel() {
//		return this.currentModel;
//	}
//
//	public void setResultsList(javax.swing.JList resultsList) {
//		this.resultsList = resultsList;
//	}
//
//	public javax.swing.JList getResultsList() {
//		return this.resultsList;
//	}
//
//	public void setScorePlots(ScoreFrameListModel scorePlots) {
//		this.scorePlots = scorePlots;
//	}
//
//	public ScoreFrameListModel getScorePlots() {
//		return this.scorePlots;
//	}
//
//	public void setDataSetJList(javax.swing.JList dataSetJList) {
//		this.dataSetJList = dataSetJList;
//	}
//
//	public javax.swing.JList getDataSetJList() {
//		return this.dataSetJList;
//	}
//
//	public void setSelectedIndexModelPopup(JPopupMenu selectedIndexModelPopup) {
//		this.selectedIndexModelPopup = selectedIndexModelPopup;
//	}
//
//	public JPopupMenu getSelectedIndexModelPopup() {
//		return this.selectedIndexModelPopup;
//	}
//
//	public void setSubMenuTresholdSelectedIndexmodelPopup(JMenu subMenuTresholdSelectedIndexmodelPopup) {
//		this.subMenuTresholdSelectedIndexmodelPopup = subMenuTresholdSelectedIndexmodelPopup;
//	}
//
//	public JMenu getSubMenuTresholdSelectedIndexmodelPopup() {
//		return this.subMenuTresholdSelectedIndexmodelPopup;
//	}
//
//	public void setModelList(javax.swing.JList modelList) {
//		this.modelList = modelList;
//	}
//
//	public javax.swing.JList getModelList() {
//		return this.modelList;
//	}
}
