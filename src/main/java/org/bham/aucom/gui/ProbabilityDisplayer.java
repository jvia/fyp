/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistogramView.java
 *
 * Created on 19.01.2010, 13:08:45
 */

package org.bham.aucom.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.models.probability.HistogramDistribution;

/**
 * 
 * @author rgolombe
 */
public class ProbabilityDisplayer extends javax.swing.JFrame {
	private static final long serialVersionUID = 0L;

	/** Creates new form HistogramView */
	public ProbabilityDisplayer(ProbabilityDistribution distributionToDisplay) {
		initComponents();
		String title = distributionToDisplay.getType();
		double[] samples = distributionToDisplay.sample();
		double[] probabilities = distributionToDisplay.sampleProbability();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i=0;i<samples.length;i++) {
			dataset.addValue(probabilities[i],
					"Probabilities", String.valueOf(samples[i]));
		}
		
		// dataset.addValue(dist.getDistributionEntropy(), "entropy", "1");
		JFreeChart chart = ChartFactory.createLineChart(title, // chart title
				"Bins", // domain axis label
				"%", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				false, // tooltips?
				false // URLs?
				);
		chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(viewPanel.getSize());
		this.viewPanel.add(chartPanel);
		this.viewPanel.validate();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		numElements = new javax.swing.JLabel();
		viewPanel = new javax.swing.JPanel();

		setResizable(false);

		numElements.setText("zero");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						numElements).addContainerGap(418, Short.MAX_VALUE))
				.addComponent(viewPanel,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 1200,
						Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												viewPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												500,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												27, Short.MAX_VALUE)
										.addComponent(numElements)
										.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ProbabilityDisplayer(new HistogramDistribution("1", 100))
						.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel numElements;
	private javax.swing.JPanel viewPanel;
	// End of variables declaration//GEN-END:variables

}