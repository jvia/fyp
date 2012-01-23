package org.bham.aucom.fts.sink;

import net.sf.xcf.fts.nodes.sink.SinkAdapter;

class ToStringSink<T> extends SinkAdapter<T> {

	@Override
	protected void pushItem(T arg0) throws Exception {
		System.out.println();
		System.out.println(arg0.toString());
		System.out.println();
	}

}
