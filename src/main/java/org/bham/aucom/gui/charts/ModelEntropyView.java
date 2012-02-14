package org.bham.aucom.gui.charts;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tupel;

public class ModelEntropyView extends JPanel {
	private static final long serialVersionUID = 0L;
	private ChartPanel panel;
	private JScrollPane scrollPane;
	private JFrame frame;
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;

	public ModelEntropyView(Model model) {
		setDataset(extractEntropyValues(model));
		initGui();
	}

	private DefaultCategoryDataset extractEntropyValues(Model main) {
		DefaultCategoryDataset out = new DefaultCategoryDataset();
		T2GramModelI model = (T2GramModelI)main;
		HashMatrix<Integer, Integer, ProbabilityDistribution> distribution = model
				.getTransitionMatrix();
		for (Tupel<Integer, Integer> tupel : distribution.keySet()) {
			out.addValue(distribution.get(tupel.getFirstElement(),
					tupel.getSecondElement()).getEntropy(), "entropy", tupel
					.getFirstElement()
					+ "_" + tupel.getSecondElement());
		}
		return out;
	}

	private void initGui() {
		setChart(ChartFactory.createBarChart("", "distribution", "entropy",
				getDataset(), PlotOrientation.VERTICAL, true, true, false));
		setPanel(new ChartPanel(getChart()));
		((CategoryPlot) getChart().getPlot()).getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		this.setLayout(new GridLayout(1,1));
		getPanel().setPreferredSize(new Dimension(4000, 600));
		getPanel().setMaximumDrawWidth(4000);

		scrollPane = new JScrollPane(getPanel());
		scrollPane.setPreferredSize(new Dimension(900, 600));
		this.add(scrollPane);
		this.validate();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// FIX
//		if (args.length == 1) {
//			UUID id;
//			try {
//				
//				id = AucomIO.getInstance().read(new File(args[0]));
//				T2GramFaultDetectionModel model = (T2GramFaultDetectionModel)DataManager.getInstance().getFaultDetectionModelById(id);
//				ModelEntropyView view = new ModelEntropyView(model);
//				JFrame frame = new JFrame();
//				frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//				frame.setLayout(new GridLayout(1, 1));
//				frame.setSize(new Dimension(900, 600));
//				frame.getContentPane().add(view);
//				frame.setVisible(true);
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
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setPanel(ChartPanel panel) {
		this.panel = panel;
	}

	public ChartPanel getPanel() {
		return panel;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

}
