package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.sink.BufferOutputEvent;
import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;

public class StringToBufferOutputEvent extends AbstractTransformNode<String, BufferOutputEvent> {
	@Override
	protected BufferOutputEvent transform(String arg0) throws Exception {
		try {
			BufferOutputEvent event = new BufferOutputEvent(arg0.getBytes());
			return event;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
}
