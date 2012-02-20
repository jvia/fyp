package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.AbstractData;

public class PauseNode<TIn extends AbstractData> extends AbstractAucomTranformNode<TIn, TIn> {
    Object pauseObject;
    private boolean pause;

    public PauseNode() {
        super("PauseNode");
        pauseObject = new Object();
        pause = false;
    }

    @Override
    protected TIn iTransform(TIn input) throws Exception {
        if (isPaused()) {
            synchronized (pauseObject) {
                pauseObject.wait();
            }
        }
        return input;
    }

    public void pause() {
        this.pause = true;
    }

    public void resume() {
        synchronized (pauseObject) {
            pauseObject.notifyAll();
        }
        this.pause = false;
    }

    public boolean isPaused() {
        return pause;
    }

}
