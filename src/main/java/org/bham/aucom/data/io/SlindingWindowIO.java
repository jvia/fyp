package org.bham.aucom.data.io;

import org.bham.aucom.data.util.SlidingWindow;

public class SlindingWindowIO extends BinaryIO<SlidingWindow> {
    SlindingWindowIO() {
        super(SlidingWindow.class);
    }

}
