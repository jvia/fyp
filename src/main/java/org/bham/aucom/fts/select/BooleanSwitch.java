package org.bham.aucom.fts.select;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.filter.AbstractFilter;

import java.util.logging.Logger;

public class BooleanSwitch extends AbstractFilter {
    Logger l = Logger.getLogger(this.getClass().getCanonicalName());
    boolean filterState;

    public BooleanSwitch() {
        this.filterState = false;
    }

    @Override
    public boolean isMatch(Event e) throws Exception {
        return this.filterState;
    }

    public void enable() {
        this.l.info("booleanswitch ENABLED");
        this.filterState = true;
    }

    public void disable() {
        this.filterState = false;
        this.l.info("booleanswitch DISABLED");

    }

}
