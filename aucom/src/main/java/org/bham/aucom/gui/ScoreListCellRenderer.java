package org.bham.aucom.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;

public class ScoreListCellRenderer extends JLabel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049971886859221829L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean hasFocus) {
		String s="undefined";
		if(value!=null)
			s = ((TimeSeries<Score>)value).toString();
		setText(s);
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		return this;
		
		
	}

}
