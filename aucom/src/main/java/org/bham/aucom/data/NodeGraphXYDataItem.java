package org.bham.aucom.data;

import org.jfree.data.xy.XYDataItem;



public class NodeGraphXYDataItem extends XYDataItem{
       private static final long serialVersionUID = 0L;
    
	private double val;
	private int classId;
	public NodeGraphXYDataItem(double x, double y, double val, int classId) {
		super(x, y);
		this.setVal(val);
		this.setClassId(classId);
	}
	private void setVal(double val) {
		this.val = val;
	}
	public double getVal() {
		return val;
	}
	private void setClassId(int classId) {
		this.classId = classId;
	}
	public int getClassId() {
		return classId;
	}
}
