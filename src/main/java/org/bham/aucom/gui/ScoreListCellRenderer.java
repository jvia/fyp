package org.bham.aucom.gui;

import javax.swing.*;
import java.awt.*;

public class ScoreListCellRenderer extends JLabel implements ListCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = -4049971886859221829L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean hasFocus) {
        String s = "undefined";
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
