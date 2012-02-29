package org.bham.aucom.xcfrecorder;

import java.util.EventObject;

public class RecorderStatusChangedEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private final RecorderState oldState;
    private final RecorderState newState;

    public RecorderStatusChangedEvent(Object arg0, RecorderState inOldState,
                                      RecorderState inNewState) {
        super(arg0);
        oldState = inOldState;
        newState = inNewState;
    }

    public RecorderState getOldState() {
        return oldState;
    }

    public RecorderState getNewState() {
        return newState;
    }

}
