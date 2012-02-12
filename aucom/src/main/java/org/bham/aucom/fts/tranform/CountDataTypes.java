package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;

import java.util.HashMap;

/**
 * A node which counts the number of events seen from any given type of event.
 * This data is used for statistical purposes.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk
 */
public class CountDataTypes extends AbstractAucomTranformNode<DataType, DataType> {
    private HashMap<Integer, Integer> counting;

    /**
     * Create the counting node.
     */
    public CountDataTypes() {
        super("CountDataTypes");
        setCounting(new HashMap<Integer, Integer>());
    }


    /**
     * Increments the count associated with this event type.
     *
     * @param input a system event
     * @return the system event
     * @throws Exception shouldn't happen
     */
    @Override
    protected DataType iTransform(DataType input) throws Exception {
        // TODO :: determine if this node can be removed. Seem useless.
        // Create the mapping if necessary
        if (!(counting.containsKey(input.getEventType())))
            counting.put(input.getEventType(), 0);

        // Increment the count
        counting.put(input.getEventType(), counting.get(input.getEventType()) + 1);

        return input;
    }


    /**
     * Set the counter.
     *
     * @param counting the new counter.
     */
    protected void setCounting(HashMap<Integer, Integer> counting) {
        this.counting = counting;
    }

    /**
     * Get the counter.
     *
     * @return the counter.
     */
    public HashMap<Integer, Integer> getCounting() {
        return counting;
    }

}
