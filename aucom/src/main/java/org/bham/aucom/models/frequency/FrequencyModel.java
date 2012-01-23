package org.bham.aucom.models.frequency;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;

import java.util.LinkedHashMap;

class FrequencyModel {

    private LinkedHashMap<Integer, Integer> data;
    private int numValues;

    public FrequencyModel()
    {
        data = new LinkedHashMap<Integer, Integer>();
        numValues = 0;
    }

    public void put(Integer classId)
    {
        if (!getData().containsKey(classId))
            getData().put(classId, 0);
        getData().put(classId, getData().get(classId) + 1);
        setNumValues(getNumValues() + 1);
    }

    public double getProbability(Integer classId)
    {
        if (!getData().containsKey(classId))
            return LOWEST_PROBABILITY;
        return getData().get(classId).doubleValue() / getNumValues();
    }

    public void setData(LinkedHashMap<Integer, Integer> data)
    {
        this.data = data;
    }

    LinkedHashMap<Integer, Integer> getData()
    {
        return this.data;
    }

    void setNumValues(int numValues)
    {
        this.numValues = numValues;
    }

    int getNumValues()
    {
        return this.numValues;
    }
}
