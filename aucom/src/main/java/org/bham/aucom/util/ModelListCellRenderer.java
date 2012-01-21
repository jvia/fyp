package org.bham.aucom.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.bham.aucom.diagnoser.Model;


public class ModelListCellRenderer extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = 0L;

	// This is the only method defined by ListCellRenderer.
	// We just reconfigure the JLabel each time we're called.

	@Override
	public Component getListCellRendererComponent(JList list, // the list
			Object value, // value to display
			int index, // cell index
			boolean isSelected, // is the cell selected
			boolean cellHasFocus) // does the cell have focus
	{
		String s="noval";
		if(value!=null)
			s = ((Model)value).getName();
			
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
