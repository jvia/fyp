package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.AbstractData;

import java.io.Serializable;

/**
 * This node shifts the timestamps of all incoming data. Essentially, all input
 * is shifted the amount which makes the first input's timestamp 0.
 *
 * @param <T> a datum of type AbstractData
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class CropTimestampFromData<T extends AbstractData> extends AbstractAucomTranformNode<T, T> implements Serializable {

    // -1 is the placeholder for there being no timestamp set
    private static final long NO_TIMESTAMP = -1;
    private static final long serialVersionUID = 1L;

    private long firstTimestamp;

    /**
     * Create the timestamp cropping node.
     */
    public CropTimestampFromData() {
        super("CropTimestampFromData");
        firstTimestamp = NO_TIMESTAMP;
    }

    /**
     * Shifts the timestamp of the incoming input by the timestamp of the first
     * input. This has the effect of shifting the whole time series such that
     * the
     * first entry occurs at timestamp 0.
     *
     * @param input the input
     * @return the input with a shifted timestamp
     * @throws Exception something went wrong
     */
    @Override
    protected T iTransform(T input) throws Exception {
        if (getFirstTimestamp() == NO_TIMESTAMP) {
            setFirstTimestamp(input.getTimestamp());
        }
        input.setTimestamp(input.getTimestamp() - getFirstTimestamp());
        return input;
    }

    /**
     * Unset the first timestamp.
     */
    public void reset() {
        setFirstTimestamp(NO_TIMESTAMP);
    }

    /**
     * Set the time of the first timestamp. This is the value that will be used
     * to shift all other timestamps.
     *
     * @param firstTimestamp the timestamp
     */
    public void setFirstTimestamp(long firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    /**
     * Get the timestamp offset.
     *
     * @return the value by which to shift timestamps
     */
    public long getFirstTimestamp() {
        return firstTimestamp;
    }
}
