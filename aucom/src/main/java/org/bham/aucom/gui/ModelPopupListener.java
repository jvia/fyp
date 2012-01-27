package org.bham.aucom.gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;

class ModelPopupListener extends MouseAdapter {
	JPopupMenu selectedItempopupMenu;
	JPopupMenu freeSpacePopupMenu;
	JList list;

	ModelPopupListener(JList list, JPopupMenu selectedItempopupMenu,
			JPopupMenu freeSpacePopupMenu) {
		this.list = list;
		this.selectedItempopupMenu = selectedItempopupMenu;
		this.freeSpacePopupMenu = freeSpacePopupMenu;
	}

	ModelPopupListener(JList list, JPopupMenu selectedItempopupMenu) {
		this.list = list;
		this.selectedItempopupMenu = selectedItempopupMenu;
		this.freeSpacePopupMenu = null;
	}

	private void maybeShowPopup(MouseEvent e) {
		int selectedIndex = list.getSelectedIndex();
		if (selectedIndex == -1) {
			if (freeSpacePopupMenu != null && e.isPopupTrigger()) {
				freeSpacePopupMenu.show(e.getComponent(), e.getX(), e.getY());
			} else {
				return;
			}

		} else {
			int indexClicked = list.locationToIndex(e.getPoint());
			Rectangle klickedBounds = list.getCellBounds(indexClicked,
					indexClicked);
			klickedBounds.contains(e.getPoint());
			if (e.isPopupTrigger()) {
				if (klickedBounds.contains(e.getPoint())) {
					this.selectedItempopupMenu.show(e.getComponent(), e.getX(), e
							.getY());
				} else {
					this.freeSpacePopupMenu.show(e.getComponent(), e.getX(), e
							.getY());
				}
			}else if(e.getClickCount()>=2){
				// double click
				
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		maybeShowPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		maybeShowPopup(e);
	}

}
