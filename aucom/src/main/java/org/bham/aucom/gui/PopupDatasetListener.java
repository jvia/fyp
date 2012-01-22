package org.bham.aucom.gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;

class PopupDatasetListener extends MouseAdapter {
	JPopupMenu selectedItempopupMenu;
	JPopupMenu freeSpacePopupMenu;
	JList list;

	PopupDatasetListener(JList list, JPopupMenu selectedItempopupMenu,
			JPopupMenu freeSpacePopupMenu) {
		this.list = list;
		this.selectedItempopupMenu = selectedItempopupMenu;
		this.freeSpacePopupMenu = freeSpacePopupMenu;
	}

	PopupDatasetListener(JList list, JPopupMenu selectedItempopupMenu) {
		this.list = list;
		this.selectedItempopupMenu = selectedItempopupMenu;
		this.freeSpacePopupMenu = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		maybeShowPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		maybeShowPopup(e);
	}

	private void maybeShowPopup(MouseEvent e) {
		int selectedIndex = list.getSelectedIndex();
		if (selectedIndex == -1) {
			if (freeSpacePopupMenu != null && e.isPopupTrigger()) {
				freeSpacePopupMenu.show(e.getComponent(), e.getX(), e.getY());
			}

		} else {
			int indexClicked = list.locationToIndex(e.getPoint());
			Rectangle klickedBounds = list.getCellBounds(indexClicked,
					indexClicked);
			klickedBounds.contains(e.getPoint());
			if (e.isPopupTrigger()) {
				if (klickedBounds.contains(e.getPoint())) {
					for(int i=0; i< selectedItempopupMenu.getSubElements().length; i++){
						
					}
					selectedItempopupMenu.show(e.getComponent(), e.getX(), e
							.getY());
				} else {
					freeSpacePopupMenu.show(e.getComponent(), e.getX(), e
							.getY());
				}
			}
		}
	}
}
