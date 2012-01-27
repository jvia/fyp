package org.bham.aucom.fts.filter;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.AbstractNode;

public class SubClassFilter<Tin, Tout extends Tin> extends AbstractNode<Tin, Tout> {
	Class<Tout> cl;
	public SubClassFilter(Class<Tout> cl) {
		this.cl = cl;
	}

	public boolean isMatch(Event<? extends Tin> e) throws Exception {
		return cl.isInstance(e);	
	}

	@Override
	public Event<? extends Tout> handleEvent(Event<? extends Tin> input) throws Exception {
		if(isMatch(input)){
			@SuppressWarnings("unchecked")
			Tout data = (Tout)input.getData();
			return input.createDerived(data);
		}
		return null;
	}
}
