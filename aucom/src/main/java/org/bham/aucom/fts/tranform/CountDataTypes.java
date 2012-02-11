package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;

import java.util.HashMap;

/**
 * A node which counts the number of events seen from any given type of event.
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


    @Override
    protected DataType iTransform(DataType input) throws Exception {
        int eventType = input.getEventType();
        if (counting.containsKey(eventType)) {
            counting.put(eventType, counting.get(eventType) + 1);
        } else {
            counting.put(eventType, 1);
        }
        return input;
    }


    protected void setCounting(HashMap<Integer, Integer> counting) {
        this.counting = counting;
    }


    public HashMap<Integer, Integer> getCounting() {
        return counting;
    }

}
