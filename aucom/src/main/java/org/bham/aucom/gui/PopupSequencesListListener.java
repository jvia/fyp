package org.bham.aucom.gui;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.encoder.Encoder;
import org.bham.aucom.data.encoder.SourceScopeTypeEncoder;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.gui.charts.ProbabilityChartFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PopupSequencesListListener extends MouseAdapter {
    JPopupMenu selectedIndexPopupMenu;
    private ProbabilityChartFrame frame;
    private JList sequenceList;
    Encoder classifier = SourceScopeTypeEncoder.getInstance();

    public PopupSequencesListListener(ProbabilityChartFrame frame, JList sequenceList) {
        this.selectedIndexPopupMenu = new JPopupMenu("menu");
        this.frame = frame;
        this.setSequenceList(sequenceList);
        JMenuItem item = new JMenuItem("show events");
        item.addActionListener(new ActionListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
            }
        });
        this.selectedIndexPopupMenu.add(item);
        item = new JMenuItem("show histogram");
        item.addActionListener(new ActionListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
            }
        });
        this.selectedIndexPopupMenu.add(item);
        item = new JMenuItem("show threshold");
        item.addActionListener(new ActionListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().getScoreXYSeries((TimeSeries<Score>) getSequenceList().getSelectedValue());
            }
        });
        //TODO add action to show threshold
        this.selectedIndexPopupMenu.add(item);

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        maybePopup(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        maybePopup(arg0);
    }

    public void maybePopup(MouseEvent arg0) {
        Point klickLocation = arg0.getPoint();
        JList l = (JList) arg0.getSource();
        int selectedIndex = l.getSelectedIndex();
        int clickedIndex = l.locationToIndex(klickLocation);
        if (arg0.isPopupTrigger()) {
            Rectangle boundary = l.getCellBounds(clickedIndex, clickedIndex);
            boolean isHit = boundary.contains(klickLocation);
            System.out.println("boundary" + boundary);
            System.out.println("klickLocation" + klickLocation);
            System.out.println("selectedIndex" + selectedIndex);
            System.out.println("clickedIndex" + clickedIndex);
            System.out.println("isHit" + isHit);
            if (isHit) {
                this.selectedIndexPopupMenu.show(l, l.getX(), l.getY());
            }

        }
    }

    public ProbabilityChartFrame getFrame() {
        return this.frame;
    }

    public void setSequenceList(JList sequenceList) {
        this.sequenceList = sequenceList;
    }

    public JList getSequenceList() {
        return this.sequenceList;
    }
}
