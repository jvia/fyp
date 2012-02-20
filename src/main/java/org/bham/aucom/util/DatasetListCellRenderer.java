package org.bham.aucom.util;

import javax.swing.*;
import java.awt.*;

public class DatasetListCellRenderer extends JLabel implements ListCellRenderer {
    private static final long serialVersionUID = 0L;

    // This is the only method defined by ListCellRenderer.
    // We just reconfigure the JLabel each time we're called.

    @SuppressWarnings("unchecked")
    @Override
    public Component getListCellRendererComponent(JList list, // the list
                                                  Object value, // value to display
                                                  int index, // cell index
                                                  boolean isSelected, // is the cell selected
                                                  boolean cellHasFocus) // does the cell have focus
    {
        String s = "unknown datasequence";
        if (value != null)
            s = value.toString();

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
