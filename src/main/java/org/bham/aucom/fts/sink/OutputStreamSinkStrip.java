package org.bham.aucom.fts.sink;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.sink.BufferOutputEvent;
import net.sf.xcf.fts.nodes.sink.OutputStreamSink;

import java.io.OutputStream;

public class OutputStreamSinkStrip extends OutputStreamSink {
    public OutputStreamSinkStrip(OutputStream os) {
        super(os);
    }

    public OutputStreamSinkStrip(OutputStream os, boolean override) {
        super(os);
    }

    @Override
    public Event<Void> handleEvent(Event<? extends BufferOutputEvent> input)
            throws Exception {
        try {

            super.handleEvent(input);
            if ("last".equals(input.getTag("item"))) {
                System.out.println("last element received");
                Thread.currentThread().interrupt();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
