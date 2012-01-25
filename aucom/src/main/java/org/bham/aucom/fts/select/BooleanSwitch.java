package org.bham.aucom.fts.select;

import java.util.logging.Logger;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.filter.AbstractFilter;

class BooleanSwitch extends AbstractFilter<Object> {
	private final Logger l = Logger.getLogger(this.getClass().getCanonicalName());
	private boolean filterState;
	public BooleanSwitch() {
		this.filterState = false;
	}
	@Override
	public boolean isMatch(Event<?> e) throws Exception {
		return this.filterState;
	}
	public void enable(){
		this.l.info("booleanswitch ENABLED");
		this.filterState = true;
	}
	public void disable(){
		this.filterState = false;
		this.l.info("booleanswitch DISABLED");
		
	}

}
