package org.bham.aucom.gui;

import org.bham.aucom.data.NodeGraphXYDataItem;
import org.bham.aucom.util.RedGreenGradientImageGenerator;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class NodeGraphRenderer extends StandardXYItemRenderer {
    private static final long serialVersionUID = 1L;

    @Override
    protected Image getImage(Plot plot, int series, int item, double x, double y) {
        XYPlot p = (XYPlot) plot;
        XYSeriesCollection set = (XYSeriesCollection) p.getDataset();
        XYSeries s = set.getSeries(series);
        NodeGraphXYDataItem itm = (NodeGraphXYDataItem) s.getDataItem(item);
        Image img = RedGreenGradientImageGenerator.getImage(itm.getVal(), itm.getClassId());
        return img;
    }

}
