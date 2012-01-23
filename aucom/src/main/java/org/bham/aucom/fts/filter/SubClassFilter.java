package org.bham.aucom.fts.filter;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.AbstractNode;

class SubClassFilter<Tin, Tout extends Tin> extends AbstractNode<Tin, Tout> {
	private final Class<Tout> cl;
	public SubClassFilter(Class<Tout> cl) {
		this.cl = cl;
	}

	boolean isMatch(Event<? extends Tin> e) {
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
