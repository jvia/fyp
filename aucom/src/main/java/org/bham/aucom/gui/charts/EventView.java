package org.bham.aucom.gui.charts;

import org.bham.aucom.data.NodeGraphXYDataItem;
import org.bham.aucom.data.Score;
import org.bham.aucom.gui.NodeGraphRenderer;
import org.bham.aucom.util.RedGreenGradientImageGenerator;
import org.jfree.chart.AucomChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;


public class EventView {
    XYSeriesCollection dataset = new XYSeriesCollection();
    private JFreeChart chart;
    private int intervalsize = 99;
    private double referenceValue;
    public int numberRows;
    private JFrame f;
    private ChartPanel tmp;

    public EventView(ArrayList<Score> data, double referenceValue) {
        initChart();
        this.referenceValue = referenceValue;
        add(data);
        f.setSize(new Dimension(1024, 120 + 21 * Math.min(numberRows, 30)));
    }

    public void info(String info) {
        Logger.getLogger(this.getClass().getName()).info(info);
    }

    public void add(ArrayList<Score> data) {
        XYSeries series = new XYSeries("test", false);
        dataset.addSeries(series);
        XYPlot plot = chart.getXYPlot();
        long firstTimestamp = data.get(0).getTimestamp();
        long lastTimestamp = data.get(data.size() - 1).getTimestamp();
        double lastDrawingTimestamp = 0;
        info("firstTimestamp " + firstTimestamp + "|lastTimestamp " + lastTimestamp);

        int length = Integer.toString(intervalsize).toCharArray().length;
        info("org " + intervalsize);
        intervalsize = (int) ((Integer.valueOf(String.valueOf(Integer.toString(intervalsize).toCharArray()[0])) + 1) * Math.pow(10, length - 1));
        info("corrected " + intervalsize);
        double minXdifference = 0.035 * intervalsize;
        numberRows = (int) (lastTimestamp - firstTimestamp) / intervalsize;
        LinkedHashMap<Integer, Integer> classids = new LinkedHashMap<Integer, Integer>();
        for (Score s : data) {
            if (!classids.containsKey(s.getEventType()))
                classids.put(s.getEventType(), 0);
            classids.put(s.getEventType(), classids.get(s.getEventType()) + 1);
            double currentTimestamp = Math.max(s.getTimestamp(), lastDrawingTimestamp + minXdifference);

            double col = (currentTimestamp - firstTimestamp) % intervalsize;
            double row = 1 + Math.floor((currentTimestamp - firstTimestamp) / intervalsize);//*intervalsize

            series.add(new NodeGraphXYDataItem(col, row, s.getValue() / referenceValue, s.getEventType()));
            lastDrawingTimestamp = currentTimestamp;
        }
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //axis.setTickUnit(new NumberTickUnit(intervalsize));
        axis.setMinorTickMarksVisible(false);
        axis.setAxisLineVisible(false);
        double domainUpper = plot.getDomainAxis().getUpperBound();
//		axis.setLowerBound(- intervalsize*0.2);
        plot.getDomainAxis().setLowerBound(-intervalsize * 0.05);
        plot.getDomainAxis().setUpperBound(domainUpper);
        plot.getDomainAxis().setLabel("Events in a " + intervalsize + " millisecond interval");
        plot.getRangeAxis().setLowerBound(0);
        plot.getRangeAxis().setUpperBound(Math.min(numberRows + 0.5, 25));
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(classids.size(), 1));
        for (Integer id : classids.keySet()) {
            String className = "noClassName";
            try {
//				className = SourceScopeTypeEncoder.getInstance().decode(id);
                className = "NoClassName";
            } catch (Exception e) {
            }
            String l = id + " : " + className + " : " + classids.get(id);
            classPanel.add(new JLabel(l));
        }
        f.getContentPane().add(classPanel, BorderLayout.LINE_END);
    }

    @SuppressWarnings("deprecation")
    public void initChart() {
        // System.out.println(inData);
        dataset = new XYSeriesCollection();
        chart = ChartFactory.createXYLineChart("Event Sequence", "Events in a " + intervalsize + " milliseconds interval",
                                               "Interval number", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(new NodeGraphRenderer());
        NodeGraphRenderer renderer = (NodeGraphRenderer) plot.getRenderer();
        renderer.setPlotLines(false);
        renderer.setPlotImages(true);
        tmp = new AucomChartPanel(chart);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.black);
//		plot.setRangeGridlinesVisible(false);
        plot.getRangeAxis().setInverted(true);
        plot.setDomainAxisLocation(AxisLocation.getOpposite(plot.getDomainAxisLocation()));
        f = new JFrame();
        f.setLayout(new BorderLayout());

        f.getContentPane().add(tmp, BorderLayout.CENTER);
        JPanel colorLegend = new JPanel();
        colorLegend.setLayout(new BorderLayout());
        ArrayList<Color> abnormalColors = RedGreenGradientImageGenerator.getAbnormalcolors();
        JPanel abnormalColorsPanel = new JPanel();
        abnormalColorsPanel.setLayout(new GridLayout(1, 3 + abnormalColors.size()));
        abnormalColorsPanel.add(new JLabel("Abnormal: "));
        abnormalColorsPanel.add(new JLabel("(low)"));
        for (int i = abnormalColors.size() - 1; i >= 0; i--) {
            System.out.println(i);
            Color abnormal = abnormalColors.get(i);
            JPanel tmp = new JPanel();
            tmp.setBackground(abnormal);
            tmp.setSize(new Dimension(5, 5));
            abnormalColorsPanel.add(tmp);
        }
        abnormalColorsPanel.add(new JLabel("(high)"));
        ArrayList<Color> normalColors = RedGreenGradientImageGenerator.getNormalcolors();
        JPanel normalColorsPanel = new JPanel();
        normalColorsPanel.add(new JLabel("Normal: "));
        normalColorsPanel.add(new JLabel("(low)"));
        normalColorsPanel.setLayout(new GridLayout(1, 3 + normalColors.size()));
        for (Color normal : normalColors) {
            JPanel tmp = new JPanel();
            tmp.setBackground(normal);
            tmp.setSize(new Dimension(5, 5));
            normalColorsPanel.add(tmp);
        }
        normalColorsPanel.add(new JLabel("(high)"));
        colorLegend.add(abnormalColorsPanel, BorderLayout.CENTER);
        colorLegend.add(normalColorsPanel, BorderLayout.SOUTH);
        f.getContentPane().add(colorLegend, BorderLayout.SOUTH);
//		JSlider slider = new JSlider(0,10);
//		slider.setOrientation(SwingConstants.VERTICAL);
//		f.getContentPane().add(slider, BorderLayout.LINE_END);
        f.validate();
        f.setVisible(true);

    }
}