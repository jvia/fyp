package org.bham.aucom.gui.charts;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tuple;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;


public class ModelEntropyView extends JPanel {
    private static final long serialVersionUID = 0L;
    private ChartPanel panel;
    private JFreeChart chart;
    private DefaultCategoryDataset dataSet;

    public ModelEntropyView(Model model) {
        setDataSet(extractEntropyValues(model));
        initGui();
    }

    private DefaultCategoryDataset extractEntropyValues(Model main) {
        DefaultCategoryDataset out = new DefaultCategoryDataset();
        HashMatrix<Integer, Integer, ProbabilityDistribution> distribution = main.getTransitionMatrix();

        for (Tuple<Integer, Integer> tuple : distribution.keySet()) {
            out.addValue(distribution.get(tuple.getFirst(),
                                          tuple.getSecond()).getEntropy(), "entropy", tuple
                                                                                                     .getFirst()
                                                                                             + "_" + tuple.getSecond());
        }
        return out;
    }

    private void initGui() {
        setChart(ChartFactory.createBarChart("", "distribution", "entropy",
                                             getDataSet(), PlotOrientation.VERTICAL, true, true, false));
        setPanel(new ChartPanel(getChart()));
        ((CategoryPlot) getChart().getPlot()).getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        this.setLayout(new GridLayout(1, 1));
        getPanel().setPreferredSize(new Dimension(4000, 600));
        getPanel().setMaximumDrawWidth(4000);

        JScrollPane scrollPane = new JScrollPane(getPanel());
        scrollPane.setPreferredSize(new Dimension(900, 600));
        this.add(scrollPane);
        this.validate();
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

    public void setDataSet(DefaultCategoryDataset dataSet) {
        this.dataSet = dataSet;
    }

    public DefaultCategoryDataset getDataSet() {
        return dataSet;
    }

}
