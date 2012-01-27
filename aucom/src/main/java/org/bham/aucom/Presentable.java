package org.bham.aucom;

import javax.swing.JPanel;

/**
 * An interface which ensures that all implementing classes can provide 
 * a JPanel on request.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public interface Presentable {

    /**
     * Return a JPanel.
     * 
     * @return JPanel
     */
    public JPanel getPanel();
}
