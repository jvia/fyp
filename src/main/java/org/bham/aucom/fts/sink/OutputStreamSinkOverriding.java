package org.bham.aucom.fts.sink;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.sink.BufferOutputEvent;
import net.sf.xcf.fts.nodes.sink.SinkAdapter;

import java.io.OutputStream;

public class OutputStreamSinkOverriding extends SinkAdapter<BufferOutputEvent> {
    boolean override = false;
    protected final OutputStream os;

    public OutputStreamSinkOverriding(OutputStream os) {
        this.os = os;
    }

    public OutputStreamSinkOverriding(OutputStream os, boolean override) {
        this.os = os;
        this.override = override;
    }

    @Override
    public Event<Void> handleEvent(Event<? extends BufferOutputEvent> input)
            throws Exception {
        super.handleEvent(input);
        if ("last".equals(input.getTag("item")))
            Thread.currentThread().interrupt();
        return null;
    }

    @Override
    protected void pushItem(BufferOutputEvent evt) throws Exception {
        os.write(evt.buf, evt.offset, evt.length);
        os.flush();
    }

}
